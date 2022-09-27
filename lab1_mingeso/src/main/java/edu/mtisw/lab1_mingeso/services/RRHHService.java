package edu.mtisw.lab1_mingeso.services;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mtisw.lab1_mingeso.entities.AutorizacionEntity;
import edu.mtisw.lab1_mingeso.entities.EmpleadoEntity;
import edu.mtisw.lab1_mingeso.entities.JustificacionEntity;
import edu.mtisw.lab1_mingeso.entities.PlanillaEntity;
import edu.mtisw.lab1_mingeso.entities.RelojEntity;
@Service
public class RRHHService {
    @Autowired
    RelojService relojService;
    @Autowired
    JustificacionService justificacionService;
    @Autowired
    AutorizacionService autorizacionService;

    //Calcula el sueldo fijo mensual de un empleado
    public int calcularSueldoFijoMensual(EmpleadoEntity empleado){
        if(empleado.getId_categoria() == 1){
            return 1700000;}
        else if(empleado.getId_categoria() == 2){
            return 1200000;}
        else if(empleado.getId_categoria() == 3){
            return 800000;}
        else {
            return 0;}
    }

    //Calcula el monto por hora extra del empleado
    public int getMontoPorHoraExtra(EmpleadoEntity empleado){
        if(empleado.getId_categoria() == 1){
            return 25000;}
        else if(empleado.getId_categoria() == 2){
            return 20000;}
        else if(empleado.getId_categoria() == 3){
            return 10000;}
        else {
            return 0;}
    }

    //Calcular agnos de servicio
    public int calcularAgnosDeServicio(EmpleadoEntity empleado) throws ParseException{
        LocalDateTime now = LocalDateTime.now();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaIngreso = formatter.parse(empleado.getFecha_ingreso_empresa());
        LocalDate fechaIngresoLocal = fechaIngreso.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        Period periodo = Period.between(fechaIngresoLocal, now.toLocalDate());
        int agnosServicio = periodo.getYears();

        if(agnosServicio > 100){
            agnosServicio = 0;
        }
        return agnosServicio;
    }

    //Calcula el bono por agnos de servicio de un empleado
    public int calcularMontoAgnosServicio(EmpleadoEntity empleado) throws ParseException{
        int agnosServicio = calcularAgnosDeServicio(empleado);

        if(agnosServicio < 5){
            return 0;}
        else if(agnosServicio >= 5 && agnosServicio < 10){
            return (int) (0.05 * calcularSueldoFijoMensual(empleado));}
        else if(agnosServicio >= 10 && agnosServicio < 15){
            return (int) (0.08 * calcularSueldoFijoMensual(empleado));}
        else if(agnosServicio >= 15 && agnosServicio < 20){
            return (int) (0.11 * calcularSueldoFijoMensual(empleado));}
        else if(agnosServicio >= 20 && agnosServicio < 25){
            return (int) (0.14 * calcularSueldoFijoMensual(empleado));}
        else if(agnosServicio >= 25){
            return (int) (0.17 * calcularSueldoFijoMensual(empleado));}
        else{
            return 0;}
    }

    //Verificar atrasos
    public int calcularDescuentosAtrasos(EmpleadoEntity empleado) throws ParseException{
        String HORA_ENTRADA = "8:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date horaEntrada = simpleDateFormat.parse(HORA_ENTRADA);
        int montoDescuento = 0;

        ArrayList<RelojEntity> relojes = relojService.obtenerRelojPorRut(empleado.getRut());

        int totalRelojes = relojes.size();
        if(totalRelojes == 0){
            return montoDescuento;
        }
        for(int i = 0; i < totalRelojes; i = i + 2){
            Date horaReloj = simpleDateFormat.parse(relojes.get(i).getHora());
            long diferenciaMinutos = (horaReloj.getTime() - horaEntrada.getTime())/60000;

            if(diferenciaMinutos > 10 && diferenciaMinutos <= 25){
                montoDescuento = montoDescuento + (int)(0.01 * calcularSueldoFijoMensual(empleado));
                //System.out.println("Atraso de 10 a 25 minutos: " + relojes.get(i).getHora() +" "+ relojes.get(i).getFecha());
            }else if(diferenciaMinutos > 25 && diferenciaMinutos <= 45){
                //System.out.println("Atraso de 25 a 45 minutos " + relojes.get(i).getHora() +" "+ relojes.get(i).getFecha());
                montoDescuento = montoDescuento + (int)(0.03 * calcularSueldoFijoMensual(empleado));
            }else if(diferenciaMinutos > 45 && diferenciaMinutos <= 70){
                montoDescuento = montoDescuento + (int)(0.06 * calcularSueldoFijoMensual(empleado));
                //System.out.println("Atraso de 45 a 70 minutos " + relojes.get(i).getHora() +" "+ relojes.get(i).getFecha());
            }else if(diferenciaMinutos > 70){
                //System.out.println(empleado.getRut() +" "+ relojes.get(i).getFecha());
                //System.out.println("Atraso de mas de 70 minutos " + relojes.get(i).getHora() +" "+ relojes.get(i).getFecha());
                //System.out.println(empleado.getRut() +" "+ relojes.get(i).getFecha());

                final String viejoFormato = "yyyy/mm/dd";
                final String nuevoFormato = "yyyy-mm-dd";

                SimpleDateFormat formato = new SimpleDateFormat(viejoFormato);
                Date fecha = formato.parse(relojes.get(i).getFecha());
                formato.applyPattern(nuevoFormato);
                String fechaNuevoFormato = formato.format(fecha);
                //System.out.println(fechaNuevoFormato);

                ArrayList<JustificacionEntity> justificaciones= justificacionService.findJustificacionbyRutAndFecha(empleado.getRut(), fechaNuevoFormato);
                if(justificaciones.size() != 0){
                    if(justificaciones.get(0).getEstado() == 0){
                        montoDescuento = montoDescuento + (int)(0.15 * calcularSueldoFijoMensual(empleado));
                    }
                }else{
                    montoDescuento = montoDescuento + (int)(0.15 * calcularSueldoFijoMensual(empleado));}
            }
        }
        return montoDescuento;
    }


    public int calcularHorasExtras(EmpleadoEntity empleado) throws ParseException{
        int cantidadHorasExtras = 0;
        long cantidadMinutosExtras = 0;

        String HORA_SALIDA = "18:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date horaSalida = simpleDateFormat.parse(HORA_SALIDA);

        ArrayList<RelojEntity> relojes = relojService.obtenerRelojPorRut(empleado.getRut());
        int totalRelojes = relojes.size();
        if(totalRelojes == 0){
            return 0;
        }

        for(int i = 1; i < totalRelojes; i = i + 2){
            Date horaReloj = simpleDateFormat.parse(relojes.get(i).getHora());
            long diferenciaMinutos = (horaReloj.getTime() - horaSalida.getTime())/60000;

            if(diferenciaMinutos > 0){
                //System.out.println("Hora de salida: " + relojes.get(i).getHora() +" "+ relojes.get(i).getFecha());
                cantidadMinutosExtras = cantidadMinutosExtras + diferenciaMinutos;
            }
        }
        cantidadHorasExtras = (int) (cantidadMinutosExtras/60);

        ArrayList<AutorizacionEntity> autorizaciones= autorizacionService.findAutorizacionbyRut(empleado.getRut());
        if(autorizaciones.size() == 0){
            return 0;
        }
        if(autorizaciones.get(0).getEstado() == 0){
            cantidadHorasExtras = 0;
        }
        return cantidadHorasExtras;
    }

    public int calcularMontoHorasExtras(EmpleadoEntity empleado) throws ParseException{
        int montoHorasExtras;
        if(empleado.getId_categoria() == 1){
            montoHorasExtras = (25000 * calcularHorasExtras(empleado));}
        else if(empleado.getId_categoria() == 2){
            montoHorasExtras = (20000 * calcularHorasExtras(empleado));}
        else if(empleado.getId_categoria() == 3){
            montoHorasExtras = (10000 * calcularHorasExtras(empleado));}
        else{
            montoHorasExtras = 0;}
        return montoHorasExtras;
    }

    public int calcularSueldoBruto(EmpleadoEntity empleado) throws ParseException{
        int sueldoBruto = calcularSueldoFijoMensual(empleado) + calcularMontoAgnosServicio(empleado) 
        + calcularMontoHorasExtras(empleado) - calcularDescuentosAtrasos(empleado);
        return sueldoBruto;
    }

    public int calcularCotizacionPrevisional(EmpleadoEntity empleado) throws ParseException{
        int cotizacionPrevisional = (int) (0.1 * calcularSueldoBruto(empleado));
        return cotizacionPrevisional;
    }

    public int calcularCotizacionSalud(EmpleadoEntity empleado) throws ParseException{
        int cotizacionSalud = (int) (0.08 * calcularSueldoBruto(empleado));
        return cotizacionSalud;
    }

    public int calcularSueldoFinal(EmpleadoEntity empleado) throws ParseException{
        int sueldoFinal = calcularSueldoBruto(empleado) - calcularCotizacionPrevisional(empleado) 
        - calcularCotizacionSalud(empleado);
        return sueldoFinal;
    }

    public PlanillaEntity crearPlanilla(EmpleadoEntity empleado) throws ParseException{
        PlanillaEntity planilla = new PlanillaEntity();
        planilla.setRut_empleado_planilla(empleado.getRut());
        planilla.setNombre_completo(empleado.getNombres());
        planilla.setId_categoria_planilla(empleado.getId_categoria());
        planilla.setAgnos_servicio(calcularAgnosDeServicio(empleado));
        planilla.setSueldo_fijo_mensual(calcularSueldoFijoMensual(empleado));
        planilla.setMonto_bono_agnos_servicio(calcularMontoAgnosServicio(empleado));
        planilla.setMonto_bonos_horas_extras(calcularMontoHorasExtras(empleado));
        planilla.setMonto_descuentos(calcularDescuentosAtrasos(empleado));
        planilla.setSueldo_bruto(calcularSueldoBruto(empleado));
        planilla.setCotizacion_previsional(calcularCotizacionPrevisional(empleado));
        planilla.setCotizacio_salud(calcularCotizacionSalud(empleado));
        planilla.setMonto_sueldo_final(calcularSueldoFinal(empleado));
        return planilla;
    }
}

