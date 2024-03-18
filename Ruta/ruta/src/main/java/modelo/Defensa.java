package modelo;

public class Defensa extends Carta{

    public Defensa(String t){
        this.puntos=0;
        this.tipo = t;
    }

    public boolean accion(Equipo e){
        if (tipo.equals("Fin de límite") && (!e.pilaVelocidad.isEmpty() && e.pilaVelocidad.cimaCarta().tipo.equals("Límite de velocidad"))) {

            e.pilaVelocidad.ponerCarta(this);
            e.pilaDistancia.desbloquearPila();

            return true;

        }else{

            if (tipo.equals("Gasolina") && (!e.pilaBatalla.isEmpty() && e.pilaBatalla.cimaCarta().tipo.equals("Sin gasolina"))) {

                e.pilaBatalla.ponerCarta(this);
                e.pilaDistancia.desbloquearPila();
                System.err.println("Se ha puesto la carta sin ");
                return true;

            } else if (tipo.equals("Llanta de repuesto") && (!e.pilaBatalla.isEmpty() && e.pilaBatalla.cimaCarta().tipo.equals("Pinchazo"))) {

                e.pilaBatalla.ponerCarta(this);
                e.pilaDistancia.desbloquearPila();
                return true;

            } else if (tipo.equals("Reparacion") && (!e.pilaBatalla.isEmpty() && e.pilaBatalla.cimaCarta().tipo.equals("Accidente"))) {

                e.pilaBatalla.ponerCarta(this);
                e.pilaDistancia.desbloquearPila();
                return true;

            } else if (tipo.equals("Siga")) {
                System.out.println(1);
                if (e.pilaBatalla.isEmpty()) {

                    e.pilaBatalla.ponerCarta(this);
                    System.err.println("Se ha puesto una carta Siga");
                    e.pilaDistancia.desbloquearPila();
                    return true;

                } else {

                    Carta cartaEnBatalla = e.pilaBatalla.cimaCarta();
                    String tipoCartaEnBatalla = cartaEnBatalla.tipo;
                    
                    if (tipoCartaEnBatalla.equals("Gasolina") || tipoCartaEnBatalla.equals("Llanta de repuesto") ||
                    tipoCartaEnBatalla.equals("Reparación") || tipoCartaEnBatalla.equals("Pare")) {

                        e.pilaBatalla.ponerCarta(this);
                        System.err.println("Se ha puesto una carta Siga");
                        e.pilaDistancia.desbloquearPila();
                        return true;

                    } else {

                        return false;

                    }
                } 
            }
            return false;
        }
    }
}

