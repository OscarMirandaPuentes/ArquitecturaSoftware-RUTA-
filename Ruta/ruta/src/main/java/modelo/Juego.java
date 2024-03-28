package modelo;

import java.util.ArrayList;
import java.util.List;

public class Juego {
    List<Equipo> equipos;
    int maxPuntuacion;
    Equipo equipo1;
    Equipo equipo2;
    Mazo m;

    public Juego(){
        equipo1 = new Equipo();
        equipo2 = new Equipo();
        equipos = new ArrayList<Equipo>();
        equipos.add(equipo1);
        equipos.add(equipo2);
    }
    
    public void repartir(Mazo mazo) {
        this.m = mazo;
        for (Equipo equipo : equipos) {
            List<Jugador> jugadores = equipo.getJugadores();
            for (int i = 0; i < 6; i++) { // Repartir 6 cartas
                for (Jugador jugador : jugadores) {
                    jugador.recibirCarta(m.dar()); // Entregar la carta al jugador
                }
            }
        }
    }

    public boolean checkPilas(){
        return (!equipo1.pilaBatalla.isEmpty() && !equipo2.pilaBatalla.isEmpty() &&
                !equipo1.pilaDistancia.isEmpty()&&!equipo2.pilaDistancia.isEmpty() &&
                !equipo1.pilaVelocidad.isEmpty()&&!equipo2.pilaVelocidad.isEmpty());
    }

    public Jugador crearJugador(String nombre) {
        return new Jugador(nombre);
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public int getMaxPuntuacion() {
        return maxPuntuacion;
    }

    public void setMaxPuntuacion(int maxPuntuacion) {
        this.maxPuntuacion = maxPuntuacion;
    }

    public Equipo getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(Equipo equipo2) {
        this.equipo2 = equipo2;
    }

    public Mazo getM() {
        return m;
    }

    public void setM(Mazo m) {
        this.m = m;
    }
}
