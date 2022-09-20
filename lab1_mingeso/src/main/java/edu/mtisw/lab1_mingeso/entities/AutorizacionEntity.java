package edu.mtisw.lab1_mingeso.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="autorizacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AutorizacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    //private int total_horas;
    private int estado;

    //Foranea
//    @ManyToOne
//    @JoinColumn(name = "rut_empleado_autorizacion")
//    private EmpleadoEntity empleado;
    private String rut_empleado_autorizacion;
}
