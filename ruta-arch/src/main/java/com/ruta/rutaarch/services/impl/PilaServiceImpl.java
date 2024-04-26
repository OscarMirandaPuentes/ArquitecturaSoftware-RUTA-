package com.ruta.rutaarch.services.impl;

import com.ruta.rutaarch.entities.Pila;
import com.ruta.rutaarch.repositories.PilaRepository;
import com.ruta.rutaarch.services.PilaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PilaServiceImpl implements PilaService {

    private final PilaRepository pilaRepository;

    @Autowired
    public PilaServiceImpl(PilaRepository pilaRepository) {
        this.pilaRepository = pilaRepository;
    }

    @Override
    @Transactional
    public Pila savePila(Pila pila) {
        return pilaRepository.save(pila);
    }

    @Override
    public Pila getPilaById(Long id) {
        return pilaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Pila> getAllPilas() {
        return pilaRepository.findAll();
    }

    @Override
    @Transactional
    public Pila updatePila(Pila pila) {
        return pilaRepository.save(pila);
    }

    @Override
    @Transactional
    public void deletePila(Long id) {
        pilaRepository.deleteById(id);
    }
}
