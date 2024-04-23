package com.ruta.rutaarch.services.impl;

import com.ruta.rutaarch.entities.Carta;
import com.ruta.rutaarch.repositories.CartaRepository;
import com.ruta.rutaarch.services.CartaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartaServiceImpl implements CartaService {

    @Autowired
    private  CartaRepository cartaRepository;

    @Override
    @Transactional
    public Carta createCarta(Carta carta) {
        return cartaRepository.save(carta);
    }

    @Override
    public Carta getCartaById(Long id) {
        return cartaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Carta> getAllCartas() {
        return cartaRepository.findAll();
    }

    @Override
    @Transactional
    public Carta updateCarta(Carta carta) {
        return cartaRepository.save(carta);
    }

    @Override
    @Transactional
    public void deleteCarta(Long id) {
        cartaRepository.deleteById(id);
    }
}
