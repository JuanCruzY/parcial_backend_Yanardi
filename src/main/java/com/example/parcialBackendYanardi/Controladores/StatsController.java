package com.example.parcialBackendYanardi.Controladores;

import com.example.parcialBackendYanardi.Servicios.StatsServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "parcial/backend/estadisticas")
public class StatsController {

    @Autowired
    protected StatsServicio servicio;

    @GetMapping("stats")
    public ResponseEntity<?> obtenerEstadisticas(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.obtenerEstadisticas());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }
}
