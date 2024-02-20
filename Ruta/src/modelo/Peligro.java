package modelo;

public class Peligro extends Carta{
    public String tipo;

    public Peligro(String t){
        puntos=0;
        this.tipo = t;
    }

    public void accion(Equipo eC){

        if (tipo=="Limite de velocidad") {
            eC.pilaVelocidad.ponerCarta(this);
        }else{
            //Añadir revisióna area de seguridad antes de usar
            eC.pilaBatalla.ponerCarta(this);
            eC.pilaDistancia.bloquearPila();
        }
    }
}
