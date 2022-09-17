package edu.mtisw.lab1_mingeso.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadRelojService {
    private String folder = "cargas/relojes/";
    private final Logger logg = LoggerFactory.getLogger(UploadRelojService.class);

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
