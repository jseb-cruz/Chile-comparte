package com.usta.proyectoIntegrador.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "detalles_ventas")


public class SaleDetailEntity implements Serializable {
private static final long serialVersionUID = 1L;


@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private  Long idDetalle;

@NotNull
    @Min(1)
    @Column(name = "cantidad",nullable = false)
    private  Integer cantidad;


    @NotNull
    @PositiveOrZero
    @Digits(integer = 8, fraction = 2)
    @Column(name="precio_unit", precision = 10, scale = 2, nullable = false)
    private BigDecimal precioUnit;


    //relacion con ventas
    @NotNull
    @PositiveOrZero
    @Digits(integer = 12, fraction = 2)
    @Column(name = "subtotal",precision = 14,scale = 2,nullable = false)
    private BigDecimal subtotal ;

    //relacion con ventas
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "id_venta",nullable = false)
    private  SaleEntity venta;

    //relacion con productos
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "id_product",nullable = false)
    private  ProductEntity producto;

    public SaleDetailEntity(){}



}
