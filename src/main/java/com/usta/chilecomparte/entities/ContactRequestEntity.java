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
@Table(name = "solicitud_contacto")
public class ContactRequestEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contacto") // Renombrado para mayor claridad
    private Long idContacto;

    @NotBlank
    @Size(max = 150)
    @Column(name = "nombre", length = 150, nullable = false)
    private String nombre;

    @NotBlank
    @Email
    @Size(max = 100)
    @Column(name = "email", length = 100, nullable = false) // Eliminado unique = true
    private String email;

    @NotBlank
    @Size(max = 20)
    @Column(name = "telefono", length = 20, nullable = false)
    private String telefono;

    @NotBlank
    @Size(max = 100)
    @Column(name = "finalidad", length = 100, nullable = false)
    private String finalidad;

    @NotNull // Cambiado de @NonNull a @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;


    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_usuario_revisor", nullable = true)
    private UserEntity revisor;
}