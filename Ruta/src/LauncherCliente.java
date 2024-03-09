import java.io.IOException;
import java.net.Socket;

import controlador.ControllerCliente;
import controlador.ControllerServidor;

public class LauncherCliente {
	public static void main(String[] args) throws IOException
    {
        ControllerCliente cli = new ControllerCliente(); //Se crea el cliente

        System.out.println("Iniciando cliente\n");
        cli.run(); //Se inicia el cliente
    }
}
 

