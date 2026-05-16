package com.usta.proyectoIntegrador.models.services;

import com.usta.proyectoIntegrador.entities.ContactRequestEntity;
import com.usta.proyectoIntegrador.models.daos.ContactRequestDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactRequestServiceImpl implements ContactRequestService {

    private final ContactRequestDao contactRequestDao;

    public ContactRequestServiceImpl(ContactRequestDao contactRequestDao) {
        this.contactRequestDao = contactRequestDao;
    }

    @Override
    public List<ContactRequestEntity> listAll() {
        return contactRequestDao.findAll();
    }

    @Override
    public void save(ContactRequestEntity contactRequest) {
        contactRequestDao.save(contactRequest);
    }

    @Override
    public Optional<ContactRequestEntity> findById(Long id) {
        return contactRequestDao.findById(id);
    }

    @Override
    public void delete(Long id) {
        contactRequestDao.deleteById(id);
    }

    @Override
    public Optional<ContactRequestEntity> findByEmail(String email) {
        return contactRequestDao.findByEmail(email);
    }
}