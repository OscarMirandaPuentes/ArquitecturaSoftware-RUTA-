package modelo;

public class Peligro extends Carta{
    public String tipo;

    public Peligro(String t){
        puntos=0;
        this.tipo = t;
    }

    public void accion(Equipo e){
        e.pilaBatalla.add(this);
        //e.pilaDistancia
    }
}
