package com.shahidfoy.pokemon_source_demo.repository;

import com.shahidfoy.pokemon_source_demo.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonCapturedRepository extends JpaRepository<Pokemon, Long> {

    List<Pokemon> findByProcessingStatus(boolean isProcessing);
}
