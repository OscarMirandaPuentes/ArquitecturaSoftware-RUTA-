package controlador;

import javax.swing.JOptionPane;

import conexiones.Servidor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.MenuInicio;
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
            
            //******* AQUI ESTA EL PROBLEMA DE QUE ME APARECE EL CONTROLLER SERVER COMO NULL ***********

            controlador = new ControllerCliente();
            controlador.cargarVista(eve.view.getTb());
            controlador.inicia();
            controlador.enviarMensajeAlServidor();


        } else if (e.getSource().equals(this.vista.crearSalaButton)) {

            this.vista.dispose();

            eve = new ControllerServidor();
            Ventana v=new Ventana(eve);
            eve.cargarVista(v);


        } else if ((e.getSource().equals(this.vista.salirButton))) {
            JOptionPane.showMessageDialog(this.vista, "Chao", "ADIOS", JOptionPane.INFORMATION_MESSAGE);
            this.vista.dispose();
        }

    }

}