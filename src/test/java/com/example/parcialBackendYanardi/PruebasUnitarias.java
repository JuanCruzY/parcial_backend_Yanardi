package com.example.parcialBackendYanardi;

import com.example.parcialBackendYanardi.DTO.DtoAdnInput;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class PruebasUnitarias {

    @Autowired
    private Validator validator;

    private DtoAdnInput dtoAdnInput;

    @BeforeEach
    public void setUp() {
        dtoAdnInput = new DtoAdnInput();
    }

    @Test
    public void testEmptyArray() {
        dtoAdnInput.setDna(new String[]{});
        Set<ConstraintViolation<DtoAdnInput>> violations = validator.validate(dtoAdnInput);
        assertFalse(violations.isEmpty(), "No hay violaciones de validación.");
        assertEquals("El array de ADN no debe estar vacío o null.", violations.iterator().next().getMessage());
    }
    @Test
    public void testNonSquareArray() {
        dtoAdnInput.setDna(new String[]{"AT", "GCTA"});
        Set<ConstraintViolation<DtoAdnInput>> violations = validator.validate(dtoAdnInput);
        assertFalse(violations.isEmpty(), "No hay violaciones de validación.");
        assertEquals("El array debe ser de tamaño NxN.", violations.iterator().next().getMessage());
    }

    @Test
    public void testArrayWithNumbers() {
        dtoAdnInput.setDna(new String[]{"A1CG", "ATCG", "ATCG", "CGAT"});
        Set<ConstraintViolation<DtoAdnInput>> violations = validator.validate(dtoAdnInput);
        assertFalse(violations.isEmpty(), "No hay violaciones de validación.");
        assertEquals("El array de ADN solo debe contener caracteres válidos (A, T, C, G).", violations.iterator().next().getMessage());
    }

    @Test
    public void testNullArray() {
        dtoAdnInput.setDna(null);
        Set<ConstraintViolation<DtoAdnInput>> violations = validator.validate(dtoAdnInput);
        assertFalse(violations.isEmpty(), "No hay violaciones de validación.");
        assertEquals("El array de ADN no debe estar vacío o null.", violations.iterator().next().getMessage());
    }

    @Test
    public void testArrayOfNxnWithNulls() {
        dtoAdnInput.setDna(new String[]{null, null, null, null});
        Set<ConstraintViolation<DtoAdnInput>> violations = validator.validate(dtoAdnInput);
        assertFalse(violations.isEmpty(), "No hay violaciones de validación.");
        assertEquals("El array de ADN no debe estar compuesto solo por valores null.", violations.iterator().next().getMessage());
    }

    @Test
    public void testArrayWithInvalidCharacters() {
        dtoAdnInput.setDna(new String[]{"ABCD", "ATCG", "ATCG", "CGAT"});
        Set<ConstraintViolation<DtoAdnInput>> violations = validator.validate(dtoAdnInput);
        assertFalse(violations.isEmpty(), "No hay violaciones de validación.");
        assertEquals("El array de ADN solo debe contener caracteres válidos (A, T, C, G).", violations.iterator().next().getMessage());
    }

}
