package com.usta.chilecomparte.util;

import com.usta.chilecomparte.entities.RoleEntity;
import com.usta.chilecomparte.services.interfaces.RoleService; // Ajusta según tu estructura
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        String[] roles = {"USER", "ADMIN", "REVISOR"};

        for (String nombreRol : roles) {
            // Verificamos si el rol ya existe para no duplicar
            if (roleService.findByRol(nombreRol) == null) {
                RoleEntity role = new RoleEntity();
                role.setRol(nombreRol);
                roleService.save(role);
                System.out.println("Rol creado: " + nombreRol);
            }
        }
    }
}