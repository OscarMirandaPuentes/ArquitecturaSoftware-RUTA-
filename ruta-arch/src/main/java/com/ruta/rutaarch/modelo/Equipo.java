package com.ruta.rutaarch.modelo;
import java.util.ArrayList;
import java.util.List;

public class Equipo {

    public Anotador anotador;
    public List<Jugador> miembrosE;
    public List<Carta> seguridad;
    public Pila pilaBatalla, pilaVelocidad, pilaDistancia;

    public Equipo(){
        anotador = new Anotador(this);
        seguridad = new ArrayList<Carta>();
        miembrosE = new ArrayList<Jugador>();
        pilaBatalla = new Pila();
        pilaDistancia = new Pila();
        pilaVelocidad = new Pila();
    }

    public int obtenerPuntaje(){
        return anotador.puntuacion;
    }

    public void agregarJugador(Jugador jugador){
        miembrosE.add(jugador);
    }

    public List<Jugador> getJugadores() {
        return miembrosE;
    }

    public boolean atacar(Carta carta, Equipo e2){
        return carta.accion(e2);
    }

    public boolean revisionDeSeguridad(String tipo) {
        for (Carta carta : seguridad) {
            if (carta.tipo == tipo) {
                return true; // Si se encuentra una carta del tipo especificado, retorna true
            }
        }
        return false; // Si no se encuentra ninguna carta del tipo especificado, retorna false
    }

    public boolean encuentraJugador(String nombre){
        for (Jugador j : miembrosE) {
            if (j.nombre == nombre) {
                return true; // Si se encuentra 
            }
        }
        return false; // Si no se encuentra 
    }

}
