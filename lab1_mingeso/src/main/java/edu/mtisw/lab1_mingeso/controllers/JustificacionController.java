package edu.mtisw.lab1_mingeso.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/listar")
    public String listar(Model model){
        ArrayList<JustificacionEntity>justificaciones=justificacionService.obtenerJustificaciones();
        System.out.println(justificaciones);
        model.addAttribute("justificaciones",justificaciones);
        return "index";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model){
        model.addAttribute("justificacion",new JustificacionEntity());
        return "formJustificacion";
    }

    @PostMapping("/guardar")
    public String crear(JustificacionEntity justificacion, @RequestParam("archivos") MultipartFile file, RedirectAttributes ms){
        justificacionService.guardarJustificacion(justificacion);
        justificacionService.save(file);
        ms.addFlashAttribute("mensaje", "Archivo guardado correctamente");
        return "redirect:/justificacion/listar";
    }

    // @GetMapping("/editar/{id}")
    // public String editar(@PathVariable int id, Model model){
    //     Optional<JustificacionEntity> justificacion=justificacionService.obtenerPorId(id);
    //     model.addAttribute("justificacion",justificacion);
    //     return "form";

    // }

    // @GetMapping("/eliminar/{id}")
    // public String eliminar(@PathVariable int id){
    //     justificacionService.eliminarJustificacion(id);
    //     return "redirect:/justificacion/listar";
    // }
}