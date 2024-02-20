package modelo;

public class Distancia extends Carta{

    public Distancia(int x){
        this.puntos = x;
    }

    public Distancia(){}

    public void accion(Equipo e){
        if (e.pilaBatalla.cimaCarta().tipo=="Siga") {
            if (e.pilaVelocidad.cimaCarta().tipo == "Limite de velocidad") {
                if (puntos<51) {
                    e.pilaDistancia.ponerCarta(this);
                }else{
                    System.out.println("Tienes límite de velocidad :(");
                }
            }else{
                e.pilaDistancia.ponerCarta(this);
            }
        } else{
            System.out.println("No puedes avanzar :(");
        }
    }
}
