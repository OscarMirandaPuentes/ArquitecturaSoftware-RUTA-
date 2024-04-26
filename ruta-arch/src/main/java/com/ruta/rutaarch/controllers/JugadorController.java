package com.ruta.rutaarch.controllers;

import com.ruta.rutaarch.entities.Jugador;
import com.ruta.rutaarch.services.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/jugadores")
public class JugadorController {

    private final JugadorService jugadorService;

    @Autowired
    public JugadorController(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    @PostMapping
    public ResponseEntity<Jugador> createJugador(@RequestBody Jugador jugador) {
        Jugador createdJugador = jugadorService.saveJugador(jugador);
        return new ResponseEntity<>(createdJugador, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jugador> getJugadorById(@PathVariable Long id) {
        Jugador jugador = jugadorService.getJugadorById(id);
        return jugador != null ? ResponseEntity.ok(jugador) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Jugador>> getAllJugadores() {
        List<Jugador> jugadores = jugadorService.getAllJugadores();
        return new ResponseEntity<>(jugadores, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jugador> updateJugador(@PathVariable Long id, @RequestBody Jugador jugador) {
        Jugador existingJugador = jugadorService.getJugadorById(id);
        if (existingJugador == null) {
            return ResponseEntity.notFound().build();
        }
        jugador.setId(id);
        Jugador updatedJugador = jugadorService.updateJugador(jugador);
        return ResponseEntity.ok(updatedJugador);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJugador(@PathVariable Long id) {
        Jugador existingJugador = jugadorService.getJugadorById(id);
        if (existingJugador == null) {
            return ResponseEntity.notFound().build();
        }
        jugadorService.deleteJugador(id);
        return ResponseEntity.noContent().build();
    }
}
