package entities;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "pila_distancia")
public class PilaDistancia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "equipo_id")
    private int equipoId;

    @OneToMany(mappedBy = "pilaDistancia")
    private List<Carta> cartas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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