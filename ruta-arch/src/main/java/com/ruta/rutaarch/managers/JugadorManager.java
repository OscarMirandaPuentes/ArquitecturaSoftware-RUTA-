package com.ruta.rutaarch.managers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import com.ruta.rutaarch.entities.Jugador;

import java.util.List;

public class JugadorManager {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsuariosUP");

    public static Jugador createJugador(Jugador jugador) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(jugador);
        em.getTransaction().commit();
        em.close();
        return jugador;
    }

    public static Jugador getJugadorById(int id) {
        EntityManager em = emf.createEntityManager();
        Jugador jugador = em.find(Jugador.class, id);
        em.close();
        return jugador;
    }

    public static List<Jugador> getAllJugadores() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT j FROM Jugador j");
        List<Jugador> jugadores = query.getResultList();
        em.close();
        return jugadores;
    }

    public static Jugador updateJugador(Jugador jugador) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Jugador updatedJugador = em.merge(jugador);
        em.getTransaction().commit();
        em.close();
        return updatedJugador;
    }

    public static void deleteJugador(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Jugador jugador = em.find(Jugador.class, id);
        if (jugador != null) {
            em.remove(jugador);
        }
        em.getTransaction().commit();
        em.close();
    }
}
