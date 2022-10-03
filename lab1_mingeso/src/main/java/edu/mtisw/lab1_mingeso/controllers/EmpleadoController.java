package edu.mtisw.lab1_mingeso.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.mtisw.lab1_mingeso.entities.EmpleadoEntity;
import edu.mtisw.lab1_mingeso.services.EmpleadoService;

@Controller
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;

    @GetMapping("/nuevo")
    public String nuevo(Model model){
        model.addAttribute("empleado",new EmpleadoEntity());
        return "empleados/formEmpleado";
    }

    @PostMapping("/guardar")
    public String guardar(EmpleadoEntity empleado, RedirectAttributes attributes){
        empleadoService.guardarEmpleado(empleado);
        attributes.addFlashAttribute("mensaje","Empleado guardado");
        return "redirect:/planilla/home";
    }
    
}
