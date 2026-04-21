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
@Table(name = "solicitud_contacto")

public class ContactRequestEntity implements Serializable {
private static final long serialVersionUID= 1L;

@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idProd;


@NotBlank
    @Size(max = 500)
    @Column(name = "nombre", length = 50, nullable = false)
private  String nombre;

    @NotBlank
    @Email
    @Size
    @Column(name ="email",length = 100, nullable = false,unique = true)
    private  String email;


@NotBlank
    @Size(max = 200)
     @Column(name = "telefono",length = 200,nullable = false)
    private String telefono;

    @NotBlank
    @Size(max = 200)
    @Column(name = "finalidad",length = 200,nullable = false)
private  String finalidad;

    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "fecha" , nullable = false)
    private LocalDateTime fecha;

    //realcion con usuarios

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuarios_nombre")
    private UserEntity usuario;


    public ContactRequestEntity(){}

}

