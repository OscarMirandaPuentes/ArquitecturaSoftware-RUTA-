package com.ruta.rutaarch.controllers;
import com.ruta.rutaarch.entities.Carta;
import com.ruta.rutaarch.services.CartaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cartas")
public class CartaController {

    private final CartaService cartaService;

    @Autowired
    public CartaController(CartaService cartaService) {
        this.cartaService = cartaService;
    }

    @PostMapping
    public ResponseEntity<Carta> createCarta(@RequestBody Carta carta) {
        Carta createdCarta = cartaService.createCarta(carta);
        return new ResponseEntity<>(createdCarta, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carta> getCartaById(@PathVariable Long id) {
        Carta carta = cartaService.getCartaById(id);
        return carta != null ? ResponseEntity.ok(carta) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Carta>> getAllCartas() {
        List<Carta> cartas = cartaService.getAllCartas();
        return new ResponseEntity<>(cartas, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carta> updateCarta(@PathVariable Long id, @RequestBody Carta carta) {
        Carta existingCarta = cartaService.getCartaById(id);
        if (existingCarta == null) {
            return ResponseEntity.notFound().build();
        }
        carta.setId(id);
        Carta updatedCarta = cartaService.updateCarta(carta);
        return ResponseEntity.ok(updatedCarta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarta(@PathVariable Long id) {
        Carta existingCarta = cartaService.getCartaById(id);
        if (existingCarta == null) {
            return ResponseEntity.notFound().build();
        }
        cartaService.deleteCarta(id);
        return ResponseEntity.noContent().build();
    }
}