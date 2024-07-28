package com.shahidfoy.pokemon_source_demo.event;

import java.util.List;

public record NewPokemonEvent(List<PokemonDetail> pokemonDetailList) {
}
