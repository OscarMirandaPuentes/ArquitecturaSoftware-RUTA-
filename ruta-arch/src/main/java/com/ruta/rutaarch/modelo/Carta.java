package com.ruta.rutaarch.modelo;

import java.io.Serializable;

public abstract class Carta implements Serializable {
    private static final long serialVersionUID = -713898713776577970L;
    public int puntos;
    public String tipo;
    public boolean accion(Equipo e){return true;}
}