package com.ruta.rutaarch.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ruta.rutaarch.request.JugadaRequest;
import com.ruta.rutaarch.services.GameplayService;


@RestController
@RequestMapping("api/jugada")
public class GameplayController {
    @Autowired
    private GameplayService gameplayService;

    @PostMapping
    public ResponseEntity<Boolean> createJugador(@RequestBody JugadaRequest request) {
        boolean response = gameplayService.jugada(request.getIdPartida(), request.getIdPlayer(), request.getAccion(), request.getPosCarta());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
