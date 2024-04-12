package entities;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "pilaBatalla")
public class PilaBatalla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "equipo_id")
    private int equipoId;

    
    @OneToMany(mappedBy = "pila_batalla")
    private List<Carta> cartas;

    
    public List<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(List<Carta> cartas) {
        this.cartas = cartas;
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


}