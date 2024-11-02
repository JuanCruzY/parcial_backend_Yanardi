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

        context.disableDefaultConstraintViolation();

        boolean isValid = true;

        if (dna == null) {
            context.buildConstraintViolationWithTemplate("El array de ADN no debe ser null.")
                    .addConstraintViolation();
            isValid = false;
        }else if (dna.length == 0) {
            context.buildConstraintViolationWithTemplate("El array de ADN no debe estar vacío.")
                    .addConstraintViolation();
            isValid = false;
        }

        if (dna != null) {
            int n = dna.length;

            if (n < 4) {
                context.buildConstraintViolationWithTemplate("El array debe ser de tamaño mayor o igual a 4x4.")
                        .addConstraintViolation();
                isValid = false;
            }

            for (String fila : dna) {
                if (fila == null || fila.length() != n) {
                    context.buildConstraintViolationWithTemplate("El array debe ser de tamaño NxN.")
                            .addConstraintViolation();
                    isValid = false;
                }
                if (fila != null && !fila.matches("[ATCG]+")) {
                    context.buildConstraintViolationWithTemplate("El array de ADN solo debe contener caracteres válidos (A, T, C, G).")
                            .addConstraintViolation();
                    isValid = false;
                }
            }

            boolean allNulls = Arrays.stream(dna).allMatch(Objects::isNull);
            if (allNulls) {
                context.buildConstraintViolationWithTemplate("El array de ADN no debe estar compuesto solo por valores null.")
                        .addConstraintViolation();
                isValid = false;
            }
        }

        return isValid;
    }

}
