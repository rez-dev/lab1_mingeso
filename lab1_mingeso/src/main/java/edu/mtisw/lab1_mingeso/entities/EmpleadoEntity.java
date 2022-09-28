package edu.mtisw.lab1_mingeso.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "empleado")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoEntity {
    @Id
    private String rut;
    private String nombres;
    private String apellidos;
    private String fecha_nacimiento;
    private String fecha_ingreso_empresa;
    private int id_categoria;
}
