package edu.mtisw.lab1_mingeso.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.mtisw.lab1_mingeso.entities.JustificacionEntity;
import edu.mtisw.lab1_mingeso.services.JustificacionService;

@Controller
@RequestMapping("/justificacion")
public class JustificacionController {

    @Autowired
    JustificacionService justificacionService;

    @GetMapping("/home")
    public String home(){
        return "justificaciones/homeJustificacion";
    }

    @GetMapping("/listar")
    public String listar(Model model){
        ArrayList<JustificacionEntity>justificaciones=justificacionService.obtenerJustificaciones();
        System.out.println(justificaciones);
        model.addAttribute("justificaciones",justificaciones);
        return "justificaciones/listadoJustificacion";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model){
        model.addAttribute("justificacion",new JustificacionEntity());
        return "justificaciones/formJustificacion";
    }


    @PostMapping("/guardar")
    public String guardar(JustificacionEntity justificacion, RedirectAttributes attributes){
        justificacionService.guardarJustificacion(justificacion);
        attributes.addFlashAttribute("mensaje","Justificacion guardada");
        return "redirect:/justificacion/listar";
    }

    @GetMapping("/subirJustificacion")
    public String subirJustificacion(Model model){
        return "justificaciones/subirJustificacion";
    }

    @PostMapping("/cargarJustificacion")
    public String carga(@RequestParam("archivos") MultipartFile file, RedirectAttributes ms){
        justificacionService.save(file);
        ms.addFlashAttribute("mensaje", "Archivo guardado correctamente");
        return "redirect:/justificacion/subirJustificacion";
    }
}