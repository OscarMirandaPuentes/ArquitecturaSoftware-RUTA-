package modelo;

public class Administrador {
    public Juego j;

    public Administrador(){
        this.j=new Juego();
    }

    public void iniciarJuego(){
        j.repartir();
        while (!finJuego()) {
            gestionarTurno();
        }
    }

    public void gestionarTurno(){
        int totalJugadores = j.equipos.get(0).getJugadores().size(); // Obtener el número total de jugadores
        
        // Iterar sobre cada jugador, intercalando entre los equipos
        for (int i = 0; i < totalJugadores; i++) {
            // Jugador del equipo 1
            Jugador jugadorEquipo1 = j.equipos.get(0).getJugadores().get(i);
            jugadorEquipo1.jugar(j.equipo1, j.equipo2,1);
            // Jugador del equipo 2
            Jugador jugadorEquipo2 = j.equipos.get(1).getJugadores().get(i);
            jugadorEquipo2.jugar(j.equipo2, j.equipo1,1);
        }
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
