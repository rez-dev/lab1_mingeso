package edu.mtisw.lab1_mingeso.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.mtisw.lab1_mingeso.entities.RelojEntity;
import edu.mtisw.lab1_mingeso.repositories.RelojRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Service
public class RelojService {
    @Autowired
    RelojRepository relojRepository;

    public void guardarReloj(RelojEntity reloj) {
        relojRepository.save(reloj);
    }

    public void eliminarTodosLosRelojes() {
        relojRepository.deleteAll();
    }

    private String folder = "cargas/relojes/";
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

    public void subirReloj(String data){
        String[] datos = data.split(";");
        RelojEntity relojEntity = new RelojEntity(null, datos[0],datos[1],datos[2]);
        relojRepository.save(relojEntity);
    }

    public String obtenerReloj() throws IOException{
        FileReader fileReader = new FileReader("cargas/relojes/DATA.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String data = "";
        String i;
        while((i = bufferedReader.readLine()) != null){
            subirReloj(i);
        }
        bufferedReader.close();
        fileReader.close();
        return data;
    }

    public ArrayList<RelojEntity> obtenerRelojPorRut(String rut) {
        return (ArrayList<RelojEntity>) relojRepository.findAllRelojbyRut(rut);
    }
    



    
}
