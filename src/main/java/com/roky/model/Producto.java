package com.roky.model;
import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private int stock;
    private Double precio;



}