package modelo;

public class Peligro extends Carta{
    public String tipo;

    public Peligro(String t){
        puntos=0;
        this.tipo = t;
    }

    public void accion(Equipo e){

        if (tipo=="Limite de velocidad") {
            e.pilaVelocidad.ponerCarta(this);
            // Funcion que limita las cartas que se ponen en pila de Distancia
        }else{
            e.pilaBatalla.ponerCarta(this);
            e.pilaDistancia.bloquearPila();
        }
    }
}
