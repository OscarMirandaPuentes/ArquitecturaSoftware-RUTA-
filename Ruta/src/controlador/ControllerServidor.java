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
import modelo.Administrador;
import modelo.Jugador;
import vista.Ventana;

public class ControllerServidor implements ActionListener{
	
    //Inicializamos el puerto y el numero maximo de conexciones que acepta el servidor
    private final int puerto = 2027;
    private final int noConexiones = 20;
    Administrador a;
    Ventana view;
    int jugadorActualPos = 0;
    int actualPos = 0;
    Jugador jugadorActual;
    //Creamos una lista de sockets, donde guardaremos los sockets que se vayan conectando
    private LinkedList<Socket> usuarios = new LinkedList<Socket>();
    
    public ControllerServidor() {
    	 this.a = new Administrador();
    }
       
   //Funcion para que el servidor empieze a recibir conexiones de clientes
    public void escuchar() {
        try {
            ServerSocket servidor = new ServerSocket(puerto);
            while (true) {
                System.out.println("Escuchando....");
                Socket cliente = servidor.accept();
                usuarios.add(cliente);

                // Instanciamos un hilo para manejar la comunicación con el cliente
                Runnable run = new HiloServidor(cliente, usuarios);
                Thread hilo = new Thread(run);
                hilo.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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