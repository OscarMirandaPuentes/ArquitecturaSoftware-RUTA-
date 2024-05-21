package com.ruta.rutaarch.modelo;

import java.util.HashMap;

public class CartasMap {
    HashMap<String, String> cartasPorTipo = new HashMap<>();
    HashMap<String, String> cartasCounter = new HashMap<>();
    HashMap<String, String> cartasInmunidad = new HashMap<>();
    HashMap<String, String> pilaLlegada = new HashMap<>();

    public CartasMap() {
        llenarCounter();
        llenarInmunidad();
        llenarTipo();
        llenarPila();
    }

    public void llenarTipo(){
        cartasPorTipo.put("Sin gasolina", "Peligro");
        cartasPorTipo.put("Pinchazo", "Peligro");
        cartasPorTipo.put("Accidente", "Peligro");
        cartasPorTipo.put("Límite de velocidad", "Peligro");
        cartasPorTipo.put("Pare", "Peligro");
        cartasPorTipo.put("Gasolina", "Defensa");
        cartasPorTipo.put("Llanta de repuesto", "Defensa");
        cartasPorTipo.put("Reparación", "Defensa");
        cartasPorTipo.put("Fin de límite", "Defensa");
        cartasPorTipo.put("Siga", "Defensa");
        cartasPorTipo.put("Cisterna", "Seguridad");
        cartasPorTipo.put("Llanta irrompible", "Seguridad");
        cartasPorTipo.put("As al volante", "Seguridad");
        cartasPorTipo.put("Vía libre", "Seguridad");
        cartasPorTipo.put("200", "Distancia");
        cartasPorTipo.put("100", "Distancia");
        cartasPorTipo.put("75", "Distancia");
        cartasPorTipo.put("50", "Distancia");
        cartasPorTipo.put("25", "Distancia");
    }

    public void llenarCounter(){
        cartasCounter.put("Gasolina", "Sin gasolina");
        cartasCounter.put("Llanta de repuesto", "Pinchazo");
        cartasCounter.put("Reparación", "Accidente");
        cartasCounter.put("Fin de límite", "Límite de velocidad");
        cartasCounter.put("Siga", "Pare");
    }

    public void llenarInmunidad(){
        cartasInmunidad.put("Sin gasolina", "Cisterna");
        cartasInmunidad.put("Pinchazo", "Llanta irrompible");
        cartasInmunidad.put("Accidente", "As al volante");
        cartasInmunidad.put("Límite de velocidad", "Vía libre");
    }

    public void llenarPila(){
        pilaLlegada.put("Sin gasolina", "pilaBatalla");
        pilaLlegada.put("Pinchazo", "pilaBatalla");
        pilaLlegada.put("Accidente", "pilaBatalla");
        pilaLlegada.put("Límite de velocidad", "pilaVelocidad");
        pilaLlegada.put("Pare", "pilaBatalla");
        pilaLlegada.put("Gasolina", "pilaBatalla");
        pilaLlegada.put("Llanta de repuesto", "pilaBatalla");
        pilaLlegada.put("Reparación", "pilaBatalla");
        pilaLlegada.put("Fin de límite", "pilaVelocidad");
        pilaLlegada.put("Siga", "pilaBatalla");
        pilaLlegada.put("Cisterna", "Seguridad");
        pilaLlegada.put("Llanta irrompible", "Seguridad");
        pilaLlegada.put("As al volante", "Seguridad");
        pilaLlegada.put("Vía libre", "Seguridad");
        pilaLlegada.put("200", "pilaDistancia");
        pilaLlegada.put("100", "pilaDistancia");
        pilaLlegada.put("75", "pilaDistancia");
        pilaLlegada.put("50", "pilaDistancia");
        pilaLlegada.put("25", "pilaDistancia");
    }

    public HashMap<String, String> getCartasPorTipo() {
        return cartasPorTipo;
    }

    public HashMap<String, String> getCartasCounter() {
        return cartasCounter;
    }

    public HashMap<String, String> getCartasInmunidad() {
        return cartasInmunidad;
    }

    public HashMap<String, String> getPilaLlegada() {
        return pilaLlegada;
    }

}
