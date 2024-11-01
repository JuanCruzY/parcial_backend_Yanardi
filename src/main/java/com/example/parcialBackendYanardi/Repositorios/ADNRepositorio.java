package com.example.parcialBackendYanardi.Repositorios;

import com.example.parcialBackendYanardi.Entidades.ADN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ADNRepositorio extends JpaRepository<ADN, Long> {

    ADN findByAdn(String[] adn);
    long countByIndicadorMutante(boolean indicadorMutante);
}
