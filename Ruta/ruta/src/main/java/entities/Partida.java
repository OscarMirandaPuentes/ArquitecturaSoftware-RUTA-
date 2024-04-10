package entities;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "partida")
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date fechaInicio;

    private Date fechaFin;

    @Column(nullable = false)
    private Integer resultadoEquipo1;

    @Column(nullable = false)
    private Integer resultadoEquipo2;

    @Column(nullable = false)
    private String estado; //partida terminada, pausada 

    @Column(nullable = false)
    private String jugadorTurno; //dependiendo del equipo

    //una partida tiene "muchos" equipos
    @Column(nullable = false)
    @OneToMany(mappedBy = "partida", cascade = CascadeType.ALL)
    private List<Equipo> equipos; // se asocia con equipo, equipo con jugador y jugador con mazo
    //para poder obtener el mazo de un jugador

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getResultadoEquipo1() {
        return resultadoEquipo1;
    }

    public void setResultadoEquipo1(int resultadoEquipo1) {
        this.resultadoEquipo1 = resultadoEquipo1;
    }

    public int getResultadoEquipo2() {
        return resultadoEquipo2;
    }

    public void setResultadoEquipo2(int resultadoEquipo2) {
        this.resultadoEquipo2 = resultadoEquipo2;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getJugadorTurno() {
        return jugadorTurno;
    }

    public void setJugadorTurno(String jugadorTurno) {
        this.jugadorTurno = jugadorTurno;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }   
}




