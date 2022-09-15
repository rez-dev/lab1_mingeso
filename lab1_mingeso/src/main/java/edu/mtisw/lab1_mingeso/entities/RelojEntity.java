package edu.mtisw.lab1_mingeso.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="reloj")
public class RelojEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    private Date fecha;
    private Date hora_entrada;
    private Date hora_salida;

    //Foranea
    private String rut_empleado_reloj;
}
