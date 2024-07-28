package com.shahidfoy.pokemon_consumer_demo.gateway;

import com.shahidfoy.pokemon_consumer_demo.event.HomingPokemonEvent;
import com.shahidfoy.pokemon_consumer_demo.service.PokemonHomingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@AllArgsConstructor
@Slf4j
@Configuration
public class PokemonHomingEventListener {

    private PokemonHomingService pokemonHomingService;

    @Bean
    public Consumer<HomingPokemonEvent> pokemonHomingArrangements() {
        return homingPokemonEvent -> {
            log.info("Received {} pokemon", homingPokemonEvent.pokemonHomingStatusList().size());
            pokemonHomingService.determinePokemonArrangement(homingPokemonEvent.pokemonHomingStatusList());
        };
    }
}
