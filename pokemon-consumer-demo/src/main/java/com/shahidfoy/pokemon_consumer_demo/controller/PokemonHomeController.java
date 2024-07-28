package com.shahidfoy.pokemon_consumer_demo.controller;

import com.shahidfoy.pokemon_consumer_demo.service.PokemonHomingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/pokehome")
@Tag(name = "Pokemon Home Controller")
public class PokemonHomeController {

    private PokemonHomingService pokemonHomingService;

    @Operation(summary = "Locate pokemon home")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping("/locateByReferenceId")
    public ResponseEntity<String> pokemonHomingStatus(
            @Parameter(description = "Pokemon Homing Reference Id", required = true)
            @RequestParam String referenceId
    ) {

        if (StringUtils.isEmpty(referenceId)) {
            ResponseEntity.badRequest().build();
        }
        String status = pokemonHomingService.findPokemonLocation(referenceId);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }
}
