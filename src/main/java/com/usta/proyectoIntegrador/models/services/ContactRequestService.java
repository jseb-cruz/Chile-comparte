package com.usta.proyectoIntegrador.models.services;

import com.usta.proyectoIntegrador.entities.ContactRequestEntity;

import java.util.List;
import java.util.Optional;

public interface ContactRequestService {

    public List<ContactRequestEntity> listAll();

    public void save(ContactRequestEntity contactRequest);

    public Optional<ContactRequestEntity> findById(Long id);

    public void delete(Long id);

    public Optional<ContactRequestEntity> findByEmail(String email);
}