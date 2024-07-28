package com.shahidfoy.pokemon_consumer_demo.repository;

import com.shahidfoy.pokemon_consumer_demo.entity.PokemonDaycare;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonDaycareRepository extends JpaRepository<PokemonDaycare, Long> {

    PokemonDaycare findByReferenceId(String referenceId);
}
