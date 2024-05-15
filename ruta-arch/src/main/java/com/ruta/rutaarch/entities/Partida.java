package com.ruta.rutaarch.entities;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "partida")
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String Ganador;

    @Column(nullable = false)
    private String estado; //partida terminada, pausada 

    @Column(nullable = false)
    private int jugadorTurno; //dependiendo del equipo

    @Column(nullable = false)
    private int numJugadores; //dependiendo del equipo

    //una partida tiene "muchos" equipos
    @Column
    @OneToMany(mappedBy = "partida", cascade = CascadeType.ALL)
    private List<Equipo> equipos; 

    @OneToOne(mappedBy = "partida", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Mazo mazo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public String getGanador() {
        return Ganador;
    }

    public void setGanador(String ganador) {
        Ganador = ganador;
    }

    public Mazo getMazo() {
        return mazo;
    }

    public void setMazo(Mazo mazo) {
        this.mazo = mazo;
    }

    public void setJugadorTurno(int jugadorTurno) {
        this.jugadorTurno = jugadorTurno;
    }

    public int getNumJugadores() {
        return numJugadores;
    }

    public void setNumJugadores(int numJugadores) {
        this.numJugadores = numJugadores;
    }

    
    
    
    
}




