package entities;

import java.util.ArrayList;

import jakarta.persistence.*;

@Entity
@Table(name = "pila")
public class Pila {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Equipo equipo_id;

    @Column(name = "tipo_pila")
    private String tipoPila; // Indica el tipo de pila: distancia, velocidad, seguridad o batalla

    @Column(name = "cantidad_cartas")
    private int cantidadCartas; // La cantidad actual de cartas en la pila

    @Column(name = "pilaBatalla")
    private ArrayList <Carta> pilaBatalla = new ArrayList<>();

    @Column(name = "pilaSeguridad")
    private ArrayList <Carta> pilaSeguridad = new ArrayList<>();;

    @Column(name = "pilaDistancia")
    private ArrayList <Carta> pilaDistancia = new ArrayList<>();;

    @Column(name = "pilaVelocidad")
    private ArrayList <Carta> pilaVelocidad  = new ArrayList<>();;

    public Long getId() {
        return id;
    }

    public Equipo getEquipo_id() {
        return equipo_id;
    }

    public void setEquipo_id(Equipo equipo_id) {
        this.equipo_id = equipo_id;
    }

    public ArrayList<Carta> getPilaBatalla() {
        return pilaBatalla;
    }

    public void setPilaBatalla(ArrayList<Carta> pilaBatalla) {
        this.pilaBatalla = pilaBatalla;
    }

    public ArrayList<Carta> getPilaSeguridad() {
        return pilaSeguridad;
    }

    public void setPilaSeguridad(ArrayList<Carta> pilaSeguridad) {
        this.pilaSeguridad = pilaSeguridad;
    }

    public ArrayList<Carta> getPilaDistancia() {
        return pilaDistancia;
    }

    public void setPilaDistancia(ArrayList<Carta> pilaDistancia) {
        this.pilaDistancia = pilaDistancia;
    }

    public ArrayList<Carta> getPilaVelocidad() {
        return pilaVelocidad;
    }

    public void setPilaVelocidad(ArrayList<Carta> pilaVelocidad) {
        this.pilaVelocidad = pilaVelocidad;
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

    public int getCantidadCartas() {
        return cantidadCartas;
    }

    public void setCantidadCartas(int cantidadCartas) {
        this.cantidadCartas = cantidadCartas;
    }
}