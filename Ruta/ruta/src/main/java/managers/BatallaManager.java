package managers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import entities.PilaBatalla;
import java.util.List;

public class BatallaManager {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsuariosUP");

    public static PilaBatalla createPilaBatalla(PilaBatalla pilaBatalla) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(pilaBatalla);
        et.commit();
        em.close();
        return pilaBatalla;
    }

    public static PilaBatalla getPilaBatallaById(Long id) {
        EntityManager em = emf.createEntityManager();
        PilaBatalla pilaBatalla = em.find(PilaBatalla.class, id);
        em.close();
        return pilaBatalla;
    }

    public static List<PilaBatalla> getAllPilasBatalla() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT pb FROM PilaBatalla pb");
        List<PilaBatalla> pilasBatalla = query.getResultList();
        em.close();
        return pilasBatalla;
    }

    public static PilaBatalla updatePilaBatalla(PilaBatalla pilaBatalla) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        PilaBatalla updatedPilaBatalla = em.merge(pilaBatalla);
        et.commit();
        em.close();
        return updatedPilaBatalla;
    }

    public static void deletePilaBatalla(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        PilaBatalla pilaBatalla = em.find(PilaBatalla.class, id);
        if (pilaBatalla != null) {
            em.remove(pilaBatalla);
        }
        et.commit();
        em.close();
    }
}
