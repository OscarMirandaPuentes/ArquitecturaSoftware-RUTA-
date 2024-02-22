package modelo;

public class Distancia extends Carta{

    public Distancia(int x){
        this.puntos = x;
        this.tipo = String.valueOf(x);
    }

    public Distancia(){}

    public void accion(Equipo e){
        Carta cartaBatalla = e.pilaBatalla.cimaCarta();
        if (cartaBatalla != null && cartaBatalla.tipo.equals("Siga")) {
            Carta cartaVelocidad = e.pilaVelocidad.cimaCarta();
            if(cartaVelocidad==null){
                e.pilaDistancia.ponerCarta(this);
            } else{
                if (cartaVelocidad.tipo.equals("Límite de velocidad")) {
                    if (puntos < 51) {
                        e.pilaDistancia.ponerCarta(this);
                    } else {
                        System.out.println("Tienes límite de velocidad :(");
                    }
                }
            }
        } else {
            System.out.println("No puedes avanzar :(");
        }
    }
}
