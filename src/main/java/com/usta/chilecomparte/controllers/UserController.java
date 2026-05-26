package com.usta.chilecomparte.controllers;

import com.usta.chilecomparte.entities.RoleEntity;
import com.usta.chilecomparte.entities.UserEntity;
import com.usta.chilecomparte.services.interfaces.RoleService;
import com.usta.chilecomparte.services.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @GetMapping(value = "/register")
    public String crearUsuario(Model model) {
        model.addAttribute("usuario", new UserEntity());
        model.addAttribute("title", "Register a new user");
        return "register";
    }

    @PostMapping("/register")
    public String registro(@ModelAttribute("usuario") @Valid UserEntity usuario,
                           BindingResult result,
                           @RequestParam("confirmarClave") String confirmarClave,
                           Model model,
                           RedirectAttributes redirectAttributes,
                           SessionStatus status) {


        if (result.hasErrors()) {
            model.addAttribute("title", "Register a new user");
            return "register";
        }


        if (!usuario.getClave().equals(confirmarClave)) {
            result.rejectValue("clave", "error.usuario", "The passwords do not match.");
            model.addAttribute("title", "Register a new User");
            return "register";
        }


        String pass = new BCryptPasswordEncoder().encode(usuario.getClave());
        usuario.setClave(pass);


        RoleEntity rolAsignado = roleService.findByRol("USER");
        usuario.setRol(rolAsignado);

        userService.save(usuario);
        status.setComplete();

        redirectAttributes.addFlashAttribute("success", "Usuario registrado exitosamente!");
        return "redirect:/login";
    }
}