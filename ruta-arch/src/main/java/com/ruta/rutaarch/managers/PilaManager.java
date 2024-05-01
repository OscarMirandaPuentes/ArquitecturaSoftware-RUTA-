package com.ruta.rutaarch.managers;
import java.util.ArrayList;
import java.util.List;

import com.ruta.rutaarch.entities.Carta;
import com.ruta.rutaarch.entities.Pila;
import jakarta.persistence.*;

public class PilaManager {
    
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsuariosUP");
    
    public void crearPila(Pila pila) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(pila);
        em.getTransaction().commit();
        em.close();
    }
    
    public Pila buscarPila(Long id) {
        EntityManager em = emf.createEntityManager();
        Pila pila = em.find(Pila.class, id);
        em.close();
        return pila;
    }

    public static List<Pila> getAllPilas() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT p FROM Pila p");
        List<Pila> pilas = query.getResultList();
        em.close();
        return pilas;
    }
    
    public void actualizarPila(Pila pila) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(pila);
        em.getTransaction().commit();
        em.close();
    }
    
    public void eliminarPila(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Pila pila = em.find(Pila.class, id);
        if (pila != null) {
            em.remove(pila);
        }
        em.getTransaction().commit();
        em.close();
    }

    public void guardarCartaEnPila(Pila pila, Carta carta) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
    
        // Dependiendo del tipo de pila de la carta, la guardamos en el arreglo correspondiente en la pila
        switch (carta.getTipoPila()) {
            case "distancia":
                pila.getPilaDistancia().add(carta);
                break;
            case "seguridad":
                pila.getPilaSeguridad().add(carta);
                break;
            case "velocidad":
                pila.getPilaVelocidad().add(carta);
                break;
            case "batalla":
                pila.getPilaBatalla().add(carta);
                break;
            default:
                throw new IllegalArgumentException("Tipo de pila no válido: " + carta.getTipoPila());
        }
    
        em.merge(pila);
        em.getTransaction().commit();
        em.close();
    }

    /* 
    public void agregarCartaAPila(Pila pila, Carta carta) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        // Dependiendo del tipo de pila, agregamos la carta al arreglo correspondiente
        switch (pila.getTipoPila()) {
            case "distancia":
                pila.getPilaDistancia().add(carta);
                break;
            case "seguridad":
                pila.getPilaSeguridad().add(carta);
                break;
            case "velocidad":
                pila.getPilaVelocidad().add(carta);
                break;
            case "batalla":
                pila.getPilaBatalla().add(carta);
                break;
            default:
                throw new IllegalArgumentException("Tipo de pila no válido: " + pila.getTipoPila());
        }
        
        em.merge(pila);
        em.getTransaction().commit();
        em.close();
    }
    */
}
