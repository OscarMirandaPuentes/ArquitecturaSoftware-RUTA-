package modelo;

import java.util.List;

public class Administrador {
    public Juego j;
    int jugadorActualPos = 0;
    int actualPos = 0;
    Jugador jugadorActual;

    public Administrador(){
        this.j=new Juego();
    }

    public void iniciarJuego(Mazo mazo){
        j.repartir(mazo);
        obtenerJugadorActual();
        jugadorActual.robar();
    }

    public List<Carta> obtenerManoJugadorActual(int id){

		if (id == actualPos && actualPos % 2 == 0){
			return this.getJ().getEquipos().get(0).getJugadores().get(jugadorActualPos).getMano();
		}
        else if (id == actualPos) {
            return this.getJ().getEquipos().get(1).getJugadores().get(jugadorActualPos).getMano();
        }
        else{
            return null;
        }
}

	public void validarPosicionJugador() {
        actualPos++;
	    if (actualPos == this.getJ().getEquipo1().getJugadores().size()) {
	        jugadorActualPos = 0;
	        actualPos = 0;
            obtenerJugadorActual();
            jugadorActual.robar();
	    }
	    else if (actualPos % 2 == 0 && actualPos != 0) {
	        jugadorActualPos++;
            obtenerJugadorActual();
            jugadorActual.robar();
	    }else{
            obtenerJugadorActual();
            jugadorActual.robar();
        }
        System.out.println("actualpos: " + actualPos + " PosicionJug: " + jugadorActualPos);
        System.out.println(this.getJ().getEquipo1().getJugadores());
        System.out.println(this.getJ().getEquipo2().getJugadores());
	}

    public Jugador obtenerJugadorActual(){
        if (actualPos % 2 == 0){
            jugadorActual = this.getJ().getEquipos().get(0).getJugadores().get(jugadorActualPos);
        }
        else {
            jugadorActual = this.getJ().getEquipos().get(1).getJugadores().get(jugadorActualPos);
        }

        return jugadorActual;
    }

    public boolean finJuego(){
        if (j.equipo1.obtenerPuntaje()>j.getMaxPuntuacion() || j.equipo2.obtenerPuntaje()>j.getMaxPuntuacion()) {
            return true;
        }
        return false;
    }

    public Juego getJ() {
        return j;
    }

    public void setJ(Juego j) {
        this.j = j;
    }

    public int getJugadorActualPos() {
        return jugadorActualPos;
    }

    public void setJugadorActualPos(int jugadorActualPos) {
        this.jugadorActualPos = jugadorActualPos;
    }

    public int getActualPos() {
        return actualPos;
    }

    public void setActualPos(int actualPos) {
        this.actualPos = actualPos;
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public void setJugadorActual(Jugador jugadorActual) {
        this.jugadorActual = jugadorActual;
    }
}
