package com.usta.chilecomparte.controllers;

import com.usta.chilecomparte.entities.RoleEntity;
import com.usta.chilecomparte.services.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    // GET /api/roles - Listar todos los roles del sistema
    @GetMapping
    public ResponseEntity<List<RoleEntity>> listarRoles() {
        List<RoleEntity> roles = roleService.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    // GET /api/roles/{id} - Obtener el detalle de un rol específico
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerRol(@PathVariable Long id) {
        RoleEntity rol = roleService.findById(id);
        if (rol != null) {
            return new ResponseEntity<>(rol, HttpStatus.OK);
        }
        return new ResponseEntity<>("Rol no encontrado", HttpStatus.NOT_FOUND);
    }

    // POST /api/roles - Crear un nuevo rol
    @PostMapping
    public ResponseEntity<?> crearRol(@RequestBody RoleEntity rol) {
        try {
            // Validamos que no exista un rol con el mismo nombre para evitar duplicados
            if (roleService.findByRol(rol.getRol()) != null) {
                return new ResponseEntity<>("El nombre del rol ya existe", HttpStatus.CONFLICT);
            }

            RoleEntity nuevoRol = roleService.save(rol);
            return new ResponseEntity<>(nuevoRol, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el rol", HttpStatus.BAD_REQUEST);
        }
    }

    // DELETE /api/roles/{id} - Eliminar un rol
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarRol(@PathVariable Long id) {
        RoleEntity rol = roleService.findById(id);
        if (rol != null) {
            roleService.deleteById(id);
            return new ResponseEntity<>("Rol eliminado correctamente", HttpStatus.OK);
        }
        return new ResponseEntity<>("Rol no encontrado", HttpStatus.NOT_FOUND);
    }
}