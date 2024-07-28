package com.shahidfoy.pokemon_source_demo.service;

import com.shahidfoy.pokemon_source_demo.entity.Pokemon;
import com.shahidfoy.pokemon_source_demo.event.NewPokemonEvent;
import com.shahidfoy.pokemon_source_demo.event.PokemonDetail;
import com.shahidfoy.pokemon_source_demo.repository.PokemonCapturedRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class PokemonEventPublisherService {

    private PokemonCapturedRepository pokemonCapturedRepository;

    public Optional<NewPokemonEvent> publishEvent() {

        List<Pokemon> pokemonList = pokemonCapturedRepository.findByProcessingStatus(false);
        List<PokemonDetail> pokemonDetails = pokemonList.stream().map(pokemon -> {
            PokemonDetail pokemonDetail = PokemonDetail.builder().build();
            BeanUtils.copyProperties(pokemon, pokemonDetail);
            pokemon.setProcessingStatus(true);
            return pokemonDetail;
        }).toList();

        log.info("#### Checked for new pokemon request, available records to process: {}", pokemonList.size());
        Optional<NewPokemonEvent> eventToPublish = Optional.empty();

        if (!pokemonDetails.isEmpty()) {
            log.info("******* Updating pokemon status as processed ***********");
            NewPokemonEvent newPokemonEvent = new NewPokemonEvent(pokemonDetails);
            pokemonCapturedRepository.saveAll(pokemonList);
            eventToPublish = Optional.of(newPokemonEvent);
        }
        return eventToPublish;
    }
}
