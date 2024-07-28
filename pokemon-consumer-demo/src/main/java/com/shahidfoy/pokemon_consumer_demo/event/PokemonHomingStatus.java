package com.shahidfoy.pokemon_consumer_demo.event;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PokemonHomingStatus {

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
    private HomingStatus homingStatus;

    public enum HomingStatus {
        DAYCARE,
        POKEBOX
    }
}
