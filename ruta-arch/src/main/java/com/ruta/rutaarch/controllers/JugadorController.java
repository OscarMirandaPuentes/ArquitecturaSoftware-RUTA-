package com.ruta.rutaarch.controllers;

import com.ruta.rutaarch.entities.Partida;
import com.ruta.rutaarch.request.insertJugadorRequest;
import com.ruta.rutaarch.services.impl.InsertarJugService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/jugadores")
public class JugadorController {

    @Autowired
    private InsertarJugService jugadorService;

    @PostMapping
    public ResponseEntity<Partida> createJugador(@RequestBody insertJugadorRequest request) {
        Partida partida = jugadorService.insertJugador(request.getEmail(), request.getIdPartida(), request.getNumEquipo());
        return new ResponseEntity<>(partida, HttpStatus.CREATED);
    }
}
