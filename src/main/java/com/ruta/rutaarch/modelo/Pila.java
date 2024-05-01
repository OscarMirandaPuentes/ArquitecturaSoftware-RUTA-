package com.ruta.rutaarch.modelo;
import java.util.EmptyStackException;
import java.util.Stack;

public class Pila {

    private Stack<Carta> cartas;
    private boolean bloqueada;

    public Pila() {
        this.cartas = new Stack<>();
        this.bloqueada = false;
    }

    public void ponerCarta(Carta carta) {
        if (!bloqueada) {
            cartas.push(carta);
            // Verificar si la carta puesta bloquea la pila
        } else {
            System.out.println("La pila está bloqueada, no se puede poner más cartas.");
        }
    }

    public Carta quitarCarta() {
        if (!cartas.isEmpty()) {
            // Devolver y quitar la carta de la cima de la pila
            Carta carta = cartas.pop();
            return carta;
        } else {
            System.out.println("La pila está vacía, no se puede quitar más cartas.");
            return null;
        }
    }

    public Carta cimaCarta() {
        try {
            return cartas.peek(); // Obtener la carta en la cima sin quitarla de la pila
        } catch (EmptyStackException e) {
            //System.out.println("La pila está vacía.");
            return null;
        }
    }

    public void bloquearPila() {
        bloqueada = true;
    }

    public void desbloquearPila(){
        bloqueada = false;
    }

    public boolean isEmpty(){
        return cartas.isEmpty();
    }
}
