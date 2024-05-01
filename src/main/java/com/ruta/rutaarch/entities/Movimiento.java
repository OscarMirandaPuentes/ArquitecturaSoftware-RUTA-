package com.ruta.rutaarch.entities;

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

    @Column(name = "accion")
    private String accion; // Indica si es "escoger" o "descartar" carta

    @Column(name = "tipo_pila_afectada")
    private String tipoPilaAfectada; // Indica el tipo de pila afectada: distancia, velocidad, seguridad o batalla

    

    public Long getId() {
        return id;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getTipoPilaAfectada() {
        return tipoPilaAfectada;
    }

    public void setTipoPilaAfectada(String tipoPilaAfectada) {
        this.tipoPilaAfectada = tipoPilaAfectada;
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

