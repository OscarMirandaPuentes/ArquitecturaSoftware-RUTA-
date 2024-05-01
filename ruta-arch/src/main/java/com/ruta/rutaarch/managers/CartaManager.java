package com.ruta.rutaarch.managers;

import java.util.List;

import com.ruta.rutaarch.entities.Carta;
import jakarta.persistence.*;

public class CartaManager {
    

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsuariosUP");

    public static Carta createCarta(Carta carta) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(carta);
        em.getTransaction().commit();
        em.close();
        return carta;
    }

    public static Carta getCartaById(int id) {
        EntityManager em = emf.createEntityManager();
        Carta jugador = em.find(Carta.class, id);
        em.close();
        return jugador;
    }

    public static List<Carta> getAllCartas() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT c FROM Carta c");
        List<Carta> jugadores = query.getResultList();
        em.close();
        return jugadores;
    }

    public static Carta updateJugador(Carta carta) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Carta updatedCarta = em.merge(carta);
        em.getTransaction().commit();
        em.close();
        return updatedCarta;
    }

    public static void deleteCarta(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Carta carta = em.find(Carta.class, id);
        if (carta != null) {
            em.remove(carta);
        }
        em.getTransaction().commit();
        em.close();
    }
}
