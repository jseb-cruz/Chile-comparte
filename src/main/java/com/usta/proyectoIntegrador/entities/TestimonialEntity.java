package com.usta.proyectoIntegrador.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "testimonios")


public class TestimonialEntity implements Serializable {
private static final long serialVersionUID = 1L;


@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_testimonio")
    private  Long idTestimonio;

    @NotBlank
    @Size(max=50)
    @Column(name="nombre",length = 50,nullable = false)
    private String nombre;

    @Size(max = 200)
    @Column(name="foto_url", length = 200)
    private  String fotoUrl;

    @Size(max = 200)
    @Column(name="instagram_url", length = 200)
    private  String instagramUrl;

    @Size(max = 200)
    @Column(name="facebook_url", length = 200)
    private  String facebookUrl;

    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha" , nullable = false)
    private LocalDate fecha;

    public TestimonialEntity(){}




}
