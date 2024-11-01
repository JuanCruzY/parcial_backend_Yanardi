package com.example.parcialBackendYanardi.DTO;

import com.example.parcialBackendYanardi.validators.ValidAdn;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class DtoAdnInput {

    @ValidAdn
    private String[] dna;
}
