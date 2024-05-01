package com.ruta.rutaarch.modelo;

public class Anotador {
    public int puntuacion=0;
    private Equipo e;

    public Anotador(Equipo equipo) {
        e = equipo;
    }

    public void aumentarPuntuacion(Carta c){
        puntuacion += c.puntos;
        todaSeguridad();
    }

    private void todaSeguridad(){
        if (e.seguridad.size()==4) {
            puntuacion += 300;
        }
    }
}
