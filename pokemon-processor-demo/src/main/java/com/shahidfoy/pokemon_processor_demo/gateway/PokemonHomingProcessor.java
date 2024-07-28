package com.shahidfoy.pokemon_processor_demo.gateway;

import com.shahidfoy.pokemon_processor_demo.event.HomingPokemonEvent;
import com.shahidfoy.pokemon_processor_demo.event.NewPokemonEvent;
import com.shahidfoy.pokemon_processor_demo.service.PokemonHomingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
@AllArgsConstructor
@Slf4j
public class PokemonHomingProcessor {

    private PokemonHomingService pokemonHomingService;

    @Bean
    public Function<NewPokemonEvent, HomingPokemonEvent> homingPokemonProcess() {
        return newPokemonEvent -> {
          HomingPokemonEvent homingPokemonEvent = pokemonHomingService.homingPokemonProcess(newPokemonEvent);

          log.info("**** Publishing pokemon homing status: {} **** ",
                  homingPokemonEvent.pokemonHomingStatusList().size());
          return (homingPokemonEvent.pokemonHomingStatusList().isEmpty()) ? null : homingPokemonEvent;
        };
    }
}
