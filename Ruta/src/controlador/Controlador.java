package controlador;

import modelo.Administrador;
import modelo.Jugador;
import vista.Ventana;

public class Controlador {
    Administrador a;
    Ventana view;

    public Controlador() {
        this.a = new Administrador();
    }

    public void iniciar(int numJugadores) {
        for (int i = 0; i < numJugadores; i++) {
            if (i % 2 == 0) {
                Jugador jugador = a.getJ().crearJugador(view.pedirNombre());
                a.getJ().getEquipo1().agregarJugador(jugador);
            } else {
                Jugador jugador = a.getJ().crearJugador(view.pedirNombre());
                a.getJ().getEquipo2().agregarJugador(jugador);
            }
            a.iniciarJuego();
        }
    }

    public void gestionarTurno(){
        int totalJugadores = a.getJ().getEquipos().get(0).getJugadores().size(); // Obtener el número total de jugadores

        // Iterar sobre cada jugador, intercalando entre los equipos
        for (int i = 0; i < totalJugadores; i++) {
            // Jugador del equipo 1
            Jugador jugadorEquipo1 = a.getJ().getEquipos().get(0).getJugadores().get(i);
            jugadorEquipo1.jugar(a.getJ().getEquipo1(), a.getJ().getEquipo2(),1);
            // Jugador del equipo 2
            Jugador jugadorEquipo2 = a.getJ().getEquipos().get(1).getJugadores().get(i);
            jugadorEquipo2.jugar(a.getJ().getEquipo2(), a.getJ().getEquipo1(),1);
        }
    }

    public void cargarVista (Ventana ev){
        this.view = ev;
    }

    //TODO:hacer los cambios de escenas
    //     Mandar info a logica
    //
}
