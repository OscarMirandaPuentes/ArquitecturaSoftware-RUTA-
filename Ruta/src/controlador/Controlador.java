package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Administrador;
import modelo.Jugador;
import vista.Ventana;

public class Controlador implements ActionListener{
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
        }
        a.iniciarJuego();
    }

    public void cargarVista (Ventana ev){
        this.view = ev;
    }

    //TODO:hacer los cambios de escenas
    //     Mandar info a logica
    //

    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {
            case "2 Jugadores":
                iniciar(2);
                break;
            case "En Parejas":
                iniciar(4);
                break;
            case "En Trios":
                iniciar(6);
                break;
            default:
                break;
        }
    }
}
