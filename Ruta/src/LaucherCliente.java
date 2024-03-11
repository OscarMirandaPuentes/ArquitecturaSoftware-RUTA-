import conexiones.Cliente;
import controlador.ControllerCliente;
import vista.TableroCliente;
import vista.Ventana;

public class LaucherCliente {
    public static void main(String[] args)
    {
        // Create a BigTwoClient object
        ControllerCliente game = new ControllerCliente();
        game.conectar();
        TableroCliente v=new TableroCliente(game);
        game.cargarVista(v);
        // Start the game with the shuffled deck
        // game.start(newDeck);
    }
}
