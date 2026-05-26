package com.usta.chilecomparte.services.interfaces;

import com.usta.chilecomparte.entities.ContactRequestEntity;
import com.usta.chilecomparte.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContactRequestService {
    public List<ContactRequestEntity> findAll();

    public Page<ContactRequestEntity> findByFinalidad(String finalidad, Pageable pageable);

    public ContactRequestEntity findById(Long id);

    public ContactRequestEntity save(ContactRequestEntity contacto);

    public void deleteById(Long id);

    public void asignarRevisor(Long idSolicitud, UserEntity admin);

    List<ContactRequestEntity> findByEmail(String email);
}