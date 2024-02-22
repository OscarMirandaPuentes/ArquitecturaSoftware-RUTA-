package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Collections;

public class Mazo {

    private List<Carta> mazo = new ArrayList<Carta>();
    Stack<Carta> pilaCartas = new Stack<>();

    public Mazo(){
        mazo();
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
            mazo.add(new Seguridad("Gasolina"));
            mazo.add(new Seguridad("Llanta de repuesto"));
            mazo.add(new Seguridad("Reparación"));
            mazo.add(new Seguridad("Fin de límite"));
        }
        
        for (int i = 0; i < 14; i++) {
            mazo.add(new Seguridad("Siga"));
        }
        
    }

    private void crearCartasSeguridad() {
        mazo.add(new Seguridad("Cisterna"));
        mazo.add(new Seguridad("Llanta irrompible"));
        mazo.add(new Seguridad("As al volante"));
        mazo.add(new Seguridad("Vía Libre"));
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
