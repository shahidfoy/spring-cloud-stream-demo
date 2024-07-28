package com.shahidfoy.pokemon_source_demo.service;

import com.shahidfoy.pokemon_source_demo.dto.PokemonRequest;
import com.shahidfoy.pokemon_source_demo.entity.Pokemon;
import com.shahidfoy.pokemon_source_demo.repository.PokemonCapturedRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class PokemonService {

    private PokemonCapturedRepository pokemonCapturedRepository;

    public Optional<String> saveCapturedPokemon(PokemonRequest pokemonRequest) {
        try {
            Pokemon pokemon = new Pokemon();
            BeanUtils.copyProperties(pokemonRequest, pokemon);
            pokemon.setProcessingStatus(false);
            pokemon.setReferenceId(RandomStringUtils.random(10, true, true));
            Pokemon pokemonSaved = pokemonCapturedRepository.save(pokemon);
            return Optional.of(pokemonSaved.getReferenceId());
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return Optional.empty();
        }
    }
}
