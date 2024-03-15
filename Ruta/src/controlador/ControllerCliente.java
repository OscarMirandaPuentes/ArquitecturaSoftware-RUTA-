package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import conexiones.Cliente;
import modelo.Administrador;
import modelo.Carta;
import modelo.Jugador;

import vista.TableroCliente;

public class ControllerCliente implements ActionListener {
	
 
    Administrador a;
    int jugadorActualPos = 0;
    int actualPos = 0;
    Jugador jugadorActual;
    public TableroCliente tb;
    Cliente cli;

    
    public ControllerCliente() {
        this.a = new Administrador();
    }
    public void conectar(int num){
        crearCliente(num);
        
    }
    
    public void crearCliente(int num){
        
        cli = new Cliente(num);
		cli.setCli(this);
        
    }

	public void mostratVista(){
		tb.setVisible(true);
	}


    public TableroCliente getTb(){
        return tb;

    }
    public void setTb(TableroCliente tb){
        this.tb = tb;
    }
    
    
    public Jugador obtenerJugadorActual(int id){
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

		if (id == actualPos){
			tb.setButtonIcons(jugadorActual.getMano());
			tb.getPlayerHandPanel().setVisible(true);
		}
		else {
			tb.getPlayerHandPanel().setVisible(false);
		}

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
	            // Obtener el jugador actual según el estado del juego
				Jugador jugadorActual = obtenerJugadorActual(actualPos);
	            int indiceBoton = tb.getCardButtons().indexOf(boton);
	            int opt = tb.accion();
				Carta carta = jugadorActual.getMano().get(indiceBoton);

	            if (a.getJ().getEquipo1().encuentraJugador(jugadorActual.nombre)) {
	                if(jugadorActual.tipoAccion(indiceBoton,a.getJ().getEquipo1(), a.getJ().getEquipo2(), opt)){
						cli.makeMove(carta);
	                }
	                break;
	            }else{
	                if(jugadorActual.tipoAccion(indiceBoton,a.getJ().getEquipo2(), a.getJ().getEquipo1(), opt)){
						cli.makeMove(carta);
	                }
	                break;
	            }
	        }
	    }
	}

	public void refreshView(){
		tb.setPilas(a.getJ().getEquipo1(), a.getJ().getEquipo2(), jugadorActual.getNombre());
	}
	public Administrador getA() {
		return a;
	}

	public void setA(Administrador a) {
		this.a = a;
	}
}