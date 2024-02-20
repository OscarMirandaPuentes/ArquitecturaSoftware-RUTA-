package modelo;

public class Defensa extends Carta{
    public String tipo;

    public Defensa(String t){
        puntos=0;
        this.tipo = t;
    }

    public void accion(Equipo e){
        if (tipo=="Fin de limite" && e.pilaVelocidad.cimaCarta().tipo=="Limite de velocidad") {
            e.pilaVelocidad.ponerCarta(this);
        }else{
            if (tipo=="Gasolina" && e.pilaBatalla.cimaCarta().tipo=="Sin Gasolina") {
                e.pilaBatalla.ponerCarta(this);
                e.pilaDistancia.desbloquearPila();
            } else if (tipo=="Llanta de repuesto" && e.pilaBatalla.cimaCarta().tipo=="Pinchazo") {
                e.pilaBatalla.ponerCarta(this);
                e.pilaDistancia.desbloquearPila();
            } else if (tipo=="Reparación" && e.pilaBatalla.cimaCarta().tipo=="Accidente") {
                e.pilaBatalla.ponerCarta(this);
                e.pilaDistancia.desbloquearPila();
            } else if (tipo=="Siga") {
                e.pilaBatalla.ponerCarta(this);
                e.pilaDistancia.desbloquearPila();
            }
        }
    }
}

