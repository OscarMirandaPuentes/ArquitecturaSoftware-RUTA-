package com.ruta.rutaarch.controllers;

import com.ruta.rutaarch.entities.Mazo;
import com.ruta.rutaarch.services.MazoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mazos")
public class MazoController {

    private final MazoService mazoService;

    @Autowired
    public MazoController(MazoService mazoService) {
        this.mazoService = mazoService;
    }

    @PostMapping
    public ResponseEntity<Mazo> createMazo(@RequestBody Mazo mazo) {
        Mazo createdMazo = mazoService.saveMazo(mazo);
        return new ResponseEntity<>(createdMazo, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mazo> getMazoById(@PathVariable Long id) {
        Mazo mazo = mazoService.getMazoById(id);
        return mazo != null ? ResponseEntity.ok(mazo) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Mazo>> getAllMazos() {
        List<Mazo> mazos = mazoService.getAllMazos();
        return new ResponseEntity<>(mazos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mazo> updateMazo(@PathVariable Long id, @RequestBody Mazo mazo) {
        Mazo existingMazo = mazoService.getMazoById(id);
        if (existingMazo == null) {
            return ResponseEntity.notFound().build();
        }
        mazo.setId(id);
        Mazo updatedMazo = mazoService.updateMazo(mazo);
        return ResponseEntity.ok(updatedMazo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMazo(@PathVariable Long id) {
        Mazo existingMazo = mazoService.getMazoById(id);
        if (existingMazo == null) {
            return ResponseEntity.notFound().build();
        }
        mazoService.deleteMazo(id);
        return ResponseEntity.noContent().build();
    }
}
