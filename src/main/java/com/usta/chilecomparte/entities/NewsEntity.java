package com.usta.chilecomparte.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor // Lombok genera el constructor vacío
@Entity
@Table(name="noticias")
public class NewsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_noticia")
    private Long idNoticia;

    @NotBlank
    @Size(max = 100)
    @Column(name = "titulo", length = 100, nullable = false)
    private String titulo;

    @Size(max = 500)
    @Column(name = "resumen", length = 500)
    private String resumen;

    @NotBlank
    @Column(name = "contenido", columnDefinition = "TEXT", nullable = false)
    private String contenido;

    @NotBlank(message = "La URL de la imagen es obligatoria")
    @Size(max = 255)
    @Column(name = "foto_url", length = 255, nullable = false)
    private String fotoUrl;

    @NotBlank
    @Size(max = 50)
    @Column(name = "autor", length = 50, nullable = false)
    private String autor;

    @Column(name = "estado", nullable = false)
    private boolean estado = false;

    @NotNull // Cambiado de @NonNull a @NotNull de Jakarta
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    // Relación corregida apuntando a id_rol en lugar del nombre
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private UserEntity usuario;

    @ManyToOne
    @JoinColumn(name = "id_usuario_revisor")
    private UserEntity usuarioRevisor;
}