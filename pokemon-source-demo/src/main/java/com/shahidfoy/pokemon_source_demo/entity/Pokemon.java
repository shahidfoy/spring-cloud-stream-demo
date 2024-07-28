package com.shahidfoy.pokemon_source_demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "POKEMON_CAPTURED")
public class Pokemon {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type1;
    private String type2;
    private Integer total;
    private Integer hp;
    private Integer attack;
    private Integer defense;
    private Integer specialAttack;
    private Integer specialDefense;
    private Integer speed;
    private Integer generations;
    private boolean legendary;
    private boolean processingStatus;
    private String referenceId;
}
