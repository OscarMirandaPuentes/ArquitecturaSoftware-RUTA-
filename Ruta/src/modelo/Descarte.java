package modelo;

import java.util.ArrayList;
import java.util.List;

public class Descarte {
    private List<Carta> cartasDescartadas;

    public Descarte() {
        cartasDescartadas = new ArrayList<>();
    }

    public void recibirCarta(Carta carta) {
        cartasDescartadas.add(carta);
    }

    public void enviarAlMazo(Mazo mazo) {
        for (Carta carta : cartasDescartadas) {
            mazo.pilaCartas.push(carta);
        }
        cartasDescartadas.clear();
    }
}
