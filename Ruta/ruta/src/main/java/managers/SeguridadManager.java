package managers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import modelo.Seguridad;
import entities.PilaBatalla;
import entities.PilaSeguridad;

import java.util.List;

public class SeguridadManager {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsuariosUP");

    public static PilaSeguridad createPilaSeguridad(PilaSeguridad pilaSeguridad) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(pilaSeguridad);
        et.commit();
        em.close();
        return pilaSeguridad;
    }

    public static PilaSeguridad getPilaSeguridadById(Long id) {
        EntityManager em = emf.createEntityManager();
        PilaSeguridad pilaSeguridad= em.find(PilaSeguridad.class, id);
        em.close();
        return pilaSeguridad;
    }

    public static List<PilaSeguridad> getAllPilasSeguridad() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT ps FROM PilaSeguridad ps");
        List<PilaSeguridad> pilasSeguridad = query.getResultList();
        em.close();
        return pilasSeguridad;
    }

    public static PilaSeguridad updatePilaBatalla(PilaSeguridad pilaSeguridad) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        PilaSeguridad updatedPilaSeguridad = em.merge(pilaSeguridad);
        et.commit();
        em.close();
        return updatedPilaSeguridad;
    }

    public static void deletePilaSeguridad(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        PilaSeguridad pilaSeguridad = em.find(PilaSeguridad.class, id);
        if (pilaSeguridad != null) {
            em.remove(pilaSeguridad);
        }
        et.commit();
        em.close();
    }
}
