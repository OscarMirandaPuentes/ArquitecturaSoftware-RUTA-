package modelo;

public class Defensa extends Carta{

    public Defensa(String t){
        this.puntos=0;
        this.tipo = t;
    }

    public void accion(Equipo e){
        System.err.println("Hola    ");
        if (tipo=="Fin de límite" && e.pilaVelocidad.cimaCarta().tipo=="Límite de velocidad") {
            e.pilaVelocidad.ponerCarta(this);
        }else{
            if (tipo=="Gasolina" && e.pilaBatalla.cimaCarta().tipo=="Sin Gasolina") {
                e.pilaBatalla.ponerCarta(this);
                e.pilaDistancia.desbloquearPila();
                System.err.println("Se ha puesto la carta sin ");
            } else if (tipo=="Llanta de repuesto" && e.pilaBatalla.cimaCarta().tipo=="Pinchazo") {
                e.pilaBatalla.ponerCarta(this);
                e.pilaDistancia.desbloquearPila();
            } else if (tipo=="Reparación" && e.pilaBatalla.cimaCarta().tipo=="Accidente") {
                e.pilaBatalla.ponerCarta(this);
                e.pilaDistancia.desbloquearPila();
            } else if (tipo=="Siga") {
                e.pilaBatalla.ponerCarta(this);
                System.err.println("Se ha puesto una carta Siga");
                e.pilaDistancia.desbloquearPila();
            }
        }
    }
}

