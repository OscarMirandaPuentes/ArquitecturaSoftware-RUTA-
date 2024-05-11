package com.ruta.rutaarch.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "pila")
public class Pila {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tipo_pila")
    private String tipoPila; // Indica el tipo de pila: distancia, velocidad, seguridad o batalla

    @OneToOne(mappedBy = "pila", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Carta cartaTope;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "equipo_id") 
    private Equipo equipo;

    public Long getId() {
        return id;
    }

    public Equipo getEquipo_id() {
        return equipo;
    }

    public void setEquipo_id(Equipo equipo_id) {
        this.equipo = equipo_id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoPila() {
        return tipoPila;
    }

    public void setTipoPila(String tipoPila) {
        this.tipoPila = tipoPila;
    }

    public Carta getCartaTope() {
        return cartaTope;
    }

    public void setCartaTope(Carta cartaTope) {
        this.cartaTope = cartaTope;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    

}