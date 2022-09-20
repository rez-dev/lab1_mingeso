package edu.mtisw.lab1_mingeso.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.mtisw.lab1_mingeso.entities.RelojEntity;

@Repository
public interface RelojRepository extends JpaRepository<RelojEntity, Integer> {
    //findAllRelojbyRut
    @Query(value = "select * from reloj as r where r.rut_empleado_reloj = :rut", nativeQuery = true)
    List<RelojEntity> findAllRelojbyRut(@Param("rut") String rut);
}
