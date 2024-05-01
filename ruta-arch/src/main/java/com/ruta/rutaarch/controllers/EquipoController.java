package com.ruta.rutaarch.controllers;

import com.ruta.rutaarch.entities.Equipo;
import com.ruta.rutaarch.services.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/equipos")
public class EquipoController {

    private final EquipoService equipoService;

    @Autowired
    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    @PostMapping
    public ResponseEntity<Equipo> createEquipo(@RequestBody Equipo equipo) {
        Equipo createdEquipo = equipoService.createEquipo(equipo);
        return new ResponseEntity<>(createdEquipo, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipo> getEquipoById(@PathVariable Long id) {
        Equipo equipo = equipoService.getEquipoById(id);
        return equipo != null ? ResponseEntity.ok(equipo) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Equipo>> getAllEquipos() {
        List<Equipo> equipos = equipoService.getAllEquipos();
        return new ResponseEntity<>(equipos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipo> updateEquipo(@PathVariable Long id, @RequestBody Equipo equipo) {
        Equipo existingEquipo = equipoService.getEquipoById(id);
        if (existingEquipo == null) {
            return ResponseEntity.notFound().build();
        }
        equipo.setId(id);
        Equipo updatedEquipo = equipoService.updateEquipo(equipo);
        return ResponseEntity.ok(updatedEquipo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipo(@PathVariable Long id) {
        Equipo existingEquipo = equipoService.getEquipoById(id);
        if (existingEquipo == null) {
            return ResponseEntity.notFound().build();
        }
        equipoService.deleteEquipo(id);
        return ResponseEntity.noContent().build();
    }
}
