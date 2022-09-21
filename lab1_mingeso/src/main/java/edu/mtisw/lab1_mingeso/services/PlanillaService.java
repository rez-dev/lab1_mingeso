package edu.mtisw.lab1_mingeso.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mtisw.lab1_mingeso.entities.PlanillaEntity;
import edu.mtisw.lab1_mingeso.repositories.PlanillaRepository;

@Service
public class PlanillaService {
    @Autowired
    PlanillaRepository planillaRepository;

    public ArrayList<PlanillaEntity> obtenerPlanillas() {
        return (ArrayList<PlanillaEntity>) planillaRepository.findAll();
    }

    public void guardarPlanilla(PlanillaEntity planilla) {
        planillaRepository.save(planilla);
    }

    public void eliminarTodasLasPlanillas() {
        planillaRepository.deleteAll();
    }
}
