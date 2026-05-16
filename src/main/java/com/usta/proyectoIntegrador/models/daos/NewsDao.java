package com.usta.proyectoIntegrador.models.daos;

import com.usta.proyectoIntegrador.entities.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsDao extends JpaRepository<NewsEntity,Long> {

    List<NewsEntity> findByTituloContainingIgnoreCase(String titulo);

    @Query("""
                SELECT p FROM NewsEntity p
                WHERE (:titulo IS NULL OR :titulo = '' OR
                LOWER(p.titulo) LIKE LOWER(CONCAT('%', :titulo, '%')))
                AND (:autor IS NULL OR :autor ='' OR\s
                LOWER(p.autor) LIKE LOWER(CONCAT('%', :autor, '%')))
                AND (:idNoticia IS NULL OR p.idNoticia = :idNoticia)
           """)

    List<NewsEntity> filterProducts(
            @Param("titulo") String titulo,
            @Param("autor") String autor,
            @Param("titulo") String idNoticia
    );

}

