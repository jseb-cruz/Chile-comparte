package com.usta.chilecomparte.services.impl;

import com.usta.chilecomparte.daos.NewsDao;
import com.usta.chilecomparte.entities.NewsEntity;
import com.usta.chilecomparte.entities.UserEntity;
import com.usta.chilecomparte.services.interfaces.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDao newsDao;

    @Override
    @Transactional(readOnly = true)
    public List<NewsEntity> findAll() {
        return newsDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NewsEntity> findByEstado(boolean estado, Pageable pageable) {
        return newsDao.findByEstado(estado, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NewsEntity> findByEstadoOrderByFechaDesc(boolean estado, Pageable pageable) {
        return newsDao.findByEstadoOrderByFechaDesc(estado, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public NewsEntity findById(Long id) {
        return newsDao.findById(id).orElse(null); // Retorna null si no encuentra la noticia
    }

    @Override
    @Transactional
    public NewsEntity save(NewsEntity noticia) {
        return newsDao.save(noticia);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        newsDao.deleteById(id);
    }

    @Override
    public void aprobarNoticia(Long idNoticia, UserEntity admin) {
        NewsEntity noticia = newsDao.findById(idNoticia).orElse(null);
        if (noticia != null) {
            noticia.setEstado(true);
            noticia.setUsuarioRevisor(admin);
            newsDao.save(noticia);
        }
    }

    @Override
    public List<NewsEntity> findByUsuario(UserEntity usuario) {
        return newsDao.findByUsuarioOrderByFechaDesc(usuario);
    }
}