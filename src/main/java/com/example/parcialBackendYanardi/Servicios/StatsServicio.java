package com.example.parcialBackendYanardi.Servicios;

import com.example.parcialBackendYanardi.DTO.DTOStats;
import com.example.parcialBackendYanardi.Repositorios.ADNRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsServicio {

    @Autowired
    private ADNRepositorio adnRepositorio;

    public DTOStats obtenerEstadisticas(){
        long mutantes = adnRepositorio.countByIndicadorMutante(true);
        long humanos = adnRepositorio.countByIndicadorMutante(false);
        double ratio = (double) mutantes/humanos;
        return new DTOStats(mutantes, humanos, ratio);
    }
}
