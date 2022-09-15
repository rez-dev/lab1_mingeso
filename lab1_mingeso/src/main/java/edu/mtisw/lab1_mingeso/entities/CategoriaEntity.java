package edu.mtisw.lab1_mingeso.entities;

import javax.persistence.*;

@Entity
@Table(name="categoria")
public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    private String nombre;
    private int sueldo_fijo_mensual;
    private int monto_hora_extra;
}
