package com.ruta.rutaarch.services.impl;

import com.ruta.rutaarch.entities.Carta;
import com.ruta.rutaarch.entities.Mazo;
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
    @Autowired
    private MazoRepository mazoRepository;

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
        crearCartasPeligro(mazo, cartas);
        crearCartasSoluciones(mazo, cartas);
        crearCartasSeguridad(mazo, cartas);
        crearCartasDistancia(mazo, cartas);
        mazo.setCartas(cartas);
        mezclarCartas(mazo);
    }

    private void crearCartasPeligro(Mazo mazo, List<Carta> cartas) {
    
        for (int i = 0; i < 3; i++) {
            Carta sinGasolina = new Carta();
            sinGasolina.setNombre("Sin gasolina");
            sinGasolina.setMazo(mazo);

            Carta pinchazo = new Carta();
            pinchazo.setNombre("Pinchazo");
            pinchazo.setMazo(mazo);

            Carta accidente = new Carta();
            accidente.setNombre("Accidente");
            accidente.setMazo(mazo);

            cartas.add(sinGasolina);
            cartas.add(pinchazo);
            cartas.add(accidente);
        }
        
        
        for (int i = 0; i < 4; i++) {
            Carta limiteVel = new Carta();
            limiteVel.setNombre("Límite de velocidad");
            limiteVel.setMazo(mazo);
            cartas.add(limiteVel);
        }
    
        Carta pare = new Carta();
        pare.setNombre("Pare");
        pare.setMazo(mazo);
        for (int i = 0; i < 5; i++) {
            cartas.add(pare);
        }   
    }
    
    private void crearCartasSoluciones(Mazo mazo, List<Carta> cartas) {
    
        for (int i = 0; i < 6; i++) {
            Carta gasolina = new Carta();
            gasolina.setNombre("Gasolina");
            gasolina.setMazo(mazo);

            Carta llantaRepuesto = new Carta();
            llantaRepuesto.setNombre("Llanta de repuesto");
            llantaRepuesto.setMazo(mazo);

            Carta reparacion = new Carta();
            reparacion.setNombre("Reparación");
            reparacion.setMazo(mazo);

            Carta finLimite = new Carta();
            finLimite.setNombre("Fin de límite");
            finLimite.setMazo(mazo);

            cartas.add(gasolina);
            cartas.add(llantaRepuesto);
            cartas.add(reparacion);
            cartas.add(finLimite);
        }
    
        
        for (int i = 0; i < 14; i++) {
            Carta siga = new Carta();
            siga.setNombre("Siga");
            siga.setMazo(mazo);
            cartas.add(siga);
        }
    }
    
    private void crearCartasSeguridad(Mazo mazo, List<Carta> cartas) {
        Carta cisterna = new Carta();
        cisterna.setNombre("Cisterna");
        cisterna.setMazo(mazo);

        Carta llantaIrrompible = new Carta();
        llantaIrrompible.setNombre("Llanta irrompible");
        llantaIrrompible.setMazo(mazo);

        Carta asAlVolante = new Carta();
        asAlVolante.setNombre("As al volante");
        asAlVolante.setMazo(mazo);

        Carta viaLibre = new Carta();
        viaLibre.setNombre("Vía libre");
        viaLibre.setMazo(mazo);
    
        cartas.add(cisterna);
        cartas.add(llantaIrrompible);
        cartas.add(asAlVolante);
        cartas.add(viaLibre);
    }
    
    private void crearCartasDistancia(Mazo mazo, List<Carta> cartas) {
      
        for (int i = 0; i < 4; i++) {
            Carta distancia200 = new Carta();
            distancia200.setNombre("200");
            distancia200.setMazo(mazo);
            cartas.add(distancia200);
        }
        for (int i = 0; i < 12; i++) {
            Carta distancia100 = new Carta();
            distancia100.setNombre("100");
            distancia100.setMazo(mazo);
            cartas.add(distancia100);
        }
        for (int i = 0; i < 10; i++) {
            Carta distancia75 = new Carta();
            distancia75.setNombre("75");
            distancia75.setMazo(mazo);

            Carta distancia50 = new Carta();
            distancia50.setNombre("50");
            distancia50.setMazo(mazo);

            Carta distancia25 = new Carta();
            distancia25.setNombre("25");
            distancia25.setMazo(mazo);

            cartas.add(distancia75);
            cartas.add(distancia50);
            cartas.add(distancia25);   
        }
    }

    private void mezclarCartas(Mazo mazo) {
        Collections.shuffle(mazo.getCartas());
    }
}
