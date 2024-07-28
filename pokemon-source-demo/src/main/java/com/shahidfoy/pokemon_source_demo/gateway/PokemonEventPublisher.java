package com.shahidfoy.pokemon_source_demo.gateway;

import com.shahidfoy.pokemon_source_demo.event.NewPokemonEvent;
import com.shahidfoy.pokemon_source_demo.service.PokemonEventPublisherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
@AllArgsConstructor
@Configuration
public class PokemonEventPublisher {

    private PokemonEventPublisherService pokemonEventPublisherService;

    @Bean
    public Supplier<NewPokemonEvent> publishNewPokemonEvent() {
        return () -> {
            Optional<NewPokemonEvent> newPokemonEvent = pokemonEventPublisherService.publishEvent();
            return newPokemonEvent.orElse(null);
        };
    }
}
