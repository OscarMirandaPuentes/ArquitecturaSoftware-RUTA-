package modelo;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Equipo {

    public Anotador anotador;
    public Consultor consultor;
    public List<Jugador> miembrosE;
    public List<Carta> seguridad;
    public Stack<Carta> pilaBatalla;
    public Stack<Carta> pilaVelocidad;
    public Stack<Carta> pilaDistancia;

    public Equipo(){
        anotador = new Anotador();
        consultor = new Consultor();
        seguridad = new ArrayList<Carta>();
    }

    public int obtenerPuntaje(){
        return 1;
    }

    public void agregarJugador(Jugador jugador){
        miembrosE.add(jugador);
    }

    public void verificarJugada(Carta carta){

    }

    public void recibirAtaque(Carta carta){

    }

    public void atacar(Carta carta, Equipo e2){
        // Hacer if con tipos de ataques posibles y en que pila van -cv
    }

}
