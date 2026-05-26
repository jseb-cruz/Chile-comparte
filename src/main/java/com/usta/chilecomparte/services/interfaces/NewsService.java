package com.usta.chilecomparte.services.interfaces;

import com.usta.chilecomparte.entities.NewsEntity;
import com.usta.chilecomparte.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface NewsService {
    public List<NewsEntity> findAll();
    public Page<NewsEntity> findByEstado(boolean estado, Pageable pageable);
    public Page<NewsEntity> findByEstadoOrderByFechaDesc(boolean estado, Pageable pageable);
    public NewsEntity findById(Long id);
    public NewsEntity save(NewsEntity noticia);
    public void deleteById(Long id);
    public void aprobarNoticia(Long idNoticia, UserEntity admin);
    List<NewsEntity> findByUsuario(UserEntity usuario);

}