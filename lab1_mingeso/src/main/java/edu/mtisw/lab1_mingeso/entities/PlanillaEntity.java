package edu.mtisw.lab1_mingeso.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="planilla")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanillaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    private String nombre_completo;
    private int agnos_servicio;
    private int monto_bono_agnos_servicio;
    private int monto_bonos_horas_extras;
    private int monto_descuentos;
    private int sueldo_bruto;
    private int cotizacion_previsional;
    private int cotizacio_salud;
    private int monto_sueldo_final;
    private int id_categoria_planilla;

    //Foranea
//    @ManyToOne
//    @JoinColumn(name = "rut_empleado_planilla")
//    private EmpleadoEntity empleado;
    // @ManyToOne(fetch=FetchType.LAZY)
    // @JoinColumn(name="rut_empleado_planilla")
    // private EmpleadoEntity empleadoEntity;
    private String rut_empleado_planilla;
}
