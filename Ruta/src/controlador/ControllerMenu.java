package controlador;

import javax.swing.JOptionPane;

import conexiones.Servidor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.MenuInicio;
import vista.Tablero;
import vista.TableroCliente;
import vista.Ventana;

public class ControllerMenu implements ActionListener {

    private MenuInicio vista;
    public ControllerCliente controlador;
    public ControllerServidor eve;



    public ControllerMenu(MenuInicio vista) {
        this.vista = vista;
        this.vista.setVisible(true);
        this.vista.setFocusable(true);
        this.vista.setFocusTraversalKeysEnabled(false);
        this.vista.crearSalaButton.addActionListener(this);
        this.vista.unirseButton.addActionListener(this);
        this.vista.salirButton.addActionListener(this);
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.vista.unirseButton)) {
            this.vista.dispose();
            
            //****** SE DEBE TENER LA COMUNICACION ENTRE SERVIDOR CLIENTE, SE ACTUALIZA LAS VISTAS SEGUN CORRESPONDA ***********
            String nombre = JOptionPane.showInputDialog(this.vista, "Nombre jugador 2", "Nombre jugador", JOptionPane.QUESTION_MESSAGE);
            controlador = new ControllerCliente(nombre);
            TableroCliente v=new TableroCliente(controlador);
            controlador.cargarVista(v);
            controlador.inicia();
            System.out.println("desde menu inicial");
            controlador.enviarMensajeAlServidor();
            //controlador.enviarMensajeAlServidor();


        } else if (e.getSource().equals(this.vista.crearSalaButton)) {

            this.vista.dispose();
            String nombre = JOptionPane.showInputDialog(this.vista, "Nombre jugador 1", "Nombre jugador", JOptionPane.QUESTION_MESSAGE);
            eve = new ControllerServidor(nombre);
            Ventana v=new Ventana(eve);
            eve.cargarVista(v);
            //eve.iniciarServidor(0);
            //eve.realizarAccionEnJuego();


        } else if ((e.getSource().equals(this.vista.salirButton))) {
            JOptionPane.showMessageDialog(this.vista, "Chao", "ADIOS", JOptionPane.INFORMATION_MESSAGE);
            this.vista.dispose();
        }

    }

}