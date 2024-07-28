package com.shahidfoy.pokemon_source_demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PokemonRequest {

    @NonNull
    private String name;
    @NonNull
    private String type1;
    private String type2;
    @NonNull
    private Integer total;
    private Integer hp;
    private Integer attack;
    private Integer defense;
    private Integer specialAttack;
    private Integer specialDefense;
    private Integer speed;
    private Integer generations;
    private boolean legendary;
}
