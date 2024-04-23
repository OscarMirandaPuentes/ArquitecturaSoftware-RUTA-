package com.ruta.rutaarch.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ruta.rutaarch.entities.user.User;
import com.ruta.rutaarch.repositories.UsuarioRepository;
import com.ruta.rutaarch.services.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Users")
public class UserController {

    @Autowired
    private UserService userService;


    // Crear un nuevo User
    @PostMapping
    public Boolean crearUser(@RequestBody User User) {
        return userService.saveUser(User);
    }

    // Obtener un User por ID
    @GetMapping
    public Optional<User> obtenerUserPorId(@RequestParam String email, @RequestParam String password) {
    return userService.findUserbyEmail(email);
}

    // Actualizar un User
    @PutMapping
    public boolean actualizarUser(@RequestBody String email, @RequestBody User UserDetalles) {
        return userService.updateUser(email, UserDetalles);
    }

    // Eliminar un User
    @DeleteMapping
    public String eliminarUser(@RequestBody String email) {
        userService.deleteUser(email);
        return "User eliminado con éxito";
    }
}

