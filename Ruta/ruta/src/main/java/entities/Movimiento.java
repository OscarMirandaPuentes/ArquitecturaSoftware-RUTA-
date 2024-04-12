package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "movimiento")
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "jugador_id")
    private Jugador jugador;

    @ManyToOne
    @JoinColumn(name = "partida_id")
    private Partida partida;

    @ManyToOne
    @JoinColumn(name = "carta_id")
    private Carta carta;

    @Column(name = "tope_elemento_seguridad")
    private String topeSeguridad;
    
    @Column(name = "tope_elemento_distancia")
    private String topeDistancia;

    @Column(name = "tope_elemento_batalla")
    private String topeBatalla;

    public String getTopeSeguridad() {
        return topeSeguridad;
    }

    public void setTopeSeguridad(String topeSeguridad) {
        this.topeSeguridad = topeSeguridad;
    }

    public String getTopeDistancia() {
        return topeDistancia;
    }

    public void setTopeDistancia(String topeDistancia) {
        this.topeDistancia = topeDistancia;
    }

    public String getTopeBatalla() {
        return topeBatalla;
    }

    public void setTopeBatalla(String topeBatalla) {
        this.topeBatalla = topeBatalla;
    }

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

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

}

