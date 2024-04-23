package com.ruta.rutaarch.services.impl;

import com.ruta.rutaarch.entities.Equipo;
import com.ruta.rutaarch.repositories.EquipoRepository;
import com.ruta.rutaarch.services.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EquipoServiceImpl implements EquipoService {

    private final EquipoRepository equipoRepository;

    @Autowired
    public EquipoServiceImpl(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    @Override
    @Transactional
    public Equipo createEquipo(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    @Override
    public Equipo getEquipoById(Long id) {
        return equipoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Equipo> getAllEquipos() {
        return equipoRepository.findAll();
    }

    @Override
    @Transactional
    public Equipo updateEquipo(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    @Override
    @Transactional
    public void deleteEquipo(Long id) {
        equipoRepository.deleteById(id);
    }
}
