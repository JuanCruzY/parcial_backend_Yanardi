package com.example.parcialBackendYanardi.Servicios;

import com.example.parcialBackendYanardi.Entidades.ADN;
import com.example.parcialBackendYanardi.Repositorios.ADNRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ADNServicio{

    @Autowired
    private ADNRepositorio adnRepositorio;

    public boolean analizarAdn (String[] adn){
        //String adnSequence = String.join(",", adn);
        Optional<ADN> adnExistente = Optional.ofNullable(adnRepositorio.findByAdn(adn));
        if (adnExistente.isPresent()){
            return adnExistente.get().isIndicadorMutante();
        }

        boolean indicMutante = isMutant(adn);
        ADN newAdn = ADN.builder().adn(adn).indicadorMutante(indicMutante).build();
        adnRepositorio.save(newAdn);
        return indicMutante;
    }

    public boolean isMutant(String[] dna){
        List<String> dnaFCD = new ArrayList<>();
        int longitud = dna.length;
        String dnaMutantA = "AAAA";
        String dnaMutantC = "CCCC";
        String dnaMutantT = "TTTT";
        String dnaMutantG = "GGGG";

        //Cargo todas las combinaciones posibles en cada fila.
        for (int i = 0; i < longitud; i++) {
            for (int j = 0; j < longitud - 3; j++) {
                StringBuilder fila = new StringBuilder();
                for (int k = j; k < j + 4; k++) {
                    fila.append(dna[i].charAt(k));
                }
                dnaFCD.add(fila.toString());
            }
        }

        //Cargo todas las combinaciones posibles de cada columna.
        for (int i = 0; i < longitud; i++){
            for (int j = 0; j < longitud - 3; j++) {
                StringBuilder columna = new StringBuilder();
                for (int k = j; k < j + 4; k++) {
                    columna.append(dna[k].charAt(i));
                }
                dnaFCD.add(columna.toString());
            }
        }

        //Cargo todas las combinaciones posibles de la diagonal principal.
        for (int j = 0; j < longitud - 3; j++) {
            StringBuilder diagonal = new StringBuilder();
            for (int k = j; k < j + 4; k++) {
                diagonal.append(dna[k].charAt(k));
            }
            dnaFCD.add(diagonal.toString());
        }

        //Cargo todas las combinaciones posibles de las diagonales secundarias.
        int aux = 1;
        for (int i = 1; i < (longitud - 3); i++) {
            for (int j = aux; j < (longitud - 3); j++) {
                StringBuilder diagonalsDown = new StringBuilder();
                StringBuilder diagonalsRight = new StringBuilder();
                for (int k = j; k < j + 4; k++) {
                    diagonalsDown.append(dna[k].charAt(k - i));
                    diagonalsRight.append(dna[k - i].charAt(k));
                }
                dnaFCD.add(diagonalsDown.toString());
                dnaFCD.add(diagonalsRight.toString());
            }
            aux++;
        }

        //Cargo todas las combinaciones posibles de las contra diagonales secundarias.
        aux = 1;
        int longAux = longitud;
        for (int i = 1; i < longitud - 3; i++) {
            for (int j = aux; j < longitud - 3; j++) {
                StringBuilder contraDiagonalsLeft = new StringBuilder();
                StringBuilder contraDiagonalsDown = new StringBuilder();
                for (int k = j; k < j + 4; k++) {
                    contraDiagonalsLeft.append(dna[k - i].charAt((longitud-1) - k));
                    contraDiagonalsDown.append(dna[k].charAt(longAux - k));
                }
                dnaFCD.add(contraDiagonalsLeft.toString());
                dnaFCD.add(contraDiagonalsDown.toString());
            }
            longAux++;
            aux++;
        }

        //Cargo todas las combinaciones posibles de la contradiagonal principal,
        for (int j = 0; j < longitud - 3; j++) {
            StringBuilder contraDiagonal = new StringBuilder();
            for (int k = j; k < j + 4; k++) {
                contraDiagonal.append(dna[k].charAt((longitud-1) - k));
            }
            dnaFCD.add(contraDiagonal.toString());
        }

        // Contadores atómicos para contar las mutaciones
        AtomicInteger counterA = new AtomicInteger(0);
        AtomicInteger counterC = new AtomicInteger(0);
        AtomicInteger counterG = new AtomicInteger(0);
        AtomicInteger counterT = new AtomicInteger(0);

        // Contar mutaciones en paralelo
        dnaFCD.parallelStream().forEach(adn -> {
            if (adn.contains(dnaMutantA)) {
                counterA.incrementAndGet();
            } else if (adn.contains(dnaMutantC)) {
                counterC.incrementAndGet();
            } else if (adn.contains(dnaMutantG)) {
                counterG.incrementAndGet();
            } else if (adn.contains(dnaMutantT)) {
                counterT.incrementAndGet();
            }
        });

        // Verificar si es mutante
        boolean mutantIndicator = (
                (counterA.get() > 0 && counterC.get() > 0) ||
                        (counterA.get() > 0 && counterG.get() > 0) ||
                        (counterA.get() > 0 && counterT.get() > 0) ||
                        (counterC.get() > 0 && counterG.get() > 0) ||
                        (counterC.get() > 0 && counterT.get() > 0) ||
                        (counterG.get() > 0 && counterT.get() > 0)
        );

        return mutantIndicator;
    }
}
