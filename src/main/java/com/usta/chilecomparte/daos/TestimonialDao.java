package com.usta.chilecomparte.daos;

import com.usta.chilecomparte.entities.TestimonialEntity;
import com.usta.chilecomparte.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestimonialDao extends JpaRepository<TestimonialEntity, Long> {
    List<TestimonialEntity> findByUsuario(UserEntity usuario);

    public interface TestimonioRepository extends JpaRepository<TestimonialEntity, Long> {
        List<TestimonialEntity> findByUsuario(UserEntity usuario);
    }

    @Query(value = "SELECT * FROM testimonios ORDER BY RANDOM() LIMIT 8", nativeQuery = true)
    List<TestimonialEntity> findRandom8();
}