package com.usta.chilecomparte.controllers;

import com.usta.chilecomparte.services.interfaces.TestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TestimonialController {

    @Autowired
    private TestimonialService testimonialService;

    @PostMapping("/testimonios/eliminar/{id}")
    public String eliminarTestimonio(@PathVariable Long id, RedirectAttributes flash) {
        try {
            testimonialService.deleteById(id);
            flash.addFlashAttribute("mensajeExito", "Testimonio eliminado correctamente.");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "No se pudo eliminar el testimonio.");
        }
        return "redirect:/dashboard";
    }
}