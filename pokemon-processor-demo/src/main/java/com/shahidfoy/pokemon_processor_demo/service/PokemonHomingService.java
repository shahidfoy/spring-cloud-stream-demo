package com.shahidfoy.pokemon_processor_demo.service;

import com.shahidfoy.pokemon_processor_demo.entity.Pokemon;
import com.shahidfoy.pokemon_processor_demo.event.HomingPokemonEvent;
import com.shahidfoy.pokemon_processor_demo.event.NewPokemonEvent;
import com.shahidfoy.pokemon_processor_demo.event.PokemonDetail;
import com.shahidfoy.pokemon_processor_demo.event.PokemonHomingStatus;
import com.shahidfoy.pokemon_processor_demo.repository.PokemonHomingRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PokemonHomingService {

    private PokemonHomingRepository pokemonHomingRepository;

    public HomingPokemonEvent homingPokemonProcess(NewPokemonEvent newPokemonEvent) {

        List<PokemonDetail> pokemonDetailList = newPokemonEvent.pokemonDetailList();
        List<PokemonHomingStatus> pokemonHomingStatusList = pokemonDetailList.stream().map(pokemonDetail -> {
           PokemonHomingStatus homingStatus = PokemonHomingStatus.builder().build();
            BeanUtils.copyProperties(pokemonDetail, homingStatus);
            log.info("Pokemon Detail: " + pokemonDetail.getTotal());
            if (pokemonDetail.getTotal() < 400) {
                homingStatus.setHomingStatus(PokemonHomingStatus.HomingStatus.DAYCARE);
            } else {
                homingStatus.setHomingStatus(PokemonHomingStatus.HomingStatus.POKEBOX);
            }
            return homingStatus;
        }).toList();

        HomingPokemonEvent homingPokemonEvent = new HomingPokemonEvent(pokemonHomingStatusList);

        savePokemonHomingStatus(pokemonHomingStatusList);
        return homingPokemonEvent;
    }

    private void savePokemonHomingStatus(List<PokemonHomingStatus> pokemonHomingStatusList) {

        List<Pokemon> pokemonList = pokemonHomingStatusList.stream().map(pokemonHomingStatus -> {
            Pokemon pokemon = new Pokemon();
            BeanUtils.copyProperties(pokemonHomingStatus, pokemon);
            pokemon.setHomingStatus(pokemonHomingStatus.getHomingStatus().name());
            return pokemon;
        }).toList();

        log.info("***** Saving pokemon homing status ****");
        pokemonHomingRepository.saveAll(pokemonList);
    }
}
