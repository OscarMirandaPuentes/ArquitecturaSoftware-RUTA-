package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "carta")
public class Carta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String tipoPila;

    /* 
    @ManyToOne
    @JoinColumn(name = "pila_batalla_id")
    private PilaBatalla pilaBatalla;

    @ManyToOne
    @JoinColumn(name = "pila_seguridad_id")
    private PilaSeguridad pilaSeguridad;

    @ManyToOne
    @JoinColumn(name = "pila_distancia_id")
    private PilaDistancia pilaDistancia;

    public PilaBatalla getPilaBatalla() {
        return pilaBatalla;
    }

    public void setPilaBatalla(PilaBatalla pilaBatalla) {
        this.pilaBatalla = pilaBatalla;
    }

    public PilaSeguridad getPilaSeguridad() {
        return pilaSeguridad;
    }

    public void setPilaSeguridad(PilaSeguridad pilaSeguridad) {
        this.pilaSeguridad = pilaSeguridad;
    }

    public PilaDistancia getPilaDistancia() {
        return pilaDistancia;
    }

    public void setPilaDistancia(PilaDistancia pilaDistancia) {
        this.pilaDistancia = pilaDistancia;
    }
    */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoPila() {
        return tipoPila;
    }

    public void setTipoPila(String tipoPila) {
        this.tipoPila = tipoPila;
    }

}
