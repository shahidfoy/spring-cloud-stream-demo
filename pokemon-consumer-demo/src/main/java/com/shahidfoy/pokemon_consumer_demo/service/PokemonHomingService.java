package com.shahidfoy.pokemon_consumer_demo.service;

import com.shahidfoy.pokemon_consumer_demo.entity.PokemonBox;
import com.shahidfoy.pokemon_consumer_demo.entity.PokemonDaycare;
import com.shahidfoy.pokemon_consumer_demo.event.PokemonHomingStatus;
import com.shahidfoy.pokemon_consumer_demo.repository.PokemonBoxRepository;
import com.shahidfoy.pokemon_consumer_demo.repository.PokemonDaycareRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PokemonHomingService {

    private PokemonBoxRepository pokemonBoxRepository;
    private PokemonDaycareRepository pokemonDaycareRepository;

    public void determinePokemonArrangement(List<PokemonHomingStatus> pokemonHomingStatusList) {

        pokemonHomingStatusList.forEach(pokemon -> {
            if (pokemon.getHomingStatus().equals(PokemonHomingStatus.HomingStatus.DAYCARE)) {
                log.info("***** Sending pokemon {} to the daycare.", pokemon.getName());
                PokemonDaycare newPokemon = new PokemonDaycare();
                BeanUtils.copyProperties(pokemon, newPokemon);
                pokemonDaycareRepository.save(newPokemon);
                log.info("***** Saved pokemon {} to the daycare.", pokemon.getName());
            } else {
                log.info("***** Sending pokemon {} to the pokebox.", pokemon.getName());
                PokemonBox newPokemon = new PokemonBox();
                BeanUtils.copyProperties(pokemon, newPokemon);
                pokemonBoxRepository.save(newPokemon);
                log.info("***** Saved pokemon {} to the pokebox.", pokemon.getName());
            }
        });
    }

    public String findPokemonLocation(String referenceId) {
        log.info("*** Searching for pokemon location using reference id: {}", referenceId);
        PokemonDaycare pokemonDaycare = pokemonDaycareRepository.findByReferenceId(referenceId);
        PokemonBox pokemonBox = pokemonBoxRepository.findByReferenceId(referenceId);

        if (pokemonDaycare != null) return "Your pokemon " + pokemonDaycare.getName() + " is at the daycare";
        else if (pokemonBox != null) return "Your pokemon " + pokemonBox.getName() + " is in your pokebox";
        else return "No pokemon found with the provided reference id";
    }
}
