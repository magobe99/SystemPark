package com.Sistempark.SenaSP.domian;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileReader;

public class UploadFile {

    @Autowired
    Servicio lstServicio;

    public List<Servicio> guardarFile(MultipartFile path) throws IOException, CsvValidationException {
        List<Servicio> lstServicio = new ArrayList<>();

        String ruta = "C:/cargas/" + path.getOriginalFilename();
        File localFile = new File(ruta);
        path.transferTo(localFile);

        CSVReader csvReader = new CSVReader(new FileReader(ruta));
        String[] fila = null;
        csvReader.readNext();
        while ((fila = csvReader.readNext()) != null) {
            Servicio s = new Servicio();
            s.setCod_servicio(Integer.parseInt(fila[0]));
            s.setNom_servicio(fila[1]);
            s.setPrecio_servicio(Integer.parseInt(fila[2]));
            s.setTiempo_servicio(fila[3]);
            lstServicio.add(s);
        }
        csvReader.close();
        return lstServicio;
    }
}
