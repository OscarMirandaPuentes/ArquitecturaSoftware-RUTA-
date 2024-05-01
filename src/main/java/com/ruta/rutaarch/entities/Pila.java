package com.ruta.rutaarch.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.*;

@Entity
@Table(name = "pila")
public class Pila {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Equipo equipo;

    @Column(name = "tipo_pila")
    private String tipoPila; // Indica el tipo de pila: distancia, velocidad, seguridad o batalla

    @OneToMany
    private List<Carta> cartas = new ArrayList<>();

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

    ////////////////
    public List<Carta> getCartas() {
        return cartas;
    }

    public List<Carta> getPilaDistancia() {
        return filtrarPorTipoPila("distancia");
    }

    public List<Carta> getPilaSeguridad() {
        return filtrarPorTipoPila("seguridad");
    }

    public List<Carta> getPilaVelocidad() {
        return filtrarPorTipoPila("velocidad");
    }

    public List<Carta> getPilaBatalla() {
        return filtrarPorTipoPila("batalla");
    }

    private List<Carta> filtrarPorTipoPila(String tipoPila) {
        List<Carta> cartasPorTipo = new ArrayList<>();
        for (Carta carta : cartas) {
            if (carta.getTipoPila().equals(tipoPila)) {
                cartasPorTipo.add(carta);
            }
        }
        return cartasPorTipo;
    }
}