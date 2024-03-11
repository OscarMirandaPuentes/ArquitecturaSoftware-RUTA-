package modelo;

import java.util.ArrayList;
import java.util.List;

public class Jugador {

    public int id;
    public String nombre;
    Mazo m = Mazo.getInstance();
    public List<Carta> mano;

    public Jugador(String n){
        this.nombre = n;
        mano = new ArrayList<Carta>();
    }

    public void recibirCarta(Carta c){
        mano.add(c);
    }

    public void robar(){

        mano.add(m.dar());
    }

    private void descartar(int o) {
        Descarte descarte=new Descarte();
        descarte.recibirCarta(mano.remove(o));
    }


    public boolean tipoAccion(int o, Equipo e, Equipo eC, int opcionAccion) {
        Carta cartaSeleccionada = mano.get(o);

        if (opcionAccion == 0) {

            System.out.println("Ha seleccionado jugar la carta: " + cartaSeleccionada.tipo);
            
            if (cartaSeleccionada.getClass() == Peligro.class) {
                e.anotador.aumentarPuntuacion(cartaSeleccionada);
                boolean posible = e.atacar(cartaSeleccionada, eC);
                if (posible){
                    descartar(o);
                }
                return posible;
            }
            else if (cartaSeleccionada.getClass() == Defensa.class) {
                e.anotador.aumentarPuntuacion(cartaSeleccionada);
                boolean posible = cartaSeleccionada.accion(e);
                if (posible)
                    descartar(o);
                return posible;
            } else {
                boolean posible = cartaSeleccionada.accion(e);
                if (posible) {
                    e.anotador.aumentarPuntuacion(cartaSeleccionada);
                    System.out.println(e.obtenerPuntaje());
                    descartar(o);
                }
                return posible;
            }

        } else if (opcionAccion == 1) {

            descartar(o);
            System.out.println("Ha seleccionado descartar la carta.");
            return true;

        } else {

            System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            return false;

        }
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

