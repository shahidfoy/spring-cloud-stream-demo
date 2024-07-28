package com.shahidfoy.pokemon_consumer_demo.repository;

import com.shahidfoy.pokemon_consumer_demo.entity.PokemonBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonBoxRepository extends JpaRepository<PokemonBox, Long> {

    PokemonBox findByReferenceId(String referenceId);
}
