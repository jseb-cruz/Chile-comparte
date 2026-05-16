package com.usta.proyectoIntegrador.models.services;

import com.usta.proyectoIntegrador.entities.TestimonialEntity;
import com.usta.proyectoIntegrador.models.daos.TestimonialDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestimonialServiceImpl implements TestimonialService {

    private final TestimonialDao testimonialDao;

    public TestimonialServiceImpl(TestimonialDao testimonialDao) {
        this.testimonialDao = testimonialDao;
    }

    @Override
    public List<TestimonialEntity> listAll() {
        return testimonialDao.findAll();
    }

    @Override
    public void save(TestimonialEntity testimonial) {
        testimonialDao.save(testimonial);
    }

    @Override
    public Optional<TestimonialEntity> findById(Long id) {
        return testimonialDao.findById(id);
    }

    @Override
    public void delete(Long id) {
        testimonialDao.deleteById(id);
    }
}