package com.ruta.rutaarch.controllers;

import com.ruta.rutaarch.entities.Partida;
import com.ruta.rutaarch.services.impl.PartidaServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/partidas")
public class PartidaController {

    @Autowired
    private PartidaServiceImpl partidaService;

    @PostMapping
    public ResponseEntity<Partida> createPartida(@RequestBody int numPlayers) {
        Partida createdPartida = partidaService.createPartida(numPlayers);
        return new ResponseEntity<>(createdPartida, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Partida> getPartidaById(@PathVariable Long id) {
        Partida partida = partidaService.getPartidaById(id);
        return partida != null ? ResponseEntity.ok(partida) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Partida>> getAllPartidas() {
        List<Partida> partidas = partidaService.getAllPartidas();
        return new ResponseEntity<>(partidas, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Partida> updatePartida(@PathVariable Long id, @RequestBody Partida partida) {
        Partida existingPartida = partidaService.getPartidaById(id);
        if (existingPartida == null) {
            return ResponseEntity.notFound().build();
        }
        partida.setId(id); // Ensure the passed ID is used for the update
        Partida updatedPartida = partidaService.updatePartida(partida);
        return ResponseEntity.ok(updatedPartida);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePartida(@PathVariable Long id) {
        Partida existingPartida = partidaService.getPartidaById(id);
        if (existingPartida == null) {
            return ResponseEntity.notFound().build();
        }
        partidaService.deletePartida(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Partida>> getPartidasByEstado(@PathVariable String estado) {
        List<Partida> partidas = partidaService.getPartidasByEstado(estado);
        return new ResponseEntity<>(partidas, HttpStatus.OK);
    }

}
