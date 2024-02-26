package modelo;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;   

public class Jugador {

    public String nombre;
    public List<Carta> mano;
    Scanner scanner = new Scanner(System.in);

    public Jugador(String n){
        this.nombre = n;
        mano = new ArrayList<Carta>();
    }

    public void recibirCarta(Carta c){
        mano.add(c);
    }

    public void jugar(Equipo e, Equipo eC, int opcion){
        boolean turno = false;
        robar();
        while (!turno) {

            if (opcion >= 1 && opcion <= mano.size()) {
                turno = tipoAccion(opcion - 1, e, eC, opcion);
            } else {
                System.out.println("Opción inválida. Por favor, seleccione un número válido.");
            }
        }
    }

    private void robar(){
        Mazo m = new Mazo();
        mano.add(m.dar());
    }

    private void descartar(int o) {
        Descarte descarte=new Descarte();
        descarte.recibirCarta(mano.remove(o));
    }


    private boolean tipoAccion(int o, Equipo e, Equipo eC, int opcionAccion) {
        Carta cartaSeleccionada = mano.get(o);


        if (opcionAccion == 1) {

            System.out.println("Ha seleccionado jugar la carta: " + cartaSeleccionada.tipo);
            
            if (cartaSeleccionada.getClass() == Peligro.class) {
                e.anotador.aumentarPuntuacion(cartaSeleccionada);
                boolean posible = e.atacar(cartaSeleccionada, eC);
                if (posible)
                    descartar(o);
                return posible;
            }
            else if (cartaSeleccionada.getClass() == Defensa.class) {
                e.anotador.aumentarPuntuacion(cartaSeleccionada);
                boolean posible = cartaSeleccionada.accion(e);
                if (posible)
                    descartar(o);
                return posible;
            } else {
                System.err.println(cartaSeleccionada.getClass());
                boolean posible = cartaSeleccionada.accion(e);
                if (posible) {
                    e.anotador.aumentarPuntuacion(cartaSeleccionada);
                    System.out.println(e.obtenerPuntaje());
                    descartar(o);
                }
                return posible;
            }

        } else if (opcionAccion == 2) {

            descartar(o);
            System.out.println("Ha seleccionado descartar la carta.");

        } else {

            System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            return false;

        }
        return false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Carta> getMano() {
        return mano;
    }

    public void setMano(List<Carta> mano) {
        this.mano = mano;
    }
}
