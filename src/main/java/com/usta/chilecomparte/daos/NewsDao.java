package com.usta.chilecomparte.daos;

import com.usta.chilecomparte.entities.NewsEntity;
import com.usta.chilecomparte.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsDao extends JpaRepository<NewsEntity, Long> {

    Page<NewsEntity> findByEstado(boolean estado, Pageable pageable);

    Page<NewsEntity> findByEstadoOrderByFechaDesc(boolean estado, Pageable pageable);

    List<NewsEntity> findByUsuarioOrderByFechaDesc(UserEntity usuario);
}