package com.ruta.rutaarch.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruta.rutaarch.services.VisualService;

@RestController
@RequestMapping("api/visual")
public class VisualController {
    @Autowired
    private VisualService visualService;

    @GetMapping("/partida/{idPartida}/jugador/{idPlayer}")
    public ResponseEntity<Map<String, String>> showPartidaJugador(@PathVariable long idPartida, @PathVariable long idPlayer) {
        Map<String, String> result = visualService.show(idPartida, idPlayer);

        if (result.containsKey("Error")) {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }
}
