package com.usta.chilecomparte.controllers;

import com.usta.chilecomparte.entities.UserEntity;
import com.usta.chilecomparte.services.interfaces.ContactRequestService;
import com.usta.chilecomparte.services.interfaces.NewsService;
import com.usta.chilecomparte.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    @Autowired
    private ContactRequestService contactRequestService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private UserService userService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("solicitudes", contactRequestService.findAll());
        model.addAttribute("noticiasPendientes", newsService.findByEstado(false, Pageable.unpaged()).getContent());
        return "admin/dashboard";
    }

    @PostMapping("/contacto/tomar/{id}")
    public String tomarSolicitud(@PathVariable Long id, Authentication auth) {
        UserEntity admin = userService.findByEmail(auth.getName());

        if (admin != null) {
            contactRequestService.asignarRevisor(id, admin);
        } else {
            System.out.println("Error: No se encontró usuario con email: " + auth.getName());
        }

        return "redirect:/admin/dashboard";
    }

    @PostMapping("/news/aprobar/{id}")
    public String aprobarNoticia(@PathVariable Long id, Authentication auth) {
        UserEntity admin = userService.findByEmail(auth.getName());

        newsService.aprobarNoticia(id, admin);

        return "redirect:/admin/dashboard";
    }
}