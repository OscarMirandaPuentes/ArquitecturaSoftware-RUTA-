package com.ruta.rutaarch.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruta.rutaarch.entities.User;
import com.ruta.rutaarch.repositories.UsuarioRepository;

import java.util.List;

@RestController
@RequestMapping("/Users")
public class UserController {

    @Autowired
    private UsuarioRepository UserRepository;

    // Obtener todos los Users
    @GetMapping
    public List<User> obtenerTodosUsers() {
        return UserRepository.findAll();
    }

    // Crear un nuevo User
    @PostMapping
    public User crearUser(@RequestBody User User) {
        return UserRepository.save(User);
    }

    // Obtener un User por ID
    @GetMapping("/{id}")
    public User obtenerUserPorId(@PathVariable Long id) {
        return UserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User no encontrado con id " + id));
    }

    // Actualizar un User
    @PutMapping("/{id}")
    public User actualizarUser(@PathVariable Long id, @RequestBody User UserDetalles) {
        User User = UserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User no encontrado con id " + id));
        User.setName(UserDetalles.getName());
        User.setEmail(UserDetalles.getEmail());
        return UserRepository.save(User);
    }

    // Eliminar un User
    @DeleteMapping("/{id}")
    public String eliminarUser(@PathVariable Long id) {
        UserRepository.deleteById(id);
        return "User eliminado con éxito";
    }
}

