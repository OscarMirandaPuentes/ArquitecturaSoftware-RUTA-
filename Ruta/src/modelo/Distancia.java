package modelo;

public class Distancia extends Carta{

    public Distancia(int x){
        this.puntos = x;
        this.tipo = String.valueOf(x);
    }

    public boolean accion(Equipo e){
        Carta cartaBatalla = e.pilaBatalla.cimaCarta();
        if (cartaBatalla != null && cartaBatalla.tipo.equals("Siga")) {
            Carta cartaVelocidad = e.pilaVelocidad.cimaCarta();
            if(cartaVelocidad==null){
                e.pilaDistancia.ponerCarta(this);
                return true;
            } else{
                if (cartaVelocidad.tipo.equals("Límite de velocidad")) {
                    if (puntos < 51) {
                        e.pilaDistancia.ponerCarta(this);
                        return true;
                    } else {
                        System.out.println("Tienes límite de velocidad :(");
                        return false;
                    }
                } else{
                    e.pilaDistancia.ponerCarta(this);
                    return true;
                }
            }
        } else {
            System.out.println("No puedes avanzar :(");
            return false;
        }
    }
}
