package edu.mtisw.lab1_mingeso.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    //Foranea
    private int id_categoria;

//    @OneToMany(mappedBy = "empleado")
//    private Set<AutorizacionEntity> autorizaciones = new HashSet<AutorizacionEntity>();
//
//    @OneToMany(mappedBy = "empleado")
//    private Set<JustificacionEntity> justificaciones = new HashSet<JustificacionEntity>();
//
//    @OneToMany(mappedBy = "empleado")
//    private Set<PlanillaEntity> planillas = new HashSet<PlanillaEntity>();
//
    // @OneToMany(mappedBy = "empleado")
    // @JoinColumn(name = "rut_empleado_planilla", referencedColumnName = "rut")
    // private List<PlanillaEntity> planillas;

//    @OneToMany(mappedBy = "empleado")
//    private Set<RelojEntity> relojes = new HashSet<RelojEntity>();

}
