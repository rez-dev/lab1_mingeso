package edu.mtisw.lab1_mingeso.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "empleado")
public class EmpleadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    private String rut;
    private String nombres;
    private String apellidos;
    private Date fecha_nacimiento;
    private Date fecha_ingreso_empresa;
    private int id_categoria;
}
