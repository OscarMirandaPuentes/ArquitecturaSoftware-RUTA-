package controlador;

import modelo.Administrador;

public class Controlador {
    Administrador a;

    public Controlador() {
        this.a = new Administrador();
    }

    public void iniciar(){a.iniciarJuego();};
}
