package edu.mtisw.lab1_mingeso.entities;

import javax.persistence.*;

@Entity
@Table(name="planilla")
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

    //Foranea
    private String rut_empleado_planilla;
    private int id_categoria_planilla;
}
