package com.example.parcialBackendYanardi;

import com.example.parcialBackendYanardi.DTO.DtoAdnInput;
import com.example.parcialBackendYanardi.Servicios.ADNServicio;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // Agrega esta anotación para cargar el contexto de Spring
public class SimpleTest {

    @Autowired
    private Validator validator; // Inyección del validador

    private DtoAdnInput dtoAdnInput;


    @BeforeEach
    public void setUp() {
        dtoAdnInput = new DtoAdnInput();
    }

    @Test
    void testCondition() {
        int a = 5;
        int b = 3;
        // Verifica que 'a' sea mayor que 'b'
        assertTrue(a > b, "a debería ser mayor que b");
    }

    @Test
    public void testEmptyArray() {
        dtoAdnInput.setDna(new String[]{});
        Set<ConstraintViolation<DtoAdnInput>> violations = validator.validate(dtoAdnInput);

        boolean found = violations.stream()
                .anyMatch(v -> v.getMessage().equals("El array de ADN no debe estar vacío."));

        assertTrue(found, "Se esperaba una violación por un array de ADN vacío.");
    }

    @Test
    public void testNonSquareArray() {
        dtoAdnInput.setDna(new String[]{"AT", "GCTA"});
        Set<ConstraintViolation<DtoAdnInput>> violations = validator.validate(dtoAdnInput);

        boolean found = violations.stream()
                .anyMatch(v -> v.getMessage().equals("El array debe ser de tamaño NxN."));

        assertTrue(found, "Se esperaba una violación por un array no cuadrado.");
    }

    @Test
    public void testArrayWithNumbers() {
        dtoAdnInput.setDna(new String[]{"A1CG", "ATCG", "ATCG", "CGAT"});
        Set<ConstraintViolation<DtoAdnInput>> violations = validator.validate(dtoAdnInput);

        boolean found = violations.stream()
                .anyMatch(v -> v.getMessage().equals("El array de ADN solo debe contener caracteres válidos (A, T, C, G)."));

        assertTrue(found, "Se esperaba una violación por un array de ADN que contiene números.");
    }


    @Test
    public void testNullArray() {
        dtoAdnInput.setDna(null);
        Set<ConstraintViolation<DtoAdnInput>> violations = validator.validate(dtoAdnInput);

        boolean found = violations.stream()
                .anyMatch(v -> v.getMessage().equals("El array de ADN no debe ser null."));

        assertTrue(found, "Se esperaba una violación por un array de ADN que es null.");
    }


    @Test
    public void testArrayOfNxnWithNulls() {
        dtoAdnInput.setDna(new String[]{null, null, null, null});
        Set<ConstraintViolation<DtoAdnInput>> violations = validator.validate(dtoAdnInput);

        boolean found = violations.stream()
                .anyMatch(v -> v.getMessage().equals("El array de ADN no debe estar compuesto solo por valores null."));

        assertTrue(found, "Se esperaba una violación por un array de ADN compuesto solo por valores null.");
    }

    @Test
    public void testArrayWithInvalidCharacters() {
        dtoAdnInput.setDna(new String[]{"ABCD", "ATCG", "ATCG", "CGAT"});
        Set<ConstraintViolation<DtoAdnInput>> violations = validator.validate(dtoAdnInput);

        boolean found = violations.stream()
                .anyMatch(v -> v.getMessage().equals("El array de ADN solo debe contener caracteres válidos (A, T, C, G)."));

        assertTrue(found, "Se esperaba una violación por un array de ADN que contiene caracteres inválidos.");
    }


    @Autowired
    private ADNServicio adnServicio;

    @Test public void mutante(){
        String[] adn = {"AAAA", "TTTT", "ATCG", "CGAT"};

        boolean booleano = adnServicio.isMutant(adn);

        assertTrue(booleano);
    }

    @Test public void noMutante(){
        String[] adn = {"AAAC", "TGTT", "ATCG", "CGAT"};

        boolean booleano = adnServicio.isMutant(adn);

        assertFalse(booleano);
    }

}

