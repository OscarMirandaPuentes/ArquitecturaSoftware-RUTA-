package com.ruta.rutaarch.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ruta.rutaarch.entities.Partida;
import com.ruta.rutaarch.entities.auth.AuthenticationResponse;
import com.ruta.rutaarch.entities.user.User;
import com.ruta.rutaarch.services.HistorialService;
import com.ruta.rutaarch.services.UserService;
import com.ruta.rutaarch.services.impl.AuthenticationService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HistorialService historialService;

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/info")
    public Optional<User> infoUser(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        return userService.findUserbyEmail(email);
    }

    @PostMapping("/historial")
    public List<Partida> historialUser(@RequestBody String email) {
        System.out.println(email);
        return historialService.mostrarHistorial(email);
    }

    // Actualizar un User
    @PutMapping("/{email}")
    public ResponseEntity<AuthenticationResponse> actualizarUser(@PathVariable String email, @RequestBody User userDetalles) {
        System.out.println(email);
        if(userService.updateUser(email, userDetalles)){
            return ResponseEntity.ok(authenticationService.NewTokenUser(userDetalles.getEmail()));
        }
        return ResponseEntity.status(HttpStatusCode.valueOf(403)).build();
    }

    // Eliminar un User
    @DeleteMapping
    public String eliminarUser(@RequestBody Map<String, String> requestBody) {
        System.out.println(requestBody.get("email"));
        userService.deleteUser(requestBody.get("email"));
        return "User eliminado con éxito";
    }
}

