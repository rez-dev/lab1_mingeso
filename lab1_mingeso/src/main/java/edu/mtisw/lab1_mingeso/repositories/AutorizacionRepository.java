package edu.mtisw.lab1_mingeso.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.mtisw.lab1_mingeso.entities.AutorizacionEntity;

@Repository
public interface AutorizacionRepository extends JpaRepository<AutorizacionEntity, Integer> {
    //findAutorizacionbyRut
    @Query(value = "select * from autorizacion as a where a.rut_empleado_autorizacion = :rut", nativeQuery = true)
    ArrayList<AutorizacionEntity> findAutorizacionbyRut(@Param("rut") String rut);
}
