package com.usta.proyectoIntegrador.models.daos;

import com.usta.proyectoIntegrador.entities.ContactRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactRequestDao extends JpaRepository<ContactRequestEntity, Long> {

    Optional<ContactRequestEntity> findByEmail(String email);

    boolean existsByEmail(String email);
}

