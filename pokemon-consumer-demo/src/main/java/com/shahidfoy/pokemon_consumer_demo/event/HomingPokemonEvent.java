package com.shahidfoy.pokemon_consumer_demo.event;

import java.util.List;

public record HomingPokemonEvent(List<PokemonHomingStatus> pokemonHomingStatusList) {
}
