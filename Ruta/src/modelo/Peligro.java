package modelo;

public class Peligro extends Carta{

    public Peligro(String t){
        this.puntos=0;
        this.tipo = t;
    }

    public boolean accion(Equipo eC){

        if (this.tipo=="Límite de velocidad"&& !eC.revisionDeSeguridad("Vía libre")) {
            
            eC.pilaVelocidad.ponerCarta(this);

            return true;
        }else{
            if (eC.revisionDeSeguridad("Cisterna") && this.tipo == "Sin gasolina") {
                System.out.println("El equipo contrario tiene una cisterna");
                return false;
            } else if (eC.revisionDeSeguridad("Llanta irrompible") && this.tipo == "Pinchazo") {
                System.out.println("El equipo contrario tiene una llanta irrompible");
                return false;
            } else if (eC.revisionDeSeguridad("As al volante") && this.tipo == "Accidente") {
                System.out.println("El equipo contrario tiene un as al volante");
                return false;
            } else{
                if(!eC.pilaBatalla.isEmpty() && !(eC.pilaBatalla.cimaCarta().tipo == "Sin gasolina" || 
                eC.pilaBatalla.cimaCarta().tipo == "Pinchazo" || eC.pilaBatalla.cimaCarta().tipo == "Accidente")){

                    eC.pilaBatalla.ponerCarta(this);
                    eC.pilaDistancia.bloquearPila();
                    return true;

                }else{
                    return false;
                }
            }
        }
    }
}
