package edu.mtisw.lab1_mingeso.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.mtisw.lab1_mingeso.entities.JustificacionEntity;

@Repository
public interface JustificacionRepository extends JpaRepository<JustificacionEntity, Integer> {
    //findEstadobyRutAndFecha
    @Query(value = "select * from justificacion as j where j.rut_empleado_justificacion = :rut and j.fecha = :fecha", nativeQuery = true)
    ArrayList<JustificacionEntity> findJustificacionbyRutAndFecha(@Param("rut") String rut, @Param("fecha") String fecha);

    @Query(value = "select * from justificacion as j where j.rut_empleado_justificacion = :rut", nativeQuery = true)
    ArrayList<JustificacionEntity> findAllByRut(@Param("rut") String rut);
}
