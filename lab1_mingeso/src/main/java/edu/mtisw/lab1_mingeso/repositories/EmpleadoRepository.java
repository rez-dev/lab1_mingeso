package edu.mtisw.lab1_mingeso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.mtisw.lab1_mingeso.entities.EmpleadoEntity;

public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Integer> {

    @Query(value = "select * from empleado as e where e.rut = :rut", nativeQuery = true)
    EmpleadoEntity findEmpleadoByRut(@Param("rut") String rut);
}
