package edu.mtisw.lab1_mingeso;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.mtisw.lab1_mingeso.entities.EmpleadoEntity;
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


    // @Test
    // void calcularDescuentoAtraso() throws ParseException{
    //     empleado.setRut("20.457.671-9");
    //     empleado.setNombres("Juan Pablo");
    //     empleado.setApellidos("Gonzalez Ramirez");
    //     empleado.setFecha_nacimiento("1998-10-10");
    //     empleado.setFecha_ingreso_empresa("2020-10-10");
    //     empleado.setId_categoria(1);

    //     ArrayList<RelojEntity>relojes = relojService.obtenerRelojPorRut(null);

    //     int descuentoAtraso = rrhh.verificarAtrasos(empleado,relojes);
    //     assertEquals(0, descuentoAtraso,0.0);
    // }
}
