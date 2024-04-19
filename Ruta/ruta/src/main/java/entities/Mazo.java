package entities;
import java.util.List;
import jakarta.persistence.*;
import modelo.Carta;

@Entity
@Table(name = "mazo")
public class Mazo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne // un mazo para un jugador
    private Jugador jugador;

    @Column(nullable = false)
    private List<Carta> cartas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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