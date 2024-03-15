import controlador.ControllerCliente;
import vista.TableroCliente;

public class LaucherCliente {
    public static void main(String[] args)
    {
        // Create a BigTwoClient object
        ControllerCliente game = new ControllerCliente();
        game.conectar(2);
        TableroCliente v=new TableroCliente(game);
        game.cargarVista(v);
        // Start the game with the shuffled deck
        // game.start(newDeck);
    }
}
