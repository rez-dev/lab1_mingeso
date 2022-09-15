package edu.mtisw.lab1_mingeso.entities;

import javax.persistence.*;

@Entity
@Table(name="autorizacion")
public class AutorizacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    private int total_horas;
    private String estado;

    //Foranea
    private String rut_empleado_autorizacion;
}
