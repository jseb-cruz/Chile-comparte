package com.usta.chilecomparte.controllers;

import com.usta.chilecomparte.entities.ContactRequestEntity;
import com.usta.chilecomparte.services.interfaces.ContactRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact-requests")
public class ContactRequestController {

    @Autowired
    private ContactRequestService contactRequestService;

    // POST /api/contact-requests - El visitante envía un mensaje
    @PostMapping
    public ResponseEntity<?> crearSolicitud(@RequestBody ContactRequestEntity solicitud) {
        try {
            ContactRequestEntity nuevaSolicitud = contactRequestService.save(solicitud);
            return new ResponseEntity<>(nuevaSolicitud, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al enviar la solicitud de contacto", HttpStatus.BAD_REQUEST);
        }
    }

    // GET /api/contact-requests - El administrador revisa las solicitudes
    @GetMapping
    public ResponseEntity<?> listarSolicitudes(
            @RequestParam(required = false) String finalidad,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        try {
            Pageable pageable = PageRequest.of(page, size);

            if (finalidad != null && !finalidad.isEmpty()) {
                Page<ContactRequestEntity> filtradas = contactRequestService.findByFinalidad(finalidad, pageable);
                return new ResponseEntity<>(filtradas, HttpStatus.OK);
            }

            return new ResponseEntity<>(contactRequestService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al obtener las solicitudes", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET /api/contact-requests/{id} - Ver detalle de una solicitud
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerSolicitud(@PathVariable Long id) {
        ContactRequestEntity solicitud = contactRequestService.findById(id);
        if (solicitud != null) {
            return new ResponseEntity<>(solicitud, HttpStatus.OK);
        }
        return new ResponseEntity<>("Solicitud no encontrada", HttpStatus.NOT_FOUND);
    }

    // DELETE /api/contact-requests/{id} - Eliminar una solicitud procesada
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarSolicitud(@PathVariable Long id) {
        ContactRequestEntity solicitud = contactRequestService.findById(id);
        if (solicitud != null) {
            contactRequestService.deleteById(id);
            return new ResponseEntity<>("Solicitud eliminada correctamente", HttpStatus.OK);
        }
        return new ResponseEntity<>("Solicitud no encontrada", HttpStatus.NOT_FOUND);
    }
}