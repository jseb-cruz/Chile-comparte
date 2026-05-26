package com.usta.chilecomparte.services.interfaces;

import com.usta.chilecomparte.entities.TestimonialEntity;
import com.usta.chilecomparte.entities.UserEntity;

import java.util.List;

public interface TestimonialService {
    public List<TestimonialEntity> findAll();
    public TestimonialEntity findById(Long id);
    public TestimonialEntity save(TestimonialEntity testimonio);
    public void deleteById(Long id);
    List<TestimonialEntity> findByUsuario(UserEntity usuario);
    public List<TestimonialEntity> findRandom8();
}