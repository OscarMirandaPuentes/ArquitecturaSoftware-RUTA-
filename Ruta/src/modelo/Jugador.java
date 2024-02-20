package modelo;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;   

public class Jugador {

    public List<Carta> mano;

    public void jugar(){
        robar();
        /**
         * Se muestra la mano del jugador para que elija que hacer, se hacen comprobaciones necesarias
         * Segun la accion del jugador se opta por caminos diferentes
         * */  
    }

    private void robar(){
        // Arreglar acceso al mazo
        Mazo m = new Mazo();
        Carta c= m.dar();
        //Llamada a método para tomar carta del mazo
        if (aceptar()) {
            mano.add(c);
            // Mostrar mano al usuario
            descartar();
        }
    }

    private void descartar() {
        /**
         * Código para dejar la carta que se elige del mazo en el descarte.
         * La mano del jugador debe finalizar con 6 cartas
         *  */ 

    }

    private boolean aceptar() {
        System.out.println("Desea aceptar la carta robada");
        System.out.println("1. Si");
        System.out.println("2. No");
        String o = new Scanner(System.in).nextLine(); 

        if (o == "1") {
            return true;
        } else {
            return false;
        }
    }
}
