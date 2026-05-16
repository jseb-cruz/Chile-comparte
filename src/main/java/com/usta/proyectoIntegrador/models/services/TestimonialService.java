package com.usta.proyectoIntegrador.models.services;

import com.usta.proyectoIntegrador.entities.TestimonialEntity;

import java.util.List;
import java.util.Optional;

public interface TestimonialService {

    public List<TestimonialEntity> listAll();

    public void save(TestimonialEntity testimonial);

    public Optional<TestimonialEntity> findById(Long id);

    public void delete(Long id);

}