package edu.mtisw.lab1_mingeso.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="justificacion")
public class JustificacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    private Date fecha;
    private String estado;

    //Foranea
    private String rut_empleado_justificacion;
}
