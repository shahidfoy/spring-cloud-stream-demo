package com.shahidfoy.pokemon_processor_demo.event;

import java.util.List;

public record HomingPokemonEvent(List<PokemonHomingStatus> pokemonHomingStatusList) {
}
