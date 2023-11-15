package com.roky.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String metodo;
    private Double monto;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
}
