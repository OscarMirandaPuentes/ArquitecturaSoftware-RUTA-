package com.ruta.rutaarch.entities;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "carta")
public class Carta implements Serializable{
    //TODO:Conexion con usuario
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @JsonIgnore
    @OneToOne
    @MapsId
    @JoinColumn(name = "pila_id")
    private Pila pila;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "mazo_id") 
    private Mazo mazo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "jugador_id") 
    private Jugador jugador;

    
    public Carta(String nombre) {
        this.nombre = nombre;
    }


    public Carta() {
    }



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


    public Pila getPila() {
        return pila;
    }


    public void setPila(Pila pila) {
        this.pila = pila;
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
