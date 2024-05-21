package com.ruta.rutaarch.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruta.rutaarch.entities.Carta;
import com.ruta.rutaarch.entities.Equipo;
import com.ruta.rutaarch.entities.Jugador;
import com.ruta.rutaarch.entities.Partida;
import com.ruta.rutaarch.modelo.CartasMap;
import com.ruta.rutaarch.services.impl.ReglasImpl;

@Service
public class GameplayService {

    @Autowired
    ReglasImpl reglas;

    @Autowired
    PartidaService partidaService;

    CartasMap cartasMap = new CartasMap();

    public void validarPosicionJugador(Partida partida, int idJugador){
        Jugador jugadorActual = partidaService.getJugadorById(partida.getId(), idJugador);
        int actualPos = 0;

        for (Equipo equipo : partida.getEquipos()) {
            List<Jugador> jugadores = equipo.getJugadores();
            for (int i = 0; i < jugadores.size(); i++) {
                Jugador jugador = jugadores.get(i);
                if (jugador.getId() == idJugador) {
                    actualPos = i;
                }
            }
        }

	    if (actualPos == (partida.getNumJugadores()/2) - 1 && jugadorActual.getEquipo().getId() % 2 == 0) {
            jugadorActual = partida.getEquipos().get(0).getJugadores().get(0);
            partida.setJugadorTurno(jugadorActual.getId());
            robar(jugadorActual, partida);
            partidaService.savePartida(partida);
	    }
	    else if (jugadorActual.getEquipo().getId() % 2 == 0) {
	        jugadorActual = partida.getEquipos().get(0).getJugadores().get(actualPos + 1);
            partida.setJugadorTurno(jugadorActual.getId());
            robar(jugadorActual, partida);
            partidaService.savePartida(partida);
	    }else{
            jugadorActual = partida.getEquipos().get(1).getJugadores().get(actualPos);
            partida.setJugadorTurno(jugadorActual.getId());
            System.out.println(jugadorActual.getId());
            robar(jugadorActual, partida);
            partidaService.savePartida(partida);
        }
       
    }

    public boolean jugada(Long idPartida, int idPlayer, int accion, int posCarta){
        Partida partida = partidaService.getPartidaById(idPartida);
        long jugadorActual = partida.getJugadorTurno();
        boolean ans = false;

       if(idPlayer == jugadorActual){
        Jugador jugador = partidaService.getJugadorById(idPartida, idPlayer);
        String carta = jugador.getMano().get(posCarta).getNombre();

        if(accion == 0){
            
            if(jugador.getEquipo().getId() % 2 == 0){
            
                ans = reglas.verificarJugada(carta, jugador.getEquipo().getPillaVelocidad(),
                                        jugador.getEquipo().getPillaDistancia(), 
                                        jugador.getEquipo().getPillaBatalla(), 
                                        partida.getEquipos().get(1).getSeguridad());
            }
            else{
                ans = reglas.verificarJugada(carta, jugador.getEquipo().getPillaVelocidad(),
                                        jugador.getEquipo().getPillaDistancia(), 
                                        jugador.getEquipo().getPillaBatalla(), 
                                        partida.getEquipos().get(0).getSeguridad());
            }

            if(ans){
                jugador.getMano().get(posCarta).setJugador(null);
                jugador.getMano().remove(posCarta);
                ubicarCartaPila(carta, partida, jugador);
            }
        }

        if(accion == 1){
            jugador.getMano().get(posCarta).setJugador(null);
            jugador.getMano().remove(posCarta);
            ans = true;
        }

        if (ans){
            validarPosicionJugador(partida, idPlayer);
        }
       }

       partidaService.savePartida(partida);

       return ans;
    }

    public void robar(Jugador jugador, Partida partida){
        List<Carta> manoInicial = jugador.getMano();
        Carta carta = partida.getMazo().getCartas().remove(0);
        carta.setJugador(jugador);
        carta.setMazo(null);
        manoInicial.add(carta);
        jugador.setMano(manoInicial);
    }

    public void ubicarCartaPila(String carta, Partida partida, Jugador jugador){
        String pilaDestino = cartasMap.getPilaLlegada().get(carta);
        String tipoCarta = cartasMap.getCartasPorTipo().get(carta);

        if(pilaDestino.equals("pilaBatalla") && tipoCarta.equals("Defensa")){
            jugador.getEquipo().setPillaBatalla(carta);
        }

        if(pilaDestino.equals("pilaDistancia") && tipoCarta.equals("Distancia")){
            jugador.getEquipo().setPillaDistancia(carta);
        }

        if(pilaDestino.equals("Seguridad") && tipoCarta.equals("Seguridad")){
            jugador.getEquipo().getSeguridad().add(carta);
        }

        if(pilaDestino.equals("pilaBatalla") && tipoCarta.equals("Peligro")){
            if(jugador.getEquipo().getId() % 2 == 0){
                partida.getEquipos().get(0).setPillaBatalla(carta);
            }
            else{
                partida.getEquipos().get(1).setPillaBatalla(carta);
            }
        }

        if(pilaDestino.equals("pilaVelocidad") && tipoCarta.equals("Peligro")){
            if(jugador.getEquipo().getId() % 2 == 0){
                partida.getEquipos().get(0).setPillaVelocidad(carta);
            }
            else{
                partida.getEquipos().get(1).setPillaVelocidad(carta);
            }
        }
    }
}

