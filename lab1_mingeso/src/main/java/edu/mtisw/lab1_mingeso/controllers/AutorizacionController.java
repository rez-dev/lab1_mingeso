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

import edu.mtisw.lab1_mingeso.entities.AutorizacionEntity;
import edu.mtisw.lab1_mingeso.services.AutorizacionService;

@Controller
@RequestMapping("/autorizacion")
public class AutorizacionController {
    @Autowired
    AutorizacionService autorizacionService;

    @GetMapping("/listar")
    public String listar(Model model){
        ArrayList<AutorizacionEntity>autorizaciones=autorizacionService.obtenerAutorizaciones();
        //System.out.println(autorizaciones);
        model.addAttribute("autorizaciones",autorizaciones);
        return "autorizaciones/listadoAutorizacion";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model){
        model.addAttribute("autorizacion",new AutorizacionEntity());
        return "autorizaciones/formAutorizacion";
    }

    @PostMapping("/guardar")
    public String guardar(AutorizacionEntity autorizacion, RedirectAttributes attributes){
        autorizacionService.guardarAutorizacion(autorizacion);
        attributes.addFlashAttribute("mensaje","Autorizacion guardada");
        return "redirect:/autorizacion/listar";
    }

    @GetMapping("/subirAutorizacion")
    public String subirAutorizacion(Model model){
        return "autorizaciones/subirAutorizacion";
    }

    @PostMapping("/cargarAutorizacion")
    public String carga(@RequestParam("archivos") MultipartFile file, RedirectAttributes ms){
        autorizacionService.save(file);
        ms.addFlashAttribute("mensaje", "Archivo guardado correctamente");
        return "redirect:/autorizacion/subirAutorizacion";
    }
}
