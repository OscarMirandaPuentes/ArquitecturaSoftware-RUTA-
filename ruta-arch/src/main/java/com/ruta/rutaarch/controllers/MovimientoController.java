package com.ruta.rutaarch.controllers;

import com.ruta.rutaarch.entities.Movimiento;
import com.ruta.rutaarch.services.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    private final MovimientoService movimientoService;

    @Autowired
    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping
    public ResponseEntity<Movimiento> createMovimiento(@RequestBody Movimiento movimiento) {
        Movimiento createdMovimiento = movimientoService.saveMovimiento(movimiento);
        return new ResponseEntity<>(createdMovimiento, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> getMovimientoById(@PathVariable Long id) {
        Movimiento movimiento = movimientoService.getMovimientoById(id);
        return movimiento != null ? ResponseEntity.ok(movimiento) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Movimiento>> getAllMovimientos() {
        List<Movimiento> movimientos = movimientoService.getAllMovimientos();
        return new ResponseEntity<>(movimientos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movimiento> updateMovimiento(@PathVariable Long id, @RequestBody Movimiento movimiento) {
        Movimiento existingMovimiento = movimientoService.getMovimientoById(id);
        if (existingMovimiento == null) {
            return ResponseEntity.notFound().build();
        }
        movimiento.setId(id); // Ensure the passed ID is used for the update
        Movimiento updatedMovimiento = movimientoService.updateMovimiento(movimiento);
        return ResponseEntity.ok(updatedMovimiento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimiento(@PathVariable Long id) {
        Movimiento existingMovimiento = movimientoService.getMovimientoById(id);
        if (existingMovimiento == null) {
            return ResponseEntity.notFound().build();
        }
        movimientoService.deleteMovimiento(id);
        return ResponseEntity.noContent().build();
    }
}