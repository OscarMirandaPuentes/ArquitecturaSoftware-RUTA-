package modelo;

public class Anotador {
    public int puntuacion=0;

    public void aumentarPuntuacion(Carta c){
        if(c.getClass().isInstance(new Distancia())){
            puntuacion += c.puntos;
        }
    }
}
