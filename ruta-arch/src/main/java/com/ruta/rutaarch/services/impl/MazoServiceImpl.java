package com.ruta.rutaarch.services.impl;

import com.ruta.rutaarch.entities.Mazo;
import com.ruta.rutaarch.repositories.MazoRepository;
import com.ruta.rutaarch.services.MazoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MazoServiceImpl implements MazoService {

    private final MazoRepository mazoRepository;

    @Autowired
    public MazoServiceImpl(MazoRepository mazoRepository) {
        this.mazoRepository = mazoRepository;
    }

    @Override
    @Transactional
    public Mazo saveMazo(Mazo mazo) {
        return mazoRepository.save(mazo);
    }

    @Override
    public Mazo getMazoById(Long id) {
        return mazoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Mazo> getAllMazos() {
        return mazoRepository.findAll();
    }

    @Override
    @Transactional
    public Mazo updateMazo(Mazo mazo) {
        return mazoRepository.save(mazo);
    }

    @Override
    @Transactional
    public void deleteMazo(Long id) {
        mazoRepository.deleteById(id);
    }
}