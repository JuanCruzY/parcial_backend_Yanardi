package com.example.parcialBackendYanardi.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Objects;

public class AdnValidator implements ConstraintValidator<ValidAdn, String[]> {

    @Override
    public void initialize(ValidAdn constraintAnnotation) {
    }

    @Override
    public boolean isValid(String[] dna, ConstraintValidatorContext context) {
        boolean isValid = true;

        if (dna == null || dna.length == 0) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("El array de ADN no debe estar vacío o null.")
                    .addConstraintViolation();
            isValid = false;
        }

        int n = dna != null ? dna.length : 0;
        for (String fila : dna) {
            if (fila == null || fila.length() != n) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("El array debe ser de tamaño NxN.")
                        .addConstraintViolation();
                isValid = false;
            }
        }

        if (n < 4) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("El array debe ser de tamaño mayor o igual a 4x4.")
                    .addConstraintViolation();
            isValid = false;
        }

        for (String fila : dna) {
            if (fila != null && !fila.matches("[ATCG]+")) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("El array de ADN solo debe contener caracteres válidos (A, T, C, G).")
                        .addConstraintViolation();
                isValid = false;
            }
        }

        boolean allNulls = dna != null && Arrays.stream(dna).allMatch(Objects::isNull);
        if (allNulls) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("El array de ADN no debe estar compuesto solo por valores null.")
                    .addConstraintViolation();
            isValid = false;
        }

        // Retorna false si alguna condición de validación fue incumplida.
        return isValid;
    }
}
