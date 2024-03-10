package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import javax.swing.JButton;

import conexiones.HiloServidor;
import conexiones.Servidor;
import modelo.Administrador;
import modelo.Jugador;
import vista.Ventana;

public class ControllerServidor implements ActionListener{
	
    //Inicializamos el puerto y el numero maximo de conexciones que acepta el servidor
    
    Administrador a;
    Ventana view;
    int jugadorActualPos = 0;
    int actualPos = 0;
    Jugador jugadorActual;
    private Servidor servidor;

    // ...

    
    public ControllerServidor() {
        this.a = new Administrador();
    }

    public void iniciarServidor(int i){
        Servidor serv = new Servidor(); //Se crea el servidor
        System.out.println("Iniciando servidor\n");
        serv.cantidadConexiones(i);
        serv.escuchar(); //Se inicia el servidor
    }


    
    // Method to send a message to all connected clients
    private void enviarMensajeAlServidor(String mensaje) {
        servidor.broadcastMessage(mensaje);
    }

    // ...

    // Example game action where a message is sent to all clients
    public void realizarAccionEnJuego() {
        // ... perform game action ...
        // Send a message to all connected clients
        enviarMensajeAlServidor("Alguna acción ha ocurrido en el juego.");
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
        if (actualPos % 2 == 0){
            jugadorActual = a.getJ().getEquipos().get(0).getJugadores().get(jugadorActualPos);
        }
        else {
            jugadorActual = a.getJ().getEquipos().get(1).getJugadores().get(jugadorActualPos);
        }
        if (jugadorActual.getMano().size()<7) {
            jugadorActual.robar();
            view.getTb().setPilas(a.getJ().getEquipo1(), a.getJ().getEquipo2(), jugadorActual.getNombre());
        }

        view.getTb().setButtonIcons(jugadorActual.getMano());


        return jugadorActual;
}

	public void validarPosicionJugador() {
	    actualPos++;
	    if (actualPos == a.getJ().getEquipo1().getJugadores().size() * 2) {
	        jugadorActualPos = 0;
	        actualPos = 0;
	    }
	    else if (actualPos % 2 == 0 && actualPos != 0) {
	        jugadorActualPos++;
	    }
	}

	public void cargarVista (Ventana ev){
	    this.view = ev;
	}
	
	public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
	    switch (comando) {
            case "2 Jugadores":
                this.iniciarServidor(2);
	            a.j.setMaxPuntuacion(1000);
	            iniciar(2);
	            break;
	        case "En Parejas":
                this.iniciarServidor(4);
	            a.j.setMaxPuntuacion(3000);
	            iniciar(4);
	            break;
	        case "En Trios":
                this.iniciarServidor(6);
	            a.j.setMaxPuntuacion(4000);
	            iniciar(6);
	            break;
	        default:
	            break;
	    }
	
	    for (JButton boton : view.getTb().getCardButtons()) {
	        if (e.getSource() == boton) {
	            Jugador jugadorActual = obtenerJugadorActual();
	            // Obtener el jugador actual según el estado del juego
	            int indiceBoton = view.getTb().getCardButtons().indexOf(boton);
	            int opt = view.getTb().accion();
	
	            if (a.getJ().getEquipo1().encuentraJugador(jugadorActual.nombre)) {
	                if(jugadorActual.tipoAccion(indiceBoton,a.getJ().getEquipo1(), a.getJ().getEquipo2(), opt)){
	                    validarPosicionJugador();
	                    obtenerJugadorActual();
	                }
	                break;
	            }else{
	                if(jugadorActual.tipoAccion(indiceBoton,a.getJ().getEquipo2(), a.getJ().getEquipo1(), opt)){
	                    validarPosicionJugador();
	                    obtenerJugadorActual();
	                }
	                break;
	            }
	        }
	    }
	}
    
}