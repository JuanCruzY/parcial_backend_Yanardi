package com.example.parcialBackendYanardi.Controladores;

import com.example.parcialBackendYanardi.DTO.DtoAdnInput;
import com.example.parcialBackendYanardi.Entidades.ADN;
import com.example.parcialBackendYanardi.Servicios.ADNServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "parcial/backend/adn")
public class ADNControlador{

    @Autowired
    protected ADNServicio servicio;

    @PostMapping("mutant")
    public ResponseEntity<?> analizarAdn(@Valid @RequestBody DtoAdnInput adnInput) {
        try {
            boolean isMutant = servicio.analizarAdn(adnInput.getDna());
            if (isMutant) {
                return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"Es un mutante\"}");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"message\":\"No es un mutante\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }
}
