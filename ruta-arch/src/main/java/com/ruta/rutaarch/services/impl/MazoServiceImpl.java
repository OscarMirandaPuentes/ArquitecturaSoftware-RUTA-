package com.ruta.rutaarch.services.impl;

import com.ruta.rutaarch.entities.Carta;
import com.ruta.rutaarch.entities.Equipo;
import com.ruta.rutaarch.entities.Mazo;
import com.ruta.rutaarch.entities.Partida;
import com.ruta.rutaarch.repositories.MazoRepository;
import com.ruta.rutaarch.services.MazoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
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

    public void iniciarMazo(Mazo mazo){
        List<Carta> cartas = new ArrayList<>();
        mazo.setCartas(cartas);
        crearCartasPeligro(mazo);
        crearCartasSoluciones(mazo);
        crearCartasSeguridad(mazo);
        crearCartasDistancia(mazo);
        mezclarCartas(mazo);
    }

    private void crearCartasPeligro(Mazo mazo) {
        Carta sinGasolina = new Carta("Sin gasolina");
        sinGasolina.setMazo(mazo);
        Carta pinchazo = new Carta("Pinchazo");
        pinchazo.setMazo(mazo);
        Carta accidente = new Carta("Accidente");
        accidente.setMazo(mazo);
    
        for (int i = 0; i < 3; i++) {
            mazo.getCartas().add(sinGasolina);
            mazo.getCartas().add(pinchazo);
            mazo.getCartas().add(accidente);
        }
    
        for (int i = 0; i < 4; i++) {
            mazo.getCartas().add(new Carta("Límite de velocidad"));
        }
    
        for (int i = 0; i < 5; i++) {
            mazo.getCartas().add(new Carta("Pare"));
        }   
    }
    
    private void crearCartasSoluciones(Mazo mazo) {
        Carta gasolina = new Carta("Gasolina");
        gasolina.setMazo(mazo);
        Carta llantaRepuesto = new Carta("Llanta de repuesto");
        llantaRepuesto.setMazo(mazo);
        Carta reparacion = new Carta("Reparación");
        reparacion.setMazo(mazo);
        Carta finLimite = new Carta("Fin de límite");
        finLimite.setMazo(mazo);
    
        for (int i = 0; i < 6; i++) {
            mazo.getCartas().add(gasolina);
            mazo.getCartas().add(llantaRepuesto);
            mazo.getCartas().add(reparacion);
            mazo.getCartas().add(finLimite);
        }
    
        Carta siga = new Carta("Siga");
        siga.setMazo(mazo);
        for (int i = 0; i < 14; i++) {
            mazo.getCartas().add(siga);
        }
    }
    
    private void crearCartasSeguridad(Mazo mazo) {
        Carta cisterna = new Carta("Cisterna");
        cisterna.setMazo(mazo);
        Carta llantaIrrompible = new Carta("Llanta irrompible");
        llantaIrrompible.setMazo(mazo);
        Carta asAlVolante = new Carta("As al volante");
        asAlVolante.setMazo(mazo);
        Carta viaLibre = new Carta("Vía libre");
        viaLibre.setMazo(mazo);
    
        mazo.getCartas().add(cisterna);
        mazo.getCartas().add(llantaIrrompible);
        mazo.getCartas().add(asAlVolante);
        mazo.getCartas().add(viaLibre);
    }
    
    private void crearCartasDistancia(Mazo mazo) {
        Carta distancia200 = new Carta("Distancia 200");
        distancia200.setMazo(mazo);
        Carta distancia100 = new Carta("Distancia 100");
        distancia100.setMazo(mazo);
        Carta distancia75 = new Carta("Distancia 75");
        distancia75.setMazo(mazo);
        Carta distancia50 = new Carta("Distancia 50");
        distancia50.setMazo(mazo);
        Carta distancia25 = new Carta("Distancia 25");
        distancia25.setMazo(mazo);
    
        for (int i = 0; i < 4; i++) {
            mazo.getCartas().add(distancia200);
        }
        for (int i = 0; i < 12; i++) {
            mazo.getCartas().add(distancia100);
        }
        for (int i = 0; i < 10; i++) {
            mazo.getCartas().add(distancia75);
            mazo.getCartas().add(distancia50);
            mazo.getCartas().add(distancia25);   
        }
    }

    private void mezclarCartas(Mazo mazo) {
        Collections.shuffle(mazo.getCartas());
    }
}
