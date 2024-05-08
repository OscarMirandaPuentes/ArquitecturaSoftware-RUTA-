package com.ruta.rutaarch.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "jugador")
public class Jugador {
    //TODO:Conexion con mano de cartas

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    //muchos jugadores a un equipo
    @ManyToOne
    @JoinColumn(name = "equipo_id") 
    private Equipo equipo;

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

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Mazo getMazo() {
        return mazo;
    }

    public void setMazo(Mazo mazo) {
        this.mazo = mazo;
    }

    @OneToOne(mappedBy = "jugador", cascade = CascadeType.ALL)
    private Mazo mazo;

    
}