package com.usta.proyectoIntegrador.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="noticias" )
public class NewsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_noticia")
    private Long idNoticia;

    @NotBlank
    @Size(max=50)
    @Column(name="titulo",length = 50,nullable = false)
    private String titulo;

    @Size(max = 200)
    @Column(name="resumen", length = 200)
    private  String resumen;

    @Size(max = 200)
    @Column(name="contenido", length = 200)
    private  String contenido;

    @Size(max = 200)
    @Column(name="foto_url", length = 200)
    private  String fotoUrl;

    @NotBlank
    @Size(max=50)
    @Column(name="autor",length = 50,nullable = false)
    private String autor;


    @Column(name = "estado", nullable = false)
    private  boolean estado = true;

    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha" , nullable = false)
    private LocalDateTime fecha;

    //realcion con usuarios

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuarios_nombre")
    private UserEntity usuario;


    public NewsEntity(){

    }

}
