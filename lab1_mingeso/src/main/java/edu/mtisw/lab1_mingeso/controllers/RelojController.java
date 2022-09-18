package edu.mtisw.lab1_mingeso.controllers;

import edu.mtisw.lab1_mingeso.services.RelojService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/reloj")
public class RelojController {
    @Autowired//Se le dice a spring que se le va a inyectar carga de servicio
    private RelojService relojService;

    @GetMapping("/subirReloj")
    public String subirReloj(){
        return "relojes/subirReloj";
    }

    @PostMapping("/cargarReloj")
    public String carga(@RequestParam("archivos") MultipartFile file, RedirectAttributes ms){
        relojService.save(file);
        ms.addFlashAttribute("mensaje", "Archivo guardado correctamente");
        return "redirect:/reloj/subirReloj";
    }
}
