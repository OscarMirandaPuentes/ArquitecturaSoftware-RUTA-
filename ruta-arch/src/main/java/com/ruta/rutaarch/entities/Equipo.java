package com.ruta.rutaarch.entities;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "equipo")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int puntaje;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "partida_id") 
    private Partida partida;

    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Jugador> jugadores;

    @Column
    private String pillaBatalla;

    @Column
    private String pillaDistancia;

    @Column
    private String pillaVelocidad;

    @Column
    private List<String> seguridad;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String getPillaBatalla() {
        return pillaBatalla;
    }

    public void setPillaBatalla(String pillaBatalla) {
        this.pillaBatalla = pillaBatalla;
    }

    public String getPillaDistancia() {
        return pillaDistancia;
    }

    public void setPillaDistancia(String pillaDistancia) {
        this.pillaDistancia = pillaDistancia;
    }

    public String getPillaVelocidad() {
        return pillaVelocidad;
    }

    public void setPillaVelocidad(String pillaVelocidad) {
        this.pillaVelocidad = pillaVelocidad;
    }

    public List<String> getSeguridad() {
        return seguridad;
    }

    public void setSeguridad(List<String> seguridad) {
        this.seguridad = seguridad;
    }

    
    
}
