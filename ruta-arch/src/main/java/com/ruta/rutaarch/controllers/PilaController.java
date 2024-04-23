package com.ruta.rutaarch.controllers;

import com.ruta.rutaarch.entities.Pila;
import com.ruta.rutaarch.services.PilaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pilas")
public class PilaController {

    private final PilaService pilaService;

    @Autowired
    public PilaController(PilaService pilaService) {
        this.pilaService = pilaService;
    }

    @PostMapping
    public ResponseEntity<Pila> createPila(@RequestBody Pila pila) {
        Pila createdPila = pilaService.savePila(pila);
        return new ResponseEntity<>(createdPila, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pila> getPilaById(@PathVariable Long id) {
        Pila pila = pilaService.getPilaById(id);
        return pila != null ? ResponseEntity.ok(pila) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Pila>> getAllPilas() {
        List<Pila> pilas = pilaService.getAllPilas();
        return new ResponseEntity<>(pilas, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pila> updatePila(@PathVariable Long id, @RequestBody Pila pila) {
        Pila existingPila = pilaService.getPilaById(id);
        if (existingPila == null) {
            return ResponseEntity.notFound().build();
        }
        pila.setId(id);
        Pila updatedPila = pilaService.updatePila(pila);
        return ResponseEntity.ok(updatedPila);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePila(@PathVariable Long id) {
        Pila existingPila = pilaService.getPilaById(id);
        if (existingPila == null) {
            return ResponseEntity.notFound().build();
        }
        pilaService.deletePila(id);
        return ResponseEntity.noContent().build();
    }
}
