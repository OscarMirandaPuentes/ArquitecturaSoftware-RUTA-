package com.ruta.rutaarch.controllers;

import com.ruta.rutaarch.entities.Partida;
import com.ruta.rutaarch.services.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/partidas")
public class PartidaController {

    private final PartidaService partidaService;

    @Autowired
    public PartidaController(PartidaService partidaService) {
        this.partidaService = partidaService;
    }

    @PostMapping
    public ResponseEntity<Partida> createPartida(@RequestBody Partida partida) {
        Partida createdPartida = partidaService.savePartida(partida);
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

    @GetMapping("/fechas")
    public ResponseEntity<List<Partida>> getPartidasByFecha(@RequestParam Date fechaInicio, @RequestParam Date fechaFin) {
        List<Partida> partidas = partidaService.getPartidasByFecha(fechaInicio, fechaFin);
        return new ResponseEntity<>(partidas, HttpStatus.OK);
    }
}
