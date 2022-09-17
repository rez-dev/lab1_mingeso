package edu.mtisw.lab1_mingeso.controllers;

import edu.mtisw.lab1_mingeso.services.UploadRelojService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/upload")
public class UploadRelojController {
    @Autowired//Se le dice a spring que se le va a inyectar carga de servicio
    private UploadRelojService uploadRelojService;

    @GetMapping("/subirData")
    public String home(){
        return "home";
    }

    @PostMapping("/cargarArchivo")
    public String carga(@RequestParam("archivos") MultipartFile file, RedirectAttributes ms){
        uploadRelojService.save(file);
        ms.addFlashAttribute("mensaje", "Archivo guardado correctamente");
        return "redirect:/upload/subirData";
    }
}
