package entities;
import java.util.List;
import jakarta.persistence.*;
import modelo.Carta;

@Entity
@Table(name = "mazo")
public class Mazo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne // un mazo para un jugador
    private Jugador jugador;

    @Column(nullable = false)
    private List<Carta> cartas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(List<Carta> cartas) {
        this.cartas = cartas;
    }

    
}