package edu.mtisw.lab1_mingeso.controllers;

import java.text.ParseException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.mtisw.lab1_mingeso.entities.AutorizacionEntity;
import edu.mtisw.lab1_mingeso.entities.EmpleadoEntity;
import edu.mtisw.lab1_mingeso.entities.JustificacionEntity;
import edu.mtisw.lab1_mingeso.entities.PlanillaEntity;
import edu.mtisw.lab1_mingeso.entities.RelojEntity;
import edu.mtisw.lab1_mingeso.services.AutorizacionService;
import edu.mtisw.lab1_mingeso.services.EmpleadoService;
import edu.mtisw.lab1_mingeso.services.JustificacionService;
import edu.mtisw.lab1_mingeso.services.PlanillaService;
import edu.mtisw.lab1_mingeso.services.RRHHService;
import edu.mtisw.lab1_mingeso.services.RelojService;

@Controller
@RequestMapping("/planilla")
public class PlanillaController {
    @Autowired
    PlanillaService planillaService;
    @Autowired
    EmpleadoService empleadoService;
    @Autowired
    RRHHService rrhhService;
    @Autowired
    RelojService relojService;
    @Autowired
    JustificacionService justificacionService;
    @Autowired
    AutorizacionService autorizacionService;

    @GetMapping("/listar")
    public String listar(Model model){
        ArrayList<PlanillaEntity> planillas = planillaService.obtenerPlanillas();
        model.addAttribute("planillas",planillas);
        return "planillas/listadoPlanilla";
    }

    @GetMapping("/home")
    public String home(){
        return "planillas/homePlanilla";
    }

    @GetMapping("/calcularPlanillas")
    public String calcularPlanillas() throws ParseException{
        ArrayList<EmpleadoEntity> empleados = empleadoService.obtenerEmpleados();
        planillaService.eliminarTodasLasPlanillas();
        for (EmpleadoEntity empleado : empleados) {
            ArrayList<RelojEntity> relojes = relojService.obtenerRelojPorRut(empleado.getRut());
            ArrayList<JustificacionEntity> justificaciones = justificacionService.findAllByRut(empleado.getRut());
            ArrayList<AutorizacionEntity> autorizaciones = autorizacionService.findAutorizacionbyRut(empleado.getRut());
            PlanillaEntity planilla = rrhhService.crearPlanilla(empleado,relojes,justificaciones,autorizaciones);
            planillaService.guardarPlanilla(planilla);
        }
        return "redirect:/planilla/home";
    }
}
