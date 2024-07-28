package com.shahidfoy.pokemon_source_demo.controller;

import com.shahidfoy.pokemon_source_demo.dto.PokemonRequest;
import com.shahidfoy.pokemon_source_demo.service.PokemonService;
import io.netty.util.internal.StringUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/pokemon")
@Tag(name = "Pokemon Controller")
public class PokemonController {

    private PokemonService pokemonService;

    @Operation(description = "Handles captured pokemon and places them in the pokebox or pokemon daycare.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "200", description = "SUCCESS")
    })
    @PostMapping(path = "/captured")
    public ResponseEntity<String> capturedPokemonRequest(@RequestBody PokemonRequest pokemonRequest) {

        if (StringUtils.isEmpty(pokemonRequest.getName()) ||
            StringUtils.isEmpty(pokemonRequest.getType1()) ||
                pokemonRequest.getTotal() < 0) {
            return ResponseEntity.badRequest().build();
        }

        Optional<String> pokemonReferenceId = pokemonService.saveCapturedPokemon(pokemonRequest);
        return pokemonReferenceId.map(prId ->
                        ResponseEntity
                                .status(HttpStatus.CREATED).body("Your pokemon reference number is " + prId))
                                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

}
