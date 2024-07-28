package com.shahidfoy.pokemon_processor_demo.repository;

import com.shahidfoy.pokemon_processor_demo.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonHomingRepository extends JpaRepository<Pokemon, Long> {
}
