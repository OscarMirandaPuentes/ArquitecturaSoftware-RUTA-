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

    public void cargarVista (Ventana ev){
        this.view = ev;
    }

    //TODO:hacer los cambios de escenas
    //     Mandar info a logica
    //
}
