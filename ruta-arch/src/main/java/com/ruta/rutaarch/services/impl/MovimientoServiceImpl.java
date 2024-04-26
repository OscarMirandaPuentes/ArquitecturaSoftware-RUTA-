package com.ruta.rutaarch.services.impl;

import com.ruta.rutaarch.entities.Movimiento;
import com.ruta.rutaarch.repositories.MovimientoRepository;
import com.ruta.rutaarch.services.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository movimientoRepository;

    @Autowired
    public MovimientoServiceImpl(MovimientoRepository movimientoRepository) {
        this.movimientoRepository = movimientoRepository;
    }

    @Override
    @Transactional
    public Movimiento saveMovimiento(Movimiento movimiento) {
        return movimientoRepository.save(movimiento);
    }

    @Override
    public Movimiento getMovimientoById(Long id) {
        return movimientoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Movimiento> getAllMovimientos() {
        return movimientoRepository.findAll();
    }

    @Override
    @Transactional
    public Movimiento updateMovimiento(Movimiento movimiento) {
        return movimientoRepository.save(movimiento);  // save works for both save and update in JPA repositories
    }

    @Override
    @Transactional
    public void deleteMovimiento(Long id) {
        movimientoRepository.deleteById(id);
    }
}