package entities;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "pila_seguridad")
public class PilaSeguridad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "equipo_id")
    private int equipoId;

    @OneToMany(mappedBy = "pilaSeguridad")
    private List<Carta> cartas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getEquipo() {
        return equipoId;
    }

    public void setEquipo(int equipoId) {
        this.equipoId = equipoId;
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(List<Carta> cartas) {
        this.cartas = cartas;
    }

    
}