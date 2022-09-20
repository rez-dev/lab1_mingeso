package edu.mtisw.lab1_mingeso.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.mtisw.lab1_mingeso.entities.JustificacionEntity;
import edu.mtisw.lab1_mingeso.repositories.JustificacionRepository;

@Service
public class JustificacionService {
    @Autowired
    JustificacionRepository justificacionRepository;

    public ArrayList<JustificacionEntity> obtenerJustificaciones() {
        return (ArrayList<JustificacionEntity>) justificacionRepository.findAll();
    }

    public void guardarJustificacion(JustificacionEntity justificacion) {
        justificacionRepository.save(justificacion);
    }

    private String folder = "cargas/justificaciones/";
    private final Logger logg = LoggerFactory.getLogger(RelojService.class);

    public String save(MultipartFile file){
        if (!(file.isEmpty())){
            try {
                byte [] bytes = file.getBytes();
                Path path = Paths.get(folder+file.getOriginalFilename());
                Files.write(path, bytes);
                logg.info("Archivo Guardado");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return "Archivo guardado correctamente";
    }

    public ArrayList<JustificacionEntity> findJustificacionbyRutAndFecha(String rut, String fecha){
        return (ArrayList<JustificacionEntity>) justificacionRepository.findJustificacionbyRutAndFecha(rut, fecha);
    }
}
