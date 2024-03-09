import java.io.IOException;
import java.net.Socket;

import controlador.ControllerCliente;
import controlador.Controlador;
import controlador.ControllerServidor;
import vista.Ventana;
import vista.Ventana;

public class Launcher {
	public static void main(String[] args) throws IOException
    {
        ControllerServidor serv = new ControllerServidor(); //Se crea el servidor

        System.out.println("Iniciando servidor\n");
        //Controlador eve = new Controlador();
        Ventana v=new Ventana(serv);
        serv.cargarVista(v);
        serv.escuchar(); //Se inicia el servidor
    }
        

        

   
    	/*
    	Controlador eve = new Controlador();

        Ventana v=new Ventana(eve);
        eve.cargarVista(v);
        */
    }
