package modelo;

public class Seguridad extends Carta{

    public Seguridad(String t){
        this.puntos=100;
        this.tipo=t;
    }

    public void accion(Equipo e) {
        e.seguridad.add(this);
        if (this.tipo=="Cisterna" && e.pilaBatalla.cimaCarta().tipo=="Sin Gasolina") {
            e.pilaDistancia.desbloquearPila();
        } else if (this.tipo=="Llanta irrompible" && e.pilaBatalla.cimaCarta().tipo=="Pinchazo"){
            e.pilaDistancia.desbloquearPila();
        } else if (this.tipo=="As al volante" && e.pilaVelocidad.cimaCarta().tipo=="Accidente"){
            e.pilaDistancia.desbloquearPila();
        }

    }
    
}
