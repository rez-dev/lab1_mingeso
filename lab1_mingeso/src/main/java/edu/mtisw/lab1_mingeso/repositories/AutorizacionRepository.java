package edu.mtisw.lab1_mingeso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mtisw.lab1_mingeso.entities.AutorizacionEntity;

@Repository
public interface AutorizacionRepository extends JpaRepository<AutorizacionEntity, Integer> {
}
