package com.usta.chilecomparte.controllers;

import com.usta.chilecomparte.entities.NewsEntity;
import com.usta.chilecomparte.services.interfaces.ImgBBService;
import com.usta.chilecomparte.services.interfaces.NewsService;
import com.usta.chilecomparte.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private ImgBBService imgBBService;

    @Autowired
    private UserService userService;

    // --- SECCIÓN WEB (Vistas Thymeleaf) ---

    // Página de listado público de noticias aprobadas
    @GetMapping("/noticias")
    public String listarTodasWeb(Model model) {
        model.addAttribute("todasNoticias", newsService.findByEstadoOrderByFechaDesc(true, Pageable.unpaged()));
        return "noticias/lista";
    }

    // --- SECCIÓN API (Endpoints para Dashboard y Admin) ---

    @ResponseBody
    @RequestMapping(value = "/api/news", method = RequestMethod.GET)
    public ResponseEntity<?> listarNoticiasApi(@RequestParam(required = false) Boolean estado, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            if (estado != null) {
                Page<NewsEntity> noticiasPaginadas = newsService.findByEstadoOrderByFechaDesc(estado, pageable);
                return new ResponseEntity<>(noticiasPaginadas, HttpStatus.OK);
            }
            return new ResponseEntity<>(newsService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al obtener noticias", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseBody
    @PostMapping(value = "/api/news", consumes = "multipart/form-data")
    public ResponseEntity<?> crearNoticia(

            @RequestPart("noticia") NewsEntity noticia,

            @RequestPart(value = "imagen", required = false) org.springframework.web.multipart.MultipartFile imagen

    ) {

        try {

            if (imagen != null && !imagen.isEmpty()) {

                String url = imgBBService.subirImagen(imagen);

                noticia.setFotoUrl(url);
            }

            noticia.setEstado(false);

            NewsEntity nueva = newsService.save(noticia);

            return new ResponseEntity<>(nueva, HttpStatus.CREATED);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @PostMapping("/api/news/aprobar/{id}")
    public ResponseEntity<?> aprobarNoticia(@PathVariable Long id) {
        NewsEntity noticia = newsService.findById(id);
        if (noticia != null) {
            noticia.setEstado(true); // Publicación automática
            newsService.save(noticia);
            return new ResponseEntity<>("Noticia aprobada", HttpStatus.OK);
        }
        return new ResponseEntity<>("Noticia no encontrada", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/noticias/eliminar/{id}")
    public String eliminarNoticia(@PathVariable Long id) {

        newsService.deleteById(id);

        return "redirect:/dashboard";
    }

    @GetMapping("/noticias/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        model.addAttribute("noticia", newsService.findById(id));

        return "editarNoticia";
    }

    @PostMapping("/noticias/actualizar")
    public String actualizarNoticia(NewsEntity noticia, @RequestParam(value = "imagen", required = false) MultipartFile imagen, RedirectAttributes flash) throws Exception {

        NewsEntity original = newsService.findById(noticia.getIdNoticia());

        original.setTitulo(noticia.getTitulo());
        original.setResumen(noticia.getResumen());
        original.setContenido(noticia.getContenido());

        // Solo cambia si subieron imagen nueva
        if (!imagen.isEmpty()) {

            String url = imgBBService.subirImagen(imagen);

            original.setFotoUrl(url);
        }

        newsService.save(original);

        flash.addFlashAttribute("mensajeExito", "Noticia actualizada correctamente");

        return "redirect:/dashboard";
    }

    @GetMapping("/noticias/detalle/{id}")
    public String verDetalleNoticia(@PathVariable Long id, Model model) {
        NewsEntity noticia = newsService.findById(id);
        if (noticia == null) {
            return "redirect:/noticias";
        }
        model.addAttribute("noticia", noticia);
        return "noticias/detalle";
    }
}