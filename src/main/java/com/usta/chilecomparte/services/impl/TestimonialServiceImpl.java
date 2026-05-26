package com.usta.chilecomparte.services.impl;

import com.usta.chilecomparte.daos.TestimonialDao;
import com.usta.chilecomparte.entities.TestimonialEntity;
import com.usta.chilecomparte.entities.UserEntity;
import com.usta.chilecomparte.services.interfaces.TestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TestimonialServiceImpl implements TestimonialService {

    @Autowired
    private TestimonialDao testimonialDao;

    @Override
    @Transactional(readOnly = true)
    public List<TestimonialEntity> findAll() {
        return testimonialDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public TestimonialEntity findById(Long id) {
        return testimonialDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public TestimonialEntity save(TestimonialEntity testimonio) {
        return testimonialDao.save(testimonio);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        testimonialDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TestimonialEntity> findByUsuario(UserEntity usuario) {
        return testimonialDao.findByUsuario(usuario);
    }

    @Transactional(readOnly = true)
    public List<TestimonialEntity> findRandom8() {
        return testimonialDao.findRandom8();
    }
}