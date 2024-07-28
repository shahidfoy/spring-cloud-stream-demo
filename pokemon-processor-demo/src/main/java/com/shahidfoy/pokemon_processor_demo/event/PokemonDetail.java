package com.shahidfoy.pokemon_processor_demo.event;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PokemonDetail {

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
    private String referenceId;
}
