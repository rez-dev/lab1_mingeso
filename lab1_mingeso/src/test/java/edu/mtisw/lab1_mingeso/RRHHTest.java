package edu.mtisw.lab1_mingeso;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.mtisw.lab1_mingeso.entities.AutorizacionEntity;
import edu.mtisw.lab1_mingeso.entities.EmpleadoEntity;
import edu.mtisw.lab1_mingeso.entities.JustificacionEntity;
import edu.mtisw.lab1_mingeso.entities.PlanillaEntity;
import edu.mtisw.lab1_mingeso.entities.RelojEntity;
import edu.mtisw.lab1_mingeso.repositories.RelojRepository;
import edu.mtisw.lab1_mingeso.services.RRHHService;
import edu.mtisw.lab1_mingeso.services.RelojService;

public class RRHHTest {
    RRHHService rrhh = new RRHHService();
    EmpleadoEntity empleado = new EmpleadoEntity();
    RelojService relojService = new RelojService();
    @Autowired
    RelojRepository relojRepository;

    @Test
    void calcularSueldoFijoMensual(){
        empleado.setRut("20.244.554-3");
        empleado.setNombres("Juan Pablo");
        empleado.setApellidos("Gonzalez Ramirez");
        empleado.setFecha_nacimiento("1998-10-10");
        empleado.setFecha_ingreso_empresa("2020-10-10");
        empleado.setId_categoria(1);

        int sueldoFijoMensual = rrhh.calcularSueldoFijoMensual(empleado);
        assertEquals(1700000, sueldoFijoMensual,0.0);
    }

    @Test
    void calcularSueldoFijoMensualCategoriaDesconocida(){
        empleado.setRut("20.244.554-3");
        empleado.setNombres("Juan Pablo");
        empleado.setApellidos("Gonzalez Ramirez");
        empleado.setFecha_nacimiento("1998-10-10");
        empleado.setFecha_ingreso_empresa("2020-10-10");
        empleado.setId_categoria(5);

        int sueldoFijoMensual = rrhh.calcularSueldoFijoMensual(empleado);
        assertEquals(0, sueldoFijoMensual,0.0);
    }

    @Test
    void getMontoHorasExtras(){
        empleado.setRut("20.244.554-3");
        empleado.setNombres("Juan Pablo");
        empleado.setApellidos("Gonzalez Ramirez");
        empleado.setFecha_nacimiento("1998-10-10");
        empleado.setFecha_ingreso_empresa("2020-10-10");
        empleado.setId_categoria(1);

        int montoHorasExtras = rrhh.getMontoPorHoraExtra(empleado);
        assertEquals(25000, montoHorasExtras,0.0);
    }

    @Test
    void getMontoHorasExtrasCategoriaDesconocida(){
        empleado.setRut("20.244.554-3");
        empleado.setNombres("Juan Pablo");
        empleado.setApellidos("Gonzalez Ramirez");
        empleado.setFecha_nacimiento("1998-10-10");
        empleado.setFecha_ingreso_empresa("2020-10-10");
        empleado.setId_categoria(5);

        int montoHorasExtras = rrhh.getMontoPorHoraExtra(empleado);
        assertEquals(0, montoHorasExtras,0.0);
    }

    @Test
    void calcularAgnosDeServicio() throws ParseException{
        empleado.setRut("20.244.554-3");
        empleado.setNombres("Juan Pablo");
        empleado.setApellidos("Gonzalez Ramirez");
        empleado.setFecha_nacimiento("1998-10-10");
        empleado.setFecha_ingreso_empresa("2000-10-10");
        empleado.setId_categoria(1);

        int agnosDeServicio = rrhh.calcularAgnosDeServicio(empleado);
        assertEquals(21, agnosDeServicio,0.0);
    }

    @Test
    void calcularAgnosDeServicioFechaIngresoIncorrecta() throws ParseException{
        empleado.setRut("20.244.554-3");
        empleado.setNombres("Juan Pablo");
        empleado.setApellidos("Gonzalez Ramirez");
        empleado.setFecha_nacimiento("1998-10-10");
        empleado.setFecha_ingreso_empresa("0000-00-00");
        empleado.setId_categoria(1);

        int agnosDeServicio = rrhh.calcularAgnosDeServicio(empleado);
        assertEquals(0, agnosDeServicio,0.0);
    }

    @Test
    void calcularMontoAgnosServicio() throws Exception{
        empleado.setRut("20.244.554-3");
        empleado.setNombres("Juan Pablo");
        empleado.setApellidos("Gonzalez Ramirez");
        empleado.setFecha_nacimiento("1998-10-10");
        empleado.setFecha_ingreso_empresa("2016-09-18");
        empleado.setId_categoria(1);

        int montoAgnosServicio = rrhh.calcularMontoAgnosServicio(empleado);
        // assertEquals(238000, montoAgnosServicio,0.0);
        assertEquals(85000, montoAgnosServicio,0.0);
    }

    @Test
    void calcularMontoAgnosServicioFechaIngresoIncorrecta() throws Exception{
        empleado.setRut("20.244.554-3");
        empleado.setNombres("Juan Pablo");
        empleado.setApellidos("Gonzalez Ramirez");
        empleado.setFecha_nacimiento("1998-10-10");
        empleado.setFecha_ingreso_empresa("0000-00-00");
        empleado.setId_categoria(1);

        int montoAgnosServicio = rrhh.calcularMontoAgnosServicio(empleado);
        assertEquals(0, montoAgnosServicio,0.0);
    }

    //CALCULAR DESCUENTOS ATRASOS
    @Test
    void calcularDescuentosAtrasosSinJustificacion() throws ParseException{
        empleado.setRut("20.244.554-3");
        empleado.setNombres("Juan Pablo");
        empleado.setApellidos("Gonzalez Ramirez");
        empleado.setFecha_nacimiento("1998-10-10");
        empleado.setFecha_ingreso_empresa("2020-10-10");
        empleado.setId_categoria(1);
        ArrayList<RelojEntity> relojes = new ArrayList<RelojEntity>();
        RelojEntity reloj1 = new RelojEntity();
        reloj1.setId((long)1);
        reloj1.setFecha("2020/10/10");
        reloj1.setHora("08:11");
        reloj1.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj1);
        RelojEntity reloj2 = new RelojEntity();
        reloj2.setId((long)2);
        reloj2.setFecha("2020/10/10");
        reloj2.setHora("18:00");
        reloj2.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj2);
        RelojEntity reloj3 = new RelojEntity();
        reloj3.setId((long)3);
        reloj3.setFecha("2020/10/11");
        reloj3.setHora("08:26");
        reloj3.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj3);
        RelojEntity reloj4 = new RelojEntity();
        reloj4.setId((long)4);
        reloj4.setFecha("2020/10/11");
        reloj4.setHora("18:00:");
        reloj4.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj4);
        RelojEntity reloj5 = new RelojEntity();
        reloj5.setId((long)5);
        reloj5.setFecha("2020/10/12");
        reloj5.setHora("08:46");
        reloj5.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj5);
        RelojEntity reloj6 = new RelojEntity();
        reloj6.setId((long)6);
        reloj6.setFecha("2020/10/12");
        reloj6.setHora("18:00");
        reloj6.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj6);
        RelojEntity reloj7 = new RelojEntity();
        reloj7.setId((long)7);
        reloj7.setFecha("2020/10/13");
        reloj7.setHora("09:20");
        reloj7.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj7);
        RelojEntity reloj8 = new RelojEntity();
        reloj8.setId((long)8);
        reloj8.setFecha("2020/10/13");
        reloj8.setHora("18:00");
        reloj8.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj8);

        ArrayList<JustificacionEntity> justificaciones = new ArrayList<JustificacionEntity>();
        // JustificacionEntity justificacion1 = new JustificacionEntity();
        // justificacion1.setId((long)1);
        // justificacion1.setFecha("2020/10/13");
        // justificacion1.setEstado((long) 1);
        // justificacion1.setRut_empleado_justificacion(empleado.getRut());
        // justificaciones.add(justificacion1);

        int descuentosAtrasos = rrhh.calcularDescuentosAtrasos(empleado,relojes,justificaciones);
        assertEquals(425000, descuentosAtrasos,0.0);
    }

    @Test
    void calcularDescuentosAtrasosConJustificacion() throws ParseException{
        empleado.setRut("20.244.554-3");
        empleado.setNombres("Juan Pablo");
        empleado.setApellidos("Gonzalez Ramirez");
        empleado.setFecha_nacimiento("1998-10-10");
        empleado.setFecha_ingreso_empresa("2020-10-10");
        empleado.setId_categoria(1);
        ArrayList<RelojEntity> relojes = new ArrayList<RelojEntity>();
        RelojEntity reloj1 = new RelojEntity();
        reloj1.setId((long)1);
        reloj1.setFecha("2020/10/10");
        reloj1.setHora("08:11");
        reloj1.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj1);
        RelojEntity reloj2 = new RelojEntity();
        reloj2.setId((long)2);
        reloj2.setFecha("2020/10/10");
        reloj2.setHora("18:00");
        reloj2.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj2);
        RelojEntity reloj3 = new RelojEntity();
        reloj3.setId((long)3);
        reloj3.setFecha("2020/10/11");
        reloj3.setHora("08:26");
        reloj3.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj3);
        RelojEntity reloj4 = new RelojEntity();
        reloj4.setId((long)4);
        reloj4.setFecha("2020/10/11");
        reloj4.setHora("18:00:");
        reloj4.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj4);
        RelojEntity reloj5 = new RelojEntity();
        reloj5.setId((long)5);
        reloj5.setFecha("2020/10/12");
        reloj5.setHora("08:46");
        reloj5.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj5);
        RelojEntity reloj6 = new RelojEntity();
        reloj6.setId((long)6);
        reloj6.setFecha("2020/10/12");
        reloj6.setHora("18:00");
        reloj6.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj6);
        RelojEntity reloj7 = new RelojEntity();
        reloj7.setId((long)7);
        reloj7.setFecha("2020/10/13");
        reloj7.setHora("09:20");
        reloj7.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj7);
        RelojEntity reloj8 = new RelojEntity();
        reloj8.setId((long)8);
        reloj8.setFecha("2020/10/13");
        reloj8.setHora("18:00");
        reloj8.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj8);

        ArrayList<JustificacionEntity> justificaciones = new ArrayList<JustificacionEntity>();
        JustificacionEntity justificacion1 = new JustificacionEntity();
        justificacion1.setId((long)1);
        justificacion1.setFecha("2020/10/13");
        justificacion1.setEstado((long) 1);
        justificacion1.setRut_empleado_justificacion(empleado.getRut());
        justificaciones.add(justificacion1);

        int descuentosAtrasos = rrhh.calcularDescuentosAtrasos(empleado,relojes,justificaciones);
        assertEquals(170000, descuentosAtrasos,0.0);
    }
    
    //CALCULAR HORAS EXTRAS
    @Test
    void calcularHorasExtrasConAutorizacion() throws ParseException{
        empleado.setRut("20.244.554-3");
        empleado.setNombres("Juan Pablo");
        empleado.setApellidos("Gonzalez Ramirez");
        empleado.setFecha_nacimiento("1998-10-10");
        empleado.setFecha_ingreso_empresa("2020-10-10");
        empleado.setId_categoria(1);
        ArrayList<RelojEntity> relojes = new ArrayList<RelojEntity>();
        RelojEntity reloj1 = new RelojEntity();
        reloj1.setId((long)1);
        reloj1.setFecha("2020/10/10");
        reloj1.setHora("08:11");
        reloj1.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj1);
        RelojEntity reloj2 = new RelojEntity();
        reloj2.setId((long)2);
        reloj2.setFecha("2020/10/10");
        reloj2.setHora("18:30");
        reloj2.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj2);
        RelojEntity reloj3 = new RelojEntity();
        reloj3.setId((long)3);
        reloj3.setFecha("2020/10/11");
        reloj3.setHora("08:26");
        reloj3.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj3);
        RelojEntity reloj4 = new RelojEntity();
        reloj4.setId((long)4);
        reloj4.setFecha("2020/10/11");
        reloj4.setHora("18:30:");
        reloj4.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj4);
        RelojEntity reloj5 = new RelojEntity();
        reloj5.setId((long)5);
        reloj5.setFecha("2020/10/12");
        reloj5.setHora("08:46");
        reloj5.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj5);
        RelojEntity reloj6 = new RelojEntity();
        reloj6.setId((long)6);
        reloj6.setFecha("2020/10/12");
        reloj6.setHora("18:30");
        reloj6.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj6);
        RelojEntity reloj7 = new RelojEntity();
        reloj7.setId((long)7);
        reloj7.setFecha("2020/10/13");
        reloj7.setHora("09:20");
        reloj7.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj7);
        RelojEntity reloj8 = new RelojEntity();
        reloj8.setId((long)8);
        reloj8.setFecha("2020/10/13");
        reloj8.setHora("18:30");
        reloj8.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj8);

        ArrayList<AutorizacionEntity> autorizaciones = new ArrayList<AutorizacionEntity>();
        AutorizacionEntity autorizacion1 = new AutorizacionEntity();
        autorizacion1.setId((long)1);
        autorizacion1.setEstado(1);
        autorizacion1.setRut_empleado_autorizacion(empleado.getRut());
        autorizaciones.add(autorizacion1);

        int horasExtras = rrhh.calcularHorasExtras(empleado,relojes,autorizaciones);
        assertEquals(2, horasExtras,0.0);
    }

    @Test
    void calcularHorasExtrasSinAutorizacion() throws ParseException{
        empleado.setRut("20.244.554-3");
        empleado.setNombres("Juan Pablo");
        empleado.setApellidos("Gonzalez Ramirez");
        empleado.setFecha_nacimiento("1998-10-10");
        empleado.setFecha_ingreso_empresa("2020-10-10");
        empleado.setId_categoria(1);
        ArrayList<RelojEntity> relojes = new ArrayList<RelojEntity>();
        RelojEntity reloj1 = new RelojEntity();
        reloj1.setId((long)1);
        reloj1.setFecha("2020/10/10");
        reloj1.setHora("08:11");
        reloj1.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj1);
        RelojEntity reloj2 = new RelojEntity();
        reloj2.setId((long)2);
        reloj2.setFecha("2020/10/10");
        reloj2.setHora("18:30");
        reloj2.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj2);
        RelojEntity reloj3 = new RelojEntity();
        reloj3.setId((long)3);
        reloj3.setFecha("2020/10/11");
        reloj3.setHora("08:26");
        reloj3.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj3);
        RelojEntity reloj4 = new RelojEntity();
        reloj4.setId((long)4);
        reloj4.setFecha("2020/10/11");
        reloj4.setHora("18:30:");
        reloj4.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj4);
        RelojEntity reloj5 = new RelojEntity();
        reloj5.setId((long)5);
        reloj5.setFecha("2020/10/12");
        reloj5.setHora("08:46");
        reloj5.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj5);
        RelojEntity reloj6 = new RelojEntity();
        reloj6.setId((long)6);
        reloj6.setFecha("2020/10/12");
        reloj6.setHora("18:30");
        reloj6.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj6);
        RelojEntity reloj7 = new RelojEntity();
        reloj7.setId((long)7);
        reloj7.setFecha("2020/10/13");
        reloj7.setHora("09:20");
        reloj7.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj7);
        RelojEntity reloj8 = new RelojEntity();
        reloj8.setId((long)8);
        reloj8.setFecha("2020/10/13");
        reloj8.setHora("18:30");
        reloj8.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj8);

        ArrayList<AutorizacionEntity> autorizaciones = new ArrayList<AutorizacionEntity>();

        int horasExtras = rrhh.calcularHorasExtras(empleado,relojes,autorizaciones);
        assertEquals(0, horasExtras,0.0);        
    }

    //CALCULAR MONTO HORAS EXTRAS
    @Test
    void calcularMontoHorasExtrasConAutorizacion() throws ParseException{
        empleado.setRut("20.244.554-3");
        empleado.setNombres("Juan Pablo");
        empleado.setApellidos("Gonzalez Ramirez");
        empleado.setFecha_nacimiento("1998-10-10");
        empleado.setFecha_ingreso_empresa("2020-10-10");
        empleado.setId_categoria(1);
        ArrayList<RelojEntity> relojes = new ArrayList<RelojEntity>();
        RelojEntity reloj1 = new RelojEntity();
        reloj1.setId((long)1);
        reloj1.setFecha("2020/10/10");
        reloj1.setHora("08:11");
        reloj1.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj1);
        RelojEntity reloj2 = new RelojEntity();
        reloj2.setId((long)2);
        reloj2.setFecha("2020/10/10");
        reloj2.setHora("18:30");
        reloj2.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj2);
        RelojEntity reloj3 = new RelojEntity();
        reloj3.setId((long)3);
        reloj3.setFecha("2020/10/11");
        reloj3.setHora("08:26");
        reloj3.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj3);
        RelojEntity reloj4 = new RelojEntity();
        reloj4.setId((long)4);
        reloj4.setFecha("2020/10/11");
        reloj4.setHora("18:30:");
        reloj4.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj4);
        RelojEntity reloj5 = new RelojEntity();
        reloj5.setId((long)5);
        reloj5.setFecha("2020/10/12");
        reloj5.setHora("08:46");
        reloj5.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj5);
        RelojEntity reloj6 = new RelojEntity();
        reloj6.setId((long)6);
        reloj6.setFecha("2020/10/12");
        reloj6.setHora("18:30");
        reloj6.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj6);
        RelojEntity reloj7 = new RelojEntity();
        reloj7.setId((long)7);
        reloj7.setFecha("2020/10/13");
        reloj7.setHora("09:20");
        reloj7.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj7);
        RelojEntity reloj8 = new RelojEntity();
        reloj8.setId((long)8);
        reloj8.setFecha("2020/10/13");
        reloj8.setHora("18:30");
        reloj8.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj8);

        ArrayList<AutorizacionEntity> autorizaciones = new ArrayList<AutorizacionEntity>();
        AutorizacionEntity autorizacion1 = new AutorizacionEntity();
        autorizacion1.setId((long)1);
        autorizacion1.setEstado(1);
        autorizacion1.setRut_empleado_autorizacion(empleado.getRut());
        autorizaciones.add(autorizacion1);

        int horasExtras = rrhh.calcularMontoHorasExtras(empleado,relojes,autorizaciones);
        assertEquals(50000, horasExtras,0.0); 
    }

    @Test
    void calcularMontoHorasExtrasSinAutorizacion() throws ParseException{
        empleado.setRut("20.244.554-3");
        empleado.setNombres("Juan Pablo");
        empleado.setApellidos("Gonzalez Ramirez");
        empleado.setFecha_nacimiento("1998-10-10");
        empleado.setFecha_ingreso_empresa("2020-10-10");
        empleado.setId_categoria(1);
        ArrayList<RelojEntity> relojes = new ArrayList<RelojEntity>();
        RelojEntity reloj1 = new RelojEntity();
        reloj1.setId((long)1);
        reloj1.setFecha("2020/10/10");
        reloj1.setHora("08:11");
        reloj1.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj1);
        RelojEntity reloj2 = new RelojEntity();
        reloj2.setId((long)2);
        reloj2.setFecha("2020/10/10");
        reloj2.setHora("18:30");
        reloj2.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj2);
        RelojEntity reloj3 = new RelojEntity();
        reloj3.setId((long)3);
        reloj3.setFecha("2020/10/11");
        reloj3.setHora("08:26");
        reloj3.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj3);
        RelojEntity reloj4 = new RelojEntity();
        reloj4.setId((long)4);
        reloj4.setFecha("2020/10/11");
        reloj4.setHora("18:30:");
        reloj4.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj4);
        RelojEntity reloj5 = new RelojEntity();
        reloj5.setId((long)5);
        reloj5.setFecha("2020/10/12");
        reloj5.setHora("08:46");
        reloj5.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj5);
        RelojEntity reloj6 = new RelojEntity();
        reloj6.setId((long)6);
        reloj6.setFecha("2020/10/12");
        reloj6.setHora("18:30");
        reloj6.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj6);
        RelojEntity reloj7 = new RelojEntity();
        reloj7.setId((long)7);
        reloj7.setFecha("2020/10/13");
        reloj7.setHora("09:20");
        reloj7.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj7);
        RelojEntity reloj8 = new RelojEntity();
        reloj8.setId((long)8);
        reloj8.setFecha("2020/10/13");
        reloj8.setHora("18:30");
        reloj8.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj8);

        ArrayList<AutorizacionEntity> autorizaciones = new ArrayList<AutorizacionEntity>();
 
        int horasExtras = rrhh.calcularMontoHorasExtras(empleado,relojes,autorizaciones);
        assertEquals(0, horasExtras,0.0);         
    }

    //CALCULAR SUELDO BRUTO
    @Test
    void calcularSueldoBruto() throws ParseException{
        empleado.setRut("20.244.554-3");
        empleado.setNombres("Juan Pablo");
        empleado.setApellidos("Gonzalez Ramirez");
        empleado.setFecha_nacimiento("1998-10-10");
        empleado.setFecha_ingreso_empresa("2020-10-10");
        empleado.setId_categoria(1);
        ArrayList<RelojEntity> relojes = new ArrayList<RelojEntity>();
        RelojEntity reloj1 = new RelojEntity();
        reloj1.setId((long)1);
        reloj1.setFecha("2020/10/10");
        reloj1.setHora("08:11");
        reloj1.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj1);
        RelojEntity reloj2 = new RelojEntity();
        reloj2.setId((long)2);
        reloj2.setFecha("2020/10/10");
        reloj2.setHora("18:30");
        reloj2.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj2);
        RelojEntity reloj3 = new RelojEntity();
        reloj3.setId((long)3);
        reloj3.setFecha("2020/10/11");
        reloj3.setHora("08:26");
        reloj3.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj3);
        RelojEntity reloj4 = new RelojEntity();
        reloj4.setId((long)4);
        reloj4.setFecha("2020/10/11");
        reloj4.setHora("18:30:");
        reloj4.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj4);
        RelojEntity reloj5 = new RelojEntity();
        reloj5.setId((long)5);
        reloj5.setFecha("2020/10/12");
        reloj5.setHora("08:46");
        reloj5.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj5);
        RelojEntity reloj6 = new RelojEntity();
        reloj6.setId((long)6);
        reloj6.setFecha("2020/10/12");
        reloj6.setHora("18:30");
        reloj6.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj6);
        RelojEntity reloj7 = new RelojEntity();
        reloj7.setId((long)7);
        reloj7.setFecha("2020/10/13");
        reloj7.setHora("09:20");
        reloj7.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj7);
        RelojEntity reloj8 = new RelojEntity();
        reloj8.setId((long)8);
        reloj8.setFecha("2020/10/13");
        reloj8.setHora("18:30");
        reloj8.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj8);

        ArrayList<AutorizacionEntity> autorizaciones = new ArrayList<AutorizacionEntity>();
        AutorizacionEntity autorizacion1 = new AutorizacionEntity();
        autorizacion1.setId((long)1);
        autorizacion1.setEstado(1);
        autorizacion1.setRut_empleado_autorizacion(empleado.getRut());
        autorizaciones.add(autorizacion1);

        ArrayList<JustificacionEntity> justificaciones = new ArrayList<JustificacionEntity>();
        JustificacionEntity justificacion1 = new JustificacionEntity();
        justificacion1.setId((long)1);
        justificacion1.setFecha("2020/10/13");
        justificacion1.setEstado((long) 1);
        justificacion1.setRut_empleado_justificacion(empleado.getRut());
        justificaciones.add(justificacion1);

        int horasExtras = rrhh.calcularSueldoBruto(empleado,relojes,justificaciones,autorizaciones);
        assertEquals(1580000, horasExtras,0.0);
    }

    //CALCULAR COTIZACION PREVISIONAL
    @Test
    void calcularCotizacionPrevisional() throws ParseException{
        empleado.setRut("20.244.554-3");
        empleado.setNombres("Juan Pablo");
        empleado.setApellidos("Gonzalez Ramirez");
        empleado.setFecha_nacimiento("1998-10-10");
        empleado.setFecha_ingreso_empresa("2020-10-10");
        empleado.setId_categoria(1);

        ArrayList<RelojEntity> relojes = new ArrayList<RelojEntity>();
        RelojEntity reloj1 = new RelojEntity();
        reloj1.setId((long)1);
        reloj1.setFecha("2020/10/10");
        reloj1.setHora("08:11");
        reloj1.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj1);
        RelojEntity reloj2 = new RelojEntity();
        reloj2.setId((long)2);
        reloj2.setFecha("2020/10/10");
        reloj2.setHora("18:30");
        reloj2.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj2);
        RelojEntity reloj3 = new RelojEntity();
        reloj3.setId((long)3);
        reloj3.setFecha("2020/10/11");
        reloj3.setHora("08:26");
        reloj3.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj3);
        RelojEntity reloj4 = new RelojEntity();
        reloj4.setId((long)4);
        reloj4.setFecha("2020/10/11");
        reloj4.setHora("18:30:");
        reloj4.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj4);
        RelojEntity reloj5 = new RelojEntity();
        reloj5.setId((long)5);
        reloj5.setFecha("2020/10/12");
        reloj5.setHora("08:46");
        reloj5.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj5);
        RelojEntity reloj6 = new RelojEntity();
        reloj6.setId((long)6);
        reloj6.setFecha("2020/10/12");
        reloj6.setHora("18:30");
        reloj6.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj6);
        RelojEntity reloj7 = new RelojEntity();
        reloj7.setId((long)7);
        reloj7.setFecha("2020/10/13");
        reloj7.setHora("09:20");
        reloj7.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj7);
        RelojEntity reloj8 = new RelojEntity();
        reloj8.setId((long)8);
        reloj8.setFecha("2020/10/13");
        reloj8.setHora("18:30");
        reloj8.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj8);

        ArrayList<AutorizacionEntity> autorizaciones = new ArrayList<AutorizacionEntity>();
        AutorizacionEntity autorizacion1 = new AutorizacionEntity();
        autorizacion1.setId((long)1);
        autorizacion1.setEstado(1);
        autorizacion1.setRut_empleado_autorizacion(empleado.getRut());
        autorizaciones.add(autorizacion1);

        ArrayList<JustificacionEntity> justificaciones = new ArrayList<JustificacionEntity>();
        JustificacionEntity justificacion1 = new JustificacionEntity();
        justificacion1.setId((long)1);
        justificacion1.setFecha("2020/10/13");
        justificacion1.setEstado((long) 1);
        justificacion1.setRut_empleado_justificacion(empleado.getRut());
        justificaciones.add(justificacion1);

        int cotizacionPrevisional = rrhh.calcularCotizacionPrevisional(empleado, relojes, justificaciones, autorizaciones);
        assertEquals(158000, cotizacionPrevisional,0.0);
    }
    //CALCULAR COTIZACION SALUD
    @Test
    void calcularCotizacionSalud() throws ParseException{
        empleado.setRut("20.244.554-3");
        empleado.setNombres("Juan Pablo");
        empleado.setApellidos("Gonzalez Ramirez");
        empleado.setFecha_nacimiento("1998-10-10");
        empleado.setFecha_ingreso_empresa("2020-10-10");
        empleado.setId_categoria(1);

        ArrayList<RelojEntity> relojes = new ArrayList<RelojEntity>();
        RelojEntity reloj1 = new RelojEntity();
        reloj1.setId((long)1);
        reloj1.setFecha("2020/10/10");
        reloj1.setHora("08:11");
        reloj1.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj1);
        RelojEntity reloj2 = new RelojEntity();
        reloj2.setId((long)2);
        reloj2.setFecha("2020/10/10");
        reloj2.setHora("18:30");
        reloj2.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj2);
        RelojEntity reloj3 = new RelojEntity();
        reloj3.setId((long)3);
        reloj3.setFecha("2020/10/11");
        reloj3.setHora("08:26");
        reloj3.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj3);
        RelojEntity reloj4 = new RelojEntity();
        reloj4.setId((long)4);
        reloj4.setFecha("2020/10/11");
        reloj4.setHora("18:30:");
        reloj4.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj4);
        RelojEntity reloj5 = new RelojEntity();
        reloj5.setId((long)5);
        reloj5.setFecha("2020/10/12");
        reloj5.setHora("08:46");
        reloj5.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj5);
        RelojEntity reloj6 = new RelojEntity();
        reloj6.setId((long)6);
        reloj6.setFecha("2020/10/12");
        reloj6.setHora("18:30");
        reloj6.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj6);
        RelojEntity reloj7 = new RelojEntity();
        reloj7.setId((long)7);
        reloj7.setFecha("2020/10/13");
        reloj7.setHora("09:20");
        reloj7.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj7);
        RelojEntity reloj8 = new RelojEntity();
        reloj8.setId((long)8);
        reloj8.setFecha("2020/10/13");
        reloj8.setHora("18:30");
        reloj8.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj8);

        ArrayList<AutorizacionEntity> autorizaciones = new ArrayList<AutorizacionEntity>();
        AutorizacionEntity autorizacion1 = new AutorizacionEntity();
        autorizacion1.setId((long)1);
        autorizacion1.setEstado(1);
        autorizacion1.setRut_empleado_autorizacion(empleado.getRut());
        autorizaciones.add(autorizacion1);

        ArrayList<JustificacionEntity> justificaciones = new ArrayList<JustificacionEntity>();
        JustificacionEntity justificacion1 = new JustificacionEntity();
        justificacion1.setId((long)1);
        justificacion1.setFecha("2020/10/13");
        justificacion1.setEstado((long) 1);
        justificacion1.setRut_empleado_justificacion(empleado.getRut());
        justificaciones.add(justificacion1);  
        
        int cotizacionSalud = rrhh.calcularCotizacionSalud(empleado, relojes, justificaciones, autorizaciones);
        assertEquals(126400, cotizacionSalud,0.0);
    }

    //CALCULAR SUELDO FINAL
    @Test
    void calcularSueldoFinal() throws ParseException{
        empleado.setRut("20.244.554-3");
        empleado.setNombres("Juan Pablo");
        empleado.setApellidos("Gonzalez Ramirez");
        empleado.setFecha_nacimiento("1998-10-10");
        empleado.setFecha_ingreso_empresa("2020-10-10");
        empleado.setId_categoria(1);

        ArrayList<RelojEntity> relojes = new ArrayList<RelojEntity>();
        RelojEntity reloj1 = new RelojEntity();
        reloj1.setId((long)1);
        reloj1.setFecha("2020/10/10");
        reloj1.setHora("08:11");
        reloj1.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj1);
        RelojEntity reloj2 = new RelojEntity();
        reloj2.setId((long)2);
        reloj2.setFecha("2020/10/10");
        reloj2.setHora("18:30");
        reloj2.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj2);
        RelojEntity reloj3 = new RelojEntity();
        reloj3.setId((long)3);
        reloj3.setFecha("2020/10/11");
        reloj3.setHora("08:26");
        reloj3.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj3);
        RelojEntity reloj4 = new RelojEntity();
        reloj4.setId((long)4);
        reloj4.setFecha("2020/10/11");
        reloj4.setHora("18:30:");
        reloj4.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj4);
        RelojEntity reloj5 = new RelojEntity();
        reloj5.setId((long)5);
        reloj5.setFecha("2020/10/12");
        reloj5.setHora("08:46");
        reloj5.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj5);
        RelojEntity reloj6 = new RelojEntity();
        reloj6.setId((long)6);
        reloj6.setFecha("2020/10/12");
        reloj6.setHora("18:30");
        reloj6.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj6);
        RelojEntity reloj7 = new RelojEntity();
        reloj7.setId((long)7);
        reloj7.setFecha("2020/10/13");
        reloj7.setHora("09:20");
        reloj7.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj7);
        RelojEntity reloj8 = new RelojEntity();
        reloj8.setId((long)8);
        reloj8.setFecha("2020/10/13");
        reloj8.setHora("18:30");
        reloj8.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj8);

        ArrayList<AutorizacionEntity> autorizaciones = new ArrayList<AutorizacionEntity>();
        AutorizacionEntity autorizacion1 = new AutorizacionEntity();
        autorizacion1.setId((long)1);
        autorizacion1.setEstado(1);
        autorizacion1.setRut_empleado_autorizacion(empleado.getRut());
        autorizaciones.add(autorizacion1);

        ArrayList<JustificacionEntity> justificaciones = new ArrayList<JustificacionEntity>();
        JustificacionEntity justificacion1 = new JustificacionEntity();
        justificacion1.setId((long)1);
        justificacion1.setFecha("2020/10/13");
        justificacion1.setEstado((long) 1);
        justificacion1.setRut_empleado_justificacion(empleado.getRut());
        justificaciones.add(justificacion1);  
        
        int cotizacionSalud = rrhh.calcularSueldoFinal(empleado, relojes, justificaciones, autorizaciones);
        assertEquals(1295600, cotizacionSalud,0.0);
    }

    //Crear Plantilla
    @Test
    void crearPlantilla() throws ParseException{
        empleado.setRut("20.244.554-3");
        empleado.setNombres("Juan Pablo");
        empleado.setApellidos("Gonzalez Ramirez");
        empleado.setFecha_nacimiento("1998-10-10");
        empleado.setFecha_ingreso_empresa("2020-10-10");
        empleado.setId_categoria(1);

        ArrayList<RelojEntity> relojes = new ArrayList<RelojEntity>();
        RelojEntity reloj1 = new RelojEntity();
        reloj1.setId((long)1);
        reloj1.setFecha("2020/10/10");
        reloj1.setHora("08:11");
        reloj1.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj1);
        RelojEntity reloj2 = new RelojEntity();
        reloj2.setId((long)2);
        reloj2.setFecha("2020/10/10");
        reloj2.setHora("18:30");
        reloj2.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj2);
        RelojEntity reloj3 = new RelojEntity();
        reloj3.setId((long)3);
        reloj3.setFecha("2020/10/11");
        reloj3.setHora("08:26");
        reloj3.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj3);
        RelojEntity reloj4 = new RelojEntity();
        reloj4.setId((long)4);
        reloj4.setFecha("2020/10/11");
        reloj4.setHora("18:30:");
        reloj4.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj4);
        RelojEntity reloj5 = new RelojEntity();
        reloj5.setId((long)5);
        reloj5.setFecha("2020/10/12");
        reloj5.setHora("08:46");
        reloj5.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj5);
        RelojEntity reloj6 = new RelojEntity();
        reloj6.setId((long)6);
        reloj6.setFecha("2020/10/12");
        reloj6.setHora("18:30");
        reloj6.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj6);
        RelojEntity reloj7 = new RelojEntity();
        reloj7.setId((long)7);
        reloj7.setFecha("2020/10/13");
        reloj7.setHora("09:20");
        reloj7.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj7);
        RelojEntity reloj8 = new RelojEntity();
        reloj8.setId((long)8);
        reloj8.setFecha("2020/10/13");
        reloj8.setHora("18:30");
        reloj8.setRut_empleado_reloj(empleado.getRut());
        relojes.add(reloj8);

        ArrayList<AutorizacionEntity> autorizaciones = new ArrayList<AutorizacionEntity>();
        AutorizacionEntity autorizacion1 = new AutorizacionEntity();
        autorizacion1.setId((long)1);
        autorizacion1.setEstado(1);
        autorizacion1.setRut_empleado_autorizacion(empleado.getRut());
        autorizaciones.add(autorizacion1);

        ArrayList<JustificacionEntity> justificaciones = new ArrayList<JustificacionEntity>();
        JustificacionEntity justificacion1 = new JustificacionEntity();
        justificacion1.setId((long)1);
        justificacion1.setFecha("2020/10/13");
        justificacion1.setEstado((long) 1);
        justificacion1.setRut_empleado_justificacion(empleado.getRut());
        justificaciones.add(justificacion1);
        
        PlanillaEntity planilla = rrhh.crearPlanilla(empleado, relojes, justificaciones, autorizaciones);
        assertEquals(empleado.getRut(), planilla.getRut_empleado_planilla());
        assertEquals(empleado.getNombres() + empleado.getApellidos(), planilla.getNombre_completo());
        assertEquals(empleado.getId_categoria(), planilla.getId_categoria_planilla());
        assertEquals(rrhh.calcularAgnosDeServicio(empleado), planilla.getAgnos_servicio());
        assertEquals(rrhh.calcularSueldoFijoMensual(empleado), planilla.getSueldo_fijo_mensual());
        assertEquals(rrhh.calcularMontoAgnosServicio(empleado), planilla.getMonto_bono_agnos_servicio());
        assertEquals(rrhh.calcularMontoHorasExtras(empleado, relojes, autorizaciones), planilla.getMonto_bonos_horas_extras());
        assertEquals(rrhh.calcularDescuentosAtrasos(empleado, relojes, justificaciones),planilla.getMonto_descuentos());
        assertEquals(rrhh.calcularSueldoBruto(empleado, relojes, justificaciones, autorizaciones), planilla.getSueldo_bruto());
        assertEquals(rrhh.calcularCotizacionPrevisional(empleado, relojes, justificaciones, autorizaciones), planilla.getCotizacion_previsional());
        assertEquals(rrhh.calcularCotizacionSalud(empleado, relojes, justificaciones, autorizaciones),planilla.getCotizacio_salud());
        assertEquals(rrhh.calcularSueldoFinal(empleado, relojes, justificaciones, autorizaciones), planilla.getMonto_sueldo_final());
    }


}
