package com.ruta.rutaarch.services.impl;

import com.ruta.rutaarch.entities.Equipo;
import com.ruta.rutaarch.entities.Jugador;
import com.ruta.rutaarch.entities.Mazo;
import com.ruta.rutaarch.entities.Partida;
import com.ruta.rutaarch.repositories.PartidaRepository;
import com.ruta.rutaarch.services.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartidaServiceImpl implements PartidaService {

    @Autowired
    private final PartidaRepository partidaRepository;

    @Autowired
    private MazoServiceImpl mazoService;

    @Autowired
    private EquipoServiceImpl equipoService;

    public PartidaServiceImpl(PartidaRepository partidaRepository) {
        this.partidaRepository = partidaRepository;
    }
    
    @Override
    @Transactional
    public Partida savePartida(Partida partida) {
        return partidaRepository.save(partida);
    }


    public Partida createPartida() {
        Partida partida = new Partida();
        partida.setEstado("Iniciada");
        partida.setJugadorTurno("0");

        List<Equipo> equipos = new ArrayList<>();
        Equipo eq1 = new Equipo();
        eq1.setPartida(partida);
        equipoService.llernarPilas(eq1);
        Equipo eq2 = new Equipo();
        eq2.setPartida(partida);
        equipoService.llernarPilas(eq2);
        equipos.add(eq1);
        equipos.add(eq2);
        partida.setEquipos(equipos);

        Mazo mazo = new Mazo();
        mazo.setPartida(partida);
        mazoService.iniciarMazo(mazo);
        partida.setMazo(mazo);
        return partidaRepository.save(partida);
    }

    @Override
    public Partida getPartidaById(Long id) {
        return partidaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Partida> getAllPartidas() {
        return partidaRepository.findAll();
    }

    @Override
    @Transactional
    public Partida updatePartida(Partida partida) {
        return partidaRepository.save(partida);
    }

    @Override
    @Transactional
    public void deletePartida(Long id) {
        partidaRepository.deleteById(id);
    }

    @Override
    public List<Partida> getPartidasByEstado(String estado) {
        return partidaRepository.findAllByEstado(estado);
    }

    @Override
    @Transactional
    public void actualizarEstadoPartida(Long partidaId, String nuevoEstado) {
        Partida partida = partidaRepository.findById(partidaId).orElse(null);
        if (partida != null) {
            partida.setEstado(nuevoEstado);
            partidaRepository.save(partida);
        }
    }

    public void agregarEquipo(Long partidaId, Equipo equipo) {
        Partida partida = partidaRepository.findById(partidaId).orElse(null);
        if (partida != null) {
            partida.getEquipos().add(equipo);
            partidaRepository.save(partida);
        }
    }

    public void agregarJugador(Long partidaId, String numEquipo, Jugador jugador) {
        Partida partida = partidaRepository.findById(partidaId).orElse(null);
        if(partida.getEquipos().size() == 2){
            if (numEquipo.equals("1")) {
                partida.getEquipos().get(0).getJugadores().add(jugador);
                partidaRepository.save(partida);
            }
            if (numEquipo.equals("2")) {
                partida.getEquipos().get(1).getJugadores().add(jugador);
                partidaRepository.save(partida);
            }
        }
    }

    @Override
    public int getEquipoGanador(Long partidaId) {
        Partida partida = partidaRepository.findById(partidaId).orElse(null);
        if (partida != null) {
            //Integer resultadoEquipo1 = partida.getResultadoEquipo1();
            //Integer resultadoEquipo2 = partida.getResultadoEquipo2();

            //if (resultadoEquipo1 != null && resultadoEquipo2 != null) {
              //  return (resultadoEquipo1 > resultadoEquipo2) ? 1 : 2;
            //}
        }
        return 0; // Assuming 0 means no winner or data is incomplete
    }


    @Override
    @Transactional
    public void eliminarTodasLasPartidas() {
        partidaRepository.deleteAll();
    }

    @Override
    public long obtenerNumeroTotalDePartidas() {
        return partidaRepository.count();
    }
}
