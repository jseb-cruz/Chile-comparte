package com.usta.proyectoIntegrador.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name="usuarios")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Size(max=10)
@Column(name="cedula")
    private String cedula;

    @NotBlank
    @Size(max=50)
    @Column(name="nombre_usu",length = 50,nullable = false)
    private String nombre;

    @NotBlank
    @Size(max=50)
    @Column(name="apellido_usu",length = 50,nullable = false)
    private String apellido;

    @NotBlank
    @Email
    @Size
    @Column(name ="email",length = 100, nullable = false,unique = true)
    private  String email;

    @NotBlank
    @Size(max = 100)
    @Column(name ="clave", length = 100,nullable = false)
    private String clave;

    @Column(name ="estado_usu", nullable = false)
    private  boolean estadoUsu = true;

    //realcion con roles
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name ="id_rol",nullable = false)
    private RoleEntity rol;

    public UserEntity(){

    }

}

