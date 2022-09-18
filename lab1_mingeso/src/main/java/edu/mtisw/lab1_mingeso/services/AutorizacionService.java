package edu.mtisw.lab1_mingeso.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.mtisw.lab1_mingeso.entities.AutorizacionEntity;
import edu.mtisw.lab1_mingeso.repositories.AutorizacionRepository;

@Service
public class AutorizacionService {
    @Autowired
    AutorizacionRepository autorizacionRepository;

    public ArrayList<AutorizacionEntity> obtenerAutorizaciones() {
        return (ArrayList<AutorizacionEntity>) autorizacionRepository.findAll();
    }

    public void guardarAutorizacion(AutorizacionEntity autorizacion) {
        autorizacionRepository.save(autorizacion);
    }

    private String folder = "cargas/autorizaciones/";
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
}
