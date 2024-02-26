package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Administrador;
import modelo.Jugador;
import vista.Ventana;

import javax.swing.*;

public class Controlador implements ActionListener{
    Administrador a;
    Ventana view;
    int jugadorActualPos = 0;
    int actualPos = 0;
    Jugador jugadorActual;

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
        view.getTb().setVisible(true);
        view.setVisible(false);
        a.iniciarJuego();
        jugadorActual = obtenerJugadorActual();
    }

    public Jugador obtenerJugadorActual(){
        if (actualPos<0 && (actualPos % 2 == 0)){
            jugadorActualPos++;
        }
            if (jugadorActualPos % 2 == 0){
                jugadorActual = a.getJ().getEquipos().get(0).getJugadores().get(jugadorActualPos);
            }
            else {
                jugadorActual = a.getJ().getEquipos().get(1).getJugadores().get(jugadorActualPos);
            }
            if (jugadorActual.getMano().size()<7)
                jugadorActual.robar();
            view.getTb().setButtonIcons(jugadorActual.getMano());
            return jugadorActual;
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
                a.j.setMaxPuntuacion(1000);
                iniciar(2);
                break;
            case "En Parejas":
                a.j.setMaxPuntuacion(3000);
                iniciar(4);
                break;
            case "En Trios":
                a.j.setMaxPuntuacion(4000);
                iniciar(6);
                break;
            default:
                break;
        }

        for (JButton boton : view.getTb().getCardButtons()) {
            if (e.getSource() == boton) {
                Jugador jugadorActual = obtenerJugadorActual();
                System.out.println(1);
                // Obtener el jugador actual según el estado del juego
                int indiceBoton = view.getTb().getCardButtons().indexOf(boton);
                int opt = view.getTb().accion();
                if(jugadorActual.tipoAccion(indiceBoton,a.getJ().getEquipo1(), a.getJ().getEquipo2(), opt)){
                    actualPos++;
                    obtenerJugadorActual();
                }
                break;
            }
        }
    }
}
