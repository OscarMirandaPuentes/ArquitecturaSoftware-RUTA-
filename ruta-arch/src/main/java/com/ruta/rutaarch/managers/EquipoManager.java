package com.ruta.rutaarch.managers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import com.ruta.rutaarch.entities.Equipo;
import com.ruta.rutaarch.entities.Partida;

import java.util.List;

public class EquipoManager {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsuariosUP");

    public static Equipo createEquipo(Equipo equipo) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(equipo);
        em.getTransaction().commit();
        em.close();
        return equipo;
    }
    public static Equipo createEquipo(Equipo equipo, Long partidaId) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Partida partida = em.find(Partida.class, partidaId);
        equipo.setPartida(partida); // Asociar el equipo a la partida
        em.persist(equipo);
        em.getTransaction().commit();
        em.close();
        return equipo;
    }

    public static Equipo getEquipoById(Long id) {
        EntityManager em = emf.createEntityManager();
        Equipo equipo = em.find(Equipo.class, id);
        em.close();
        return equipo;
    }

    public static List<Equipo> getAllEquipos() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT e FROM Equipo e");
        List<Equipo> equipos = query.getResultList();
        em.close();
        return equipos;
    }

    public static Equipo updateEquipo(Equipo equipo) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Equipo updatedEquipo = em.merge(equipo);
        em.getTransaction().commit();
        em.close();
        return updatedEquipo;
    }

    public static void deleteEquipo(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Equipo equipo = em.find(Equipo.class, id);
        if (equipo != null) {
            em.remove(equipo);
        }
        em.getTransaction().commit();
        em.close();
    }
}
