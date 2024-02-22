package modelo;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;   

public class Jugador {

    public String nombre;
    public List<Carta> mano;
    Scanner scanner = new Scanner(System.in);

    public Jugador(String n){
        this.nombre = n;
        mano = new ArrayList<Carta>();
    }

    public void recibirCarta(Carta c){
        mano.add(c);
    }

    public void jugar(Equipo e, Equipo eC){
        robar();

        // Mostrar la mano del jugador
        System.out.println("Jugador "+this.nombre+" su mano:");
        for (int i = 0; i < mano.size(); i++) {
            System.out.println((i+1) + ". " + mano.get(i).tipo); 
        }

        // Pedir al jugador que elija una carta
        System.out.print("Por favor, elija una carta (ingrese el número correspondiente): ");
        int opcion = scanner.nextInt();

        if (opcion >= 1 && opcion <= mano.size()) {
            tipoAccion(opcion-1, e, eC);
        } else {
            System.out.println("Opción inválida. Por favor, seleccione un número válido.");
        }
    }

    private void robar(){
        Mazo m = new Mazo();
        mano.add(m.dar());
    }

    private void descartar(int o) {
        Descarte descarte=new Descarte();
        descarte.recibirCarta(mano.remove(o));
    } 

    private void tipoAccion(int o, Equipo e, Equipo eC) {
        Carta cartaSeleccionada = mano.get(o);
            
        System.out.print("¿Qué desea hacer con la carta seleccionada?\n1. Jugar\n2. Descartar\nSeleccione una opción: ");
        int opcionAccion = scanner.nextInt();

        if (opcionAccion == 1) {

            System.out.println("Ha seleccionado jugar la carta: " + cartaSeleccionada.tipo);
            
            if (cartaSeleccionada.getClass() == Peligro.class) {
                e.anotador.aumentarPuntuacion(cartaSeleccionada);
                e.atacar(cartaSeleccionada, eC);
            } else {
                System.err.println(cartaSeleccionada.getClass());
                e.anotador.aumentarPuntuacion(cartaSeleccionada);
                cartaSeleccionada.accion(e);
            }

        } else if (opcionAccion == 2) {

            descartar(o);
            System.out.println("Ha seleccionado descartar la carta.");

        } else {

            System.out.println("Opción inválida. Por favor, seleccione una opción válida.");

        }
    }
}
