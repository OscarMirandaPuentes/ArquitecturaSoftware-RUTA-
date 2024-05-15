package com.ruta.rutaarch.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "carta")
public class Carta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "mazo_id") 
    private Mazo mazo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "jugador_id") 
    private Jugador jugador;


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



    public Mazo getMazo() {
        return mazo;
    }


    public void setMazo(Mazo mazo) {
        this.mazo = mazo;
    }


    public Jugador getJugador() {
        return jugador;
    }


    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    

}
