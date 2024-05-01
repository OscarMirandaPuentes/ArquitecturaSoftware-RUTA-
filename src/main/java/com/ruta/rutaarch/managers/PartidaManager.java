package com.ruta.rutaarch.managers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import com.ruta.rutaarch.entities.Equipo;
import com.ruta.rutaarch.entities.Jugador;
import com.ruta.rutaarch.entities.Mazo;
import com.ruta.rutaarch.entities.Partida;

import java.util.Date;
import java.util.List;

public class PartidaManager {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsuariosUP");

    public static Partida createPartida(Partida partida) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(partida);
        em.getTransaction().commit();
        em.close();
        return partida;
    }

    public static Partida getPartidaById(Long id) {
        EntityManager em = emf.createEntityManager();
        Partida partida = em.find(Partida.class, id);
        em.close();
        return partida;
    }

    public static List<Partida> getAllPartidas() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT p FROM Partida p");
        List<Partida> partidas = query.getResultList();
        em.close();
        return partidas;
    }

    public static Partida updatePartida(Partida partida) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Partida updatedPartida = em.merge(partida);
        em.getTransaction().commit();
        em.close();
        return updatedPartida;
    }

    public static void deletePartida(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Partida partida = em.find(Partida.class, id);
        if (partida != null) {
            em.remove(partida);
        }
        em.getTransaction().commit();
        em.close();
    }

    public static Mazo getMazoDeJugadorEnPartida(Long partidaId, Long equipoId, Long jugadorId) {
        EntityManager em = emf.createEntityManager();
        Partida partida = em.find(Partida.class, partidaId);
        if (partida != null) {
            for (Equipo equipo : partida.getEquipos()) {
                if (equipo.getId() == equipoId) {
                    for (Jugador jugador : equipo.getJugadores()) {
                        if (jugador.getId() == jugadorId) {
                            return jugador.getMazo();
                        }
                    }
                }
            }
        }
        return null; // Si no se encuentra la partida, el equipo o el jugador, retorna null
    }



    public static List<Partida> getPartidasPorEstado(String estado) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT p FROM Partida p WHERE p.estado = :estado");
        query.setParameter("estado", estado);
        List<Partida> partidas = query.getResultList();
        em.close();
        return partidas;
    }

    public static List<Partida> getPartidasPorFecha(Date fechaInicio, Date fechaFin) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT p FROM Partida p WHERE p.fechaInicio BETWEEN :fechaInicio AND :fechaFin");
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        List<Partida> partidas = query.getResultList();
        em.close();
        return partidas;
    }

    public static String getJugadorTurno(Long partidaId) {
        EntityManager em = emf.createEntityManager();
        Partida partida = em.find(Partida.class, partidaId);
        em.close();
        return partida.getJugadorTurno();
    }

    public static void actualizarEstadoPartida(Long partidaId, String nuevoEstado) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Partida partida = em.find(Partida.class, partidaId);
        partida.setEstado(nuevoEstado);
        em.getTransaction().commit();
        em.close();
    }

    public static int getEquipoGanador(Long partidaId) {
        EntityManager em = emf.createEntityManager();
        Partida partida = em.find(Partida.class, partidaId);
        em.close();
        return (partida.getResultadoEquipo1() > partida.getResultadoEquipo2()) ? 1 : 2;
    }

    public static void eliminarTodasLasPartidas() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Partida").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public static long obtenerNumeroTotalDePartidas() {
        EntityManager em = emf.createEntityManager();
        long cantidad = (long) em.createQuery("SELECT COUNT(p) FROM Partida p").getSingleResult();
        em.close();
        return cantidad;
    }
}
