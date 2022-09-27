package edu.mtisw.lab1_mingeso;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.mtisw.lab1_mingeso.entities.EmpleadoEntity;
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
    //CALCULAR HORAS EXTRAS
    //CALCULAR MONTO HORAS EXTRAS

    //CALCULAR SUELDO BRUTO
    //CALCULAR COTIZACION PREVISIONAL
    //CALCULAR COTIZACION SALUD
    //CALCULAR SUELDO FINAL
}
