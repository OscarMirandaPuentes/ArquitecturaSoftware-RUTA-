package modelo;
import java.util.ArrayList;
import java.util.List;

public class Equipo {

    public Anotador anotador;
    public Consultor consultor;
    public List<Jugador> miembrosE;
    public List<Carta> seguridad;
    public Pila pilaBatalla;
    public Pila pilaVelocidad;
    public Pila pilaDistancia;

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
