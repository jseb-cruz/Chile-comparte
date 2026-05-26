package com.usta.chilecomparte.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "testimonios")
public class TestimonialEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_testimonio")
    private Long idTestimonio;

    @NotBlank
    @Size(max = 50)
    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @NotBlank
    @Size(max = 200)
    @Column(name = "foto_url", length = 200, nullable = false)
    private String fotoUrl;

    @NotBlank
    @Size(max = 500)
    @Column(name = "comentario", length = 500, nullable = false)
    private String comentario;

    @Size(max = 200)
    @Column(name = "instagram_url", length = 200)
    private String instagramUrl;

    @Size(max = 200)
    @Column(name = "facebook_url", length = 200)
    private String facebookUrl;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    // Relación con usuarios corregida (apunta al ID, no al nombre)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private UserEntity usuario;
}