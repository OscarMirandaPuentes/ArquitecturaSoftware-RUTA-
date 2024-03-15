import java.io.IOException;

import conexiones.Servidor;

public class Launcher {
	public static void main(String[] args) throws IOException
    {
        Servidor server = new Servidor("localhost", 2);
        if (args.length > 0) {
            server.start(Integer.parseInt(args[0]));
            /* 
            // PANTALLA DE INICIO
            // PANTALLA DE SELECCION DE NUMERO DE JUGADORES
            // PANTALLA DE FINALIZACIÓN
            */
        } else {
            server.start(2396);
        }
    }
        
    }
