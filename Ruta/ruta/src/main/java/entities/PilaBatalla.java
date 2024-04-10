package entities;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "pila_batalla")
public class PilaBatalla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "equipo_id")
    private Integer equipoId;

    @OneToMany(mappedBy = "pilaBatalla")
    private List<Carta> cartas;

    public PilaBatalla() {
        // Default constructor
    }

    public PilaBatalla(int e) {
        this.equipoId = e;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(int equipoId) {
        this.equipoId = equipoId;
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(List<Carta> cartas) {
        this.cartas = cartas;
    }

    
}