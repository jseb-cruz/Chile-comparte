package com.usta.chilecomparte.controllers;

import com.usta.chilecomparte.entities.ContactRequestEntity;
import com.usta.chilecomparte.entities.NewsEntity;
import com.usta.chilecomparte.entities.TestimonialEntity;
import com.usta.chilecomparte.entities.UserEntity;
import com.usta.chilecomparte.services.interfaces.ContactRequestService;
import com.usta.chilecomparte.services.interfaces.NewsService;
import com.usta.chilecomparte.services.interfaces.TestimonialService;
import com.usta.chilecomparte.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.usta.chilecomparte.services.interfaces.ImgBBService;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Controller
public class ViewController {
    @Autowired
    private NewsService newsService;

    @Autowired
    private TestimonialService testimonialService;

    @Autowired
    private ContactRequestService contactRequestService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImgBBService imgBBService;


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("noticias", newsService.findByEstadoOrderByFechaDesc(true, Pageable.ofSize(3)));

        model.addAttribute("testimoniosAleatorios", testimonialService.findRandom8());
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication auth, Model model) {

        UserEntity usuario = userService.findByEmail(auth.getName());

        model.addAttribute("misNoticias", newsService.findByUsuario(usuario));

        model.addAttribute("misSolicitudes", contactRequestService.findByEmail(usuario.getEmail()));

        model.addAttribute("misTestimonios", testimonialService.findByUsuario(usuario));

        return "dashboard";
    }

    @PostMapping("/contacto/enviar")
    public String guardarContacto(ContactRequestEntity solicitud, RedirectAttributes flash) {
        // Seteamos la fecha actual antes de guardar
        solicitud.setFecha(java.time.LocalDateTime.now());

        contactRequestService.save(solicitud);

        flash.addFlashAttribute("success", "¡Solicitud enviada correctamente!");
        return "redirect:/"; // Regresa al index
    }

    @PostMapping("/noticias/guardar")
    public String guardarNoticia(

            NewsEntity noticia,

            @RequestParam("imagen") MultipartFile imagen,

            Authentication auth,

            RedirectAttributes flash

    ) {

        try {

            UserEntity autor = userService.findByEmail(auth.getName());

            noticia.setFecha(LocalDateTime.now());

            noticia.setAutor(autor.getNombre());

            noticia.setEstado(false);

            noticia.setUsuario(autor);

            if (imagen != null && !imagen.isEmpty()) {

                String url = imgBBService.subirImagen(imagen);

                noticia.setFotoUrl(url);

            }

            newsService.save(noticia);

            flash.addFlashAttribute("mensajeExito", "¡Noticia enviada con éxito! Está en espera de aprobación.");

        } catch (Exception e) {

            e.printStackTrace();

            flash.addFlashAttribute("mensajeExito", "Error subiendo imagen.");

        }

        return "redirect:/noticias";
    }


    @PostMapping("/testimonios/guardar")
    public String guardarTestimonio(
            @RequestParam("archivoFoto") MultipartFile archivoFoto,
            TestimonialEntity testimonio, // Spring mapea automáticamente los campos del form
            Authentication auth,
            RedirectAttributes flash
    ) {
        try {
            UserEntity usuario = userService.findByEmail(auth.getName());
            testimonio.setUsuario(usuario);
            testimonio.setNombre(usuario.getNombre());
            testimonio.setFecha(LocalDate.now());

            if (archivoFoto != null && !archivoFoto.isEmpty()) {
                String url = imgBBService.subirImagen(archivoFoto);
                testimonio.setFotoUrl(url);
            }

            testimonialService.save(testimonio); // Asegúrate de usar el nombre correcto del servicio
            flash.addFlashAttribute("mensajeExito", "¡Gracias por compartir tu experiencia!");

        } catch (Exception e) {
            e.printStackTrace();
            flash.addFlashAttribute("error", "Error al procesar el testimonio.");
        }
        return "redirect:/dashboard";
    }

    @GetMapping("/acceso-denegado")
    public String accesoDenegado(Model model) {
        model.addAttribute("title", "Acceso Denegado");
        return "403";
    }
}