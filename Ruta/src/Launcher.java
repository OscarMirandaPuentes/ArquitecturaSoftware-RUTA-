import controlador.Controlador;
import vista.Ventana;

public class Launcher {
    public static void main(String[] args) throws Exception {
        Controlador eve = new Controlador();

        Ventana v=new Ventana(eve);
        eve.cargarVista(v);
    }
}
