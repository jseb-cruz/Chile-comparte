package com.usta.proyectoIntegrador.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="ventas" )
public class SaleEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Long idVenta;


    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha" , nullable = false)
    private LocalDate fecha;


    @NotNull
    @PositiveOrZero
    @Digits(integer = 12, fraction = 2)
    @Column(name="total", precision = 14, scale = 2, nullable = false)
    private BigDecimal total;


    @Column(name = "estado_v", nullable = false)
    private  boolean estadoV = true;


    //realcion con usuarios

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuarios_id_usu")
    private UserEntity usuario;

    //rlacion con detalle de ventas
    @OneToMany(mappedBy = "venta", fetch = FetchType.LAZY,
    cascade = CascadeType.ALL,orphanRemoval = true)
    private List<SaleDetailEntity> detalleVentas= new ArrayList<>();


    public SaleEntity(){

    }

}
