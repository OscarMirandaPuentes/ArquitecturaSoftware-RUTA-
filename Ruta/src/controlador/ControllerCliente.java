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
import javax.swing.JPanel;

import conexiones.Cliente;
import conexiones.HiloServidor;
import conexiones.Servidor;
import modelo.Administrador;
import modelo.Jugador;

import vista.Tablero;
import vista.TableroCliente;
import vista.Ventana;

public class ControllerCliente implements ActionListener {
	
 
    Administrador a;
    String nombre;
    int jugadorActualPos = 0;
    int actualPos = 0;
    Jugador jugadorActual;
    public TableroCliente tb;
    Cliente cli;

    public ControllerCliente(String nombre) {    
        this.nombre = nombre;
        this.a = new Administrador();
        crearCliente();
    }
    public void inicia(){
        System.out.println("entra a mostrar el tablero");
        tb.setVisible(true);
        jugadorActual = obtenerJugadorActual();
        
    }

    public void crearCliente(){

        cli = new Cliente(); 

        System.out.println("Nuevo Cliente\n");
        cli.setNombre(nombre);;
        enviarMensajeAlServidor();

        Thread clientThread = new Thread(cli);
        clientThread.start();
        
        //cli.run();

    }

    public void enviarMensajeAlServidor(){
        this.cli.enviarMsg("hello world");
    }

    public TableroCliente getTb(){
        return tb;

    }
    public void setTb(TableroCliente tb){
        this.tb = tb;
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
            tb.setPilas(a.getJ().getEquipo1(), a.getJ().getEquipo2(), jugadorActual.getNombre());
        }

        tb.setButtonIcons(jugadorActual.getMano());


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

	public void cargarVista (TableroCliente ev){
	    this.tb = ev;
	}
	
	public void actionPerformed(ActionEvent e) {
	
	    for (JButton boton : tb.getCardButtons()) {
	        if (e.getSource() == boton) {
	            Jugador jugadorActual = obtenerJugadorActual();
	            // Obtener el jugador actual según el estado del juego
	            int indiceBoton = tb.getCardButtons().indexOf(boton);
	            int opt = tb.accion();
	
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