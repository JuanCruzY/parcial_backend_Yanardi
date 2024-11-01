package com.example.parcialBackendYanardi.Entidades;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.io.Serializable;

@Audited
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Table(name = "ADN")
public class ADN implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "adn")
    private String[] adn;

    @Column(name = "IndicadorMutante")
    private boolean indicadorMutante;
}
