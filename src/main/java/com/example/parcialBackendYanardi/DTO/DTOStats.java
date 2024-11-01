package com.example.parcialBackendYanardi.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class DTOStats {

    private long count_mutant_dna;

    private long count_human_dna;

    private double ratio;
}
