package modelo;

public class Defensa extends Carta{

    public Defensa(String t){
        this.puntos=0;
        this.tipo = t;
    }

    public boolean accion(Equipo e){
        if (tipo=="Fin de límite" && (e.pilaVelocidad.isEmpty() && e.pilaVelocidad.cimaCarta().tipo=="Límite de velocidad")) {
            e.pilaVelocidad.ponerCarta(this);
            return true;
        }else{
            if (tipo=="Gasolina" && (e.pilaBatalla.isEmpty() && e.pilaBatalla.cimaCarta().tipo=="Sin Gasolina")) {
                e.pilaBatalla.ponerCarta(this);
                e.pilaDistancia.desbloquearPila();
                System.err.println("Se ha puesto la carta sin ");
                return true;
            } else if (tipo=="Llanta de repuesto" && (e.pilaBatalla.isEmpty() && e.pilaBatalla.cimaCarta().tipo=="Pinchazo")) {
                e.pilaBatalla.ponerCarta(this);
                e.pilaDistancia.desbloquearPila();
                return true;
            } else if (tipo=="Reparación" && (e.pilaBatalla.isEmpty() && e.pilaBatalla.cimaCarta().tipo=="Accidente")) {
                e.pilaBatalla.ponerCarta(this);
                e.pilaDistancia.desbloquearPila();
                return true;
            } else if (tipo=="Siga") {
                e.pilaBatalla.ponerCarta(this);
                System.err.println("Se ha puesto una carta Siga");
                e.pilaDistancia.desbloquearPila();
                return true;
            }
        }
        return false;
    }
}

