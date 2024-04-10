package managers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import entities.PilaBatalla;
import entities.PilaDistancia;

import java.util.List;

public class DistanciaManager {
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsuariosUP");

    public static PilaDistancia createPilaDistancia(PilaDistancia pilaDistancia) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(pilaDistancia);
        et.commit();
        em.close();
        return pilaDistancia;
    }

    public static PilaDistancia getPilaDistanciaById(Long id) {
        EntityManager em = emf.createEntityManager();
        PilaDistancia pilaDistancia = em.find(PilaDistancia.class, id);
        em.close();
        return pilaDistancia;
    }

    public static List<PilaDistancia> getAllPilasDistancia() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT pd FROM PilaDistancia pd");
        List<PilaDistancia> pilasDistancias = query.getResultList();
        em.close();
        return  pilasDistancias;
    }

    public static PilaDistancia updatePilaDistancia(PilaDistancia pilaDistancia) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        PilaDistancia updatedPilaDistancia = em.merge(pilaDistancia);
        et.commit();
        em.close();
        return updatedPilaDistancia;
    }

    public static void deletePilaDistancia(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        PilaDistancia pilaDistancia = em.find(PilaDistancia.class, id);
        if (pilaDistancia != null) {
            em.remove(pilaDistancia);
        }
        et.commit();
        em.close();
    }
}
