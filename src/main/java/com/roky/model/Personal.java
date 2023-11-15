package com.roky.model;
import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
public class Personal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String cargo;
    private String password;
}
