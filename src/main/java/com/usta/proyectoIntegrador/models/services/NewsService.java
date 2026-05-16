package com.usta.proyectoIntegrador.models.services;

import com.usta.proyectoIntegrador.entities.NewsEntity;

import java.util.List;

public interface NewsService {

    public List<NewsEntity> listAll();
    public NewsEntity findById(Long id);
    public void save(NewsEntity news);
    public void delete(Long id);
    public List<NewsEntity> searchByTitle(String titulo);

    List<NewsEntity> filterNews(String titulo, String autor, Long idNoticia);
}
