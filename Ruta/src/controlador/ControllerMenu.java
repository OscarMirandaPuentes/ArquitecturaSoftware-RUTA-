package controlador;

import javax.swing.JOptionPane;

import conexiones.Servidor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.MenuInicio;
import vista.Ventana;

public class ControllerMenu implements ActionListener {

    private MenuInicio vista;


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
            String nombre = JOptionPane.showInputDialog(this.vista, "Nombre jugador 2", "Nombre jugador", JOptionPane.QUESTION_MESSAGE);
            //ControllerCliente controlador = new ControllerCliente(new JugarCliente(nombre,jugadores));
        } else if (e.getSource().equals(this.vista.crearSalaButton)) {
            //String nombre = JOptionPane.showInputDialog(this.vista, "Nombre jugador 1", "Nombre jugador", JOptionPane.QUESTION_MESSAGE);
            //Thread hilo = new Thread(new SalaDeEspera(this.vista, false, nombre, jugadores));
            //hilo.start();
            this.vista.dispose();

            ControllerServidor eve = new ControllerServidor();
            Ventana v=new Ventana(eve);
            eve.cargarVista(v);


        } else if ((e.getSource().equals(this.vista.salirButton))) {
            JOptionPane.showMessageDialog(this.vista, "Chao", "ADIOS", JOptionPane.INFORMATION_MESSAGE);
            this.vista.dispose();
        }

    }

}