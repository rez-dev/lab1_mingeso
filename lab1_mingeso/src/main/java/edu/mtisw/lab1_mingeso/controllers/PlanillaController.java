package edu.mtisw.lab1_mingeso.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.mtisw.lab1_mingeso.entities.PlanillaEntity;
import edu.mtisw.lab1_mingeso.services.PlanillaService;

@Controller
@RequestMapping("/planilla")
public class PlanillaController {
    @Autowired
    PlanillaService planillaService;

    @GetMapping("/listar")
    public String listar(Model model){
        ArrayList<PlanillaEntity> planillas = planillaService.obtenerPlanillas();
        model.addAttribute("planillas",planillas);
        return "planillas/listadoPlanilla";
    }
}
