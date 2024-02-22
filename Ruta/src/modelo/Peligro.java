package modelo;

public class Peligro extends Carta{

    public Peligro(String t){
        this.puntos=0;
        this.tipo = t;
    }

    public void accion(Equipo eC){

        if (this.tipo=="Límite de velocidad"&& !eC.revisionDeSeguridad("Vía libre")) {
            eC.pilaVelocidad.ponerCarta(this);
        }else{
            if (eC.revisionDeSeguridad("Cisterna") && this.tipo == "Sin gasolina") {
                System.out.println("El equipo contrario tiene una cisterna");
            } else if (eC.revisionDeSeguridad("Llanta irrompible") && this.tipo == "Pinchazo") {
                System.out.println("El equipo contrario tiene una llanta irrompible");
            } else if (eC.revisionDeSeguridad("As al volante") && this.tipo == "Accidente") {
                System.out.println("El equipo contrario tiene un as al volante");
            } else{
                eC.pilaBatalla.ponerCarta(this);
                eC.pilaDistancia.bloquearPila();
            }
        }
    }
}
