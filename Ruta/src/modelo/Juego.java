package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Juego {
    List<Equipo> equipos;
    int maxPuntuacion = 1000;
    Equipo equipo1;
    Equipo equipo2;
    Mazo m;

    public Juego(){
        equipo1 = new Equipo();
        equipo2 = new Equipo();
        equipos = new ArrayList<Equipo>();
        equipos.add(equipo1);
        equipos.add(equipo2);
        m = new Mazo();
    }

    public void iniciar(){
        Scanner scanner = new Scanner(System.in);
        int numJugadores;
        do {
            System.out.print("Ingrese el número de jugadores (2, 4 o 6): ");
            numJugadores = scanner.nextInt();
        } while (numJugadores != 2 && numJugadores != 4 && numJugadores != 6);
        
        for (int i = 0; i < numJugadores; i++) {
            if (i % 2 == 0) {
                Jugador jugador = crearJugador(scanner);
                equipo1.agregarJugador(jugador);
            } else {
                Jugador jugador = crearJugador(scanner);
                equipo2.agregarJugador(jugador);
            }
        }

        // Mostrar los equipos y sus miembros
        for (int i = 0; i < equipos.size(); i++) {
            System.out.println("Equipo " + (i + 1) + ":");
            for (Jugador jugador : equipos.get(i).getJugadores()) {
                System.out.println("- " + jugador.nombre);
            }
        }
    }
    
    public void repartir() {
        for (Equipo equipo : equipos) {
            List<Jugador> jugadores = equipo.getJugadores();
            for (int i = 0; i < 6; i++) { // Repartir 6 cartas
                for (Jugador jugador : jugadores) {
                    jugador.recibirCarta(m.dar()); // Entregar la carta al jugador
                }
            }
        }
    }

    private Jugador crearJugador(Scanner scanner) {
        System.out.print("Ingrese el nombre del jugador: ");
        String nombre = scanner.next();
        return new Jugador(nombre);
    }
}
