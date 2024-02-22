package modelo;
import java.util.ArrayList;
import java.util.List;

public class Equipo {

    public Anotador anotador;
    public Consultor consultor;
    public List<Jugador> miembrosE;
    public List<Carta> seguridad = new ArrayList<Carta>();
    public Pila pilaBatalla = new Pila();
    public Pila pilaVelocidad = new Pila();
    public Pila pilaDistancia = new Pila();

    public Equipo(){
        anotador = new Anotador();
        consultor = new Consultor();
        seguridad = new ArrayList<Carta>();
        miembrosE = new ArrayList<Jugador>();
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

    public void atacar(Carta carta, Equipo e2){
        carta.accion(e2);
    }

    public boolean revisionDeSeguridad(String tipo) {
        for (Carta carta : seguridad) {
            if (carta.tipo == tipo) {
                return true; // Si se encuentra una carta del tipo especificado, retorna true
            }
        }
        return false; // Si no se encuentra ninguna carta del tipo especificado, retorna false
    }

}
