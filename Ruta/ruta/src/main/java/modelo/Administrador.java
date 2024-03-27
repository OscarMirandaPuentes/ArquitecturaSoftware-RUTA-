package modelo;


public class Administrador {
    public Juego j;

    public Administrador(){
        this.j=new Juego();
    }

    public void iniciarJuego(Mazo mazo){
        j.repartir(mazo);
    }

    public boolean finJuego(){
        if (j.equipo1.obtenerPuntaje()>j.getMaxPuntuacion() || j.equipo2.obtenerPuntaje()>j.getMaxPuntuacion()) {
            return true;
        }
        return false;
    }

    public Juego getJ() {
        return j;
    }

    public void setJ(Juego j) {
        this.j = j;
    }
}
