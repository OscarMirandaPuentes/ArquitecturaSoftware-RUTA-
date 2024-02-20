package modelo;

public class Anotador {
    public int puntuacion=0;

    public void aumentarPuntuacion(Carta c){
        puntuacion += c.puntos;
    }
}
