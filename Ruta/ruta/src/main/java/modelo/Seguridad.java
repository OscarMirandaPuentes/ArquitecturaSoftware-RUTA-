package modelo;

public class Seguridad extends Carta{

    public Seguridad(String t){
        this.puntos=100;
        this.tipo=t;
    }

    public boolean accion(Equipo e) {

        e.seguridad.add(this);
        Carta cimaBatalla = e.pilaBatalla.cimaCarta();

        if (cimaBatalla!=null) {

            if (this.tipo=="Cisterna" && cimaBatalla.tipo=="Sin Gasolina") {

                e.pilaDistancia.desbloquearPila();

            } else if (this.tipo=="Llanta irrompible" && cimaBatalla.tipo=="Pinchazo"){

                e.pilaDistancia.desbloquearPila();

            } else if (this.tipo=="As al volante" && cimaBatalla.tipo=="Accidente"){

                e.pilaDistancia.desbloquearPila();

            }
        }
       
        return true;
        
    }
    
}
