package com.ruta.rutaarch.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Collections;

public class Mazo  implements Serializable {
    private static final long serialVersionUID = -3711761437629470849L;
    private static Mazo instancia = null;
    private List<Carta> mazo = new ArrayList<Carta>();
    Stack<Carta> pilaCartas = new Stack<>();

    public Mazo(){
        mazo();
    }

     public static Mazo getInstance() {
        if (instancia == null) {
            instancia = new Mazo();
        }
        return instancia;
    }

    private void mazo() {
        crearCartasPeligro();
        crearCartasSoluciones();
        crearCartasSeguridad();
        crearCartasDistancia();
        mezclarCartas();
        pasarCartasAPila();
    }

    private void crearCartasPeligro() {

        for (int i = 0; i < 3; i++) {
            mazo.add(new Peligro("Sin gasolina"));
            mazo.add(new Peligro("Pinchazo"));
            mazo.add(new Peligro("Accidente"));
        }

        for (int i = 0; i < 4; i++) {
            mazo.add(new Peligro("Límite de velocidad"));
        }
        
        for (int i = 0; i < 5; i++) {
            mazo.add(new Peligro("Pare"));
        }   
    }

    private void crearCartasSoluciones() {

        for (int i = 0; i < 6; i++) {
            mazo.add(new Defensa("Gasolina"));
            mazo.add(new Defensa("Llanta de repuesto"));
            mazo.add(new Defensa("Reparación"));
            mazo.add(new Defensa("Fin de límite"));
        }
        
        for (int i = 0; i < 14; i++) {
            mazo.add(new Defensa("Siga"));
        }
        
    }

    private void crearCartasSeguridad() {
        mazo.add(new Seguridad("Cisterna"));
        mazo.add(new Seguridad("Llanta irrompible"));
        mazo.add(new Seguridad("As al volante"));
        mazo.add(new Seguridad("Vía libre"));
    }

    private void crearCartasDistancia() {
        for (int i = 0; i < 4; i++) {
            mazo.add(new Distancia(200));
        }
        for (int i = 0; i < 12; i++) {
            mazo.add(new Distancia(100));
        }
        for (int i = 0; i < 10; i++) {
            mazo.add(new Distancia(75));
            mazo.add(new Distancia(50));
            mazo.add(new Distancia(25));   
        }
    }

    private void mezclarCartas() {
        Collections.shuffle(mazo);
    }

    private void pasarCartasAPila() {
        for (Carta carta : mazo) {
            pilaCartas.push(carta);
        }
    }

    public Carta dar(){
        return pilaCartas.pop();
    }

}
