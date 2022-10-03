package edu.mtisw.lab1_mingeso.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mtisw.lab1_mingeso.entities.EmpleadoEntity;
import edu.mtisw.lab1_mingeso.repositories.EmpleadoRepository;

@Service
public class EmpleadoService {
    @Autowired
    EmpleadoRepository empleadoRepository;

    public EmpleadoEntity obtenerEmpleadoPorRut(String rut) {
        return empleadoRepository.findEmpleadoByRut(rut);
    }

    public ArrayList<EmpleadoEntity> obtenerEmpleados(){
        return (ArrayList<EmpleadoEntity>) empleadoRepository.findAll();
    }

    public void guardarEmpleado(EmpleadoEntity empleado) {
        empleadoRepository.save(empleado);
    }
}
