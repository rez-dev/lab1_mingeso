package edu.mtisw.lab1_mingeso.controllers;

import edu.mtisw.lab1_mingeso.entities.AutorizacionEntity;
import edu.mtisw.lab1_mingeso.entities.EmpleadoEntity;
import edu.mtisw.lab1_mingeso.entities.JustificacionEntity;
import edu.mtisw.lab1_mingeso.entities.RelojEntity;
import edu.mtisw.lab1_mingeso.services.EmpleadoService;
import edu.mtisw.lab1_mingeso.services.RRHHService;
import edu.mtisw.lab1_mingeso.services.RelojService;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

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

@Controller
@RequestMapping("/reloj")
public class RelojController {
    @Autowired//Se le dice a spring que se le va a inyectar carga de servicio
    private RelojService relojService;
    @Autowired
    private EmpleadoService empleadoService;
    @Autowired
    private RRHHService rrhhService;

    @GetMapping("/subirReloj")
    public String subirReloj(){
        return "relojes/subirReloj";
    }

    @PostMapping("/cargarReloj")
    public String carga(@RequestParam("archivos") MultipartFile file, RedirectAttributes ms) throws IOException{
        relojService.save(file);
        ms.addFlashAttribute("mensaje", "Archivo guardado correctamente");
        relojService.eliminarTodosLosRelojes();
        relojService.obtenerReloj();
        return "redirect:/reloj/subirReloj";
    }

    @GetMapping("/listar/{rut}")
    public String listar(@PathVariable String rut, Model model){
        ArrayList<RelojEntity>relojes = relojService.obtenerRelojPorRut(rut);
        // System.out.println(relojes);
        model.addAttribute("relojes",relojes);
        return "relojes/listadoReloj";
    }

    @GetMapping("/calcularHoras/{rut}")
    public String calcularHoras(@PathVariable String rut, Model model) throws ParseException{
        //ArrayList<RelojEntity>relojes = relojService.obtenerRelojPorRut(rut);
        EmpleadoEntity empleado = empleadoService.obtenerEmpleadoPorRut(rut);
        // int sueldoFijoMensual = rrhhService.calcularSueldoFijoMensual(empleado);
        // int descuentoAtrasos = rrhhService.calcularDescuentosAtrasos(empleado);
        // int montoHorasExtras = rrhhService.calcularMontoHorasExtras(empleado);
        // int montoAgnosServicio = rrhhService.calcularMontoAgnosServicio(empleado);
        ArrayList<RelojEntity> relojes = relojService.obtenerRelojPorRut(empleado.getRut());
        ArrayList<JustificacionEntity> justificaciones = new ArrayList<JustificacionEntity>();
        ArrayList<AutorizacionEntity> autorizaciones = new ArrayList<AutorizacionEntity>();
        int sueldoFinal = rrhhService.calcularSueldoFinal(empleado,relojes,justificaciones,autorizaciones);
        // System.out.println(sueldoFijoMensual);
        // System.out.println(descuentoAtrasos);
        // System.out.println(montoHorasExtras);
        // System.out.println(montoAgnosServicio);
        System.out.println("Sueldo Final " + sueldoFinal);
        return "redirect:/reloj/subirReloj";
    }
}
