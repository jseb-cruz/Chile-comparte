package com.usta.chilecomparte.services.impl;

import com.usta.chilecomparte.daos.ContactRequestDao;
import com.usta.chilecomparte.entities.ContactRequestEntity;
import com.usta.chilecomparte.entities.UserEntity;
import com.usta.chilecomparte.services.interfaces.ContactRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContactRequestServiceImpl implements ContactRequestService {

    @Autowired
    private ContactRequestDao contactRequestDao;

    @Override
    @Transactional(readOnly = true)
    public List<ContactRequestEntity> findAll() {
        return contactRequestDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ContactRequestEntity> findByFinalidad(String finalidad, Pageable pageable) {
        return contactRequestDao.findByFinalidad(finalidad, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public ContactRequestEntity findById(Long id) {
        return contactRequestDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public ContactRequestEntity save(ContactRequestEntity contacto) {
        return contactRequestDao.save(contacto);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        contactRequestDao.deleteById(id);
    }

    @Override
    public void asignarRevisor(Long idSolicitud, UserEntity admin) {
        ContactRequestEntity solicitud = contactRequestDao.findById(idSolicitud).orElse(null);
        if (solicitud != null) {
            solicitud.setRevisor(admin);
            contactRequestDao.save(solicitud);
        }
    }

    @Override
    public List<ContactRequestEntity> findByEmail(String email) {
        return contactRequestDao.findByEmailOrderByFechaDesc(email);
    }


}