package modelo;

public class Distancia extends Carta{

    public Distancia(int x){
        puntos = x;
    }

    public Distancia(){}

    public void accion(Equipo e){
        e.pilaDistancia.add(this);
    }
}
