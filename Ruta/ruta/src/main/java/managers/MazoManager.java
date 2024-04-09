package managers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import entities.Mazo;

import java.util.List;

public class MazoManager {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsuariosUP");

    public static Mazo createMazo(Mazo mazo) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(mazo);
        em.getTransaction().commit();
        em.close();
        return mazo;
    }

    public static Mazo getMazoById(int id) {
        EntityManager em = emf.createEntityManager();
        Mazo mazo = em.find(Mazo.class, id);
        em.close();
        return mazo;
    }

    public static List<Mazo> getAllMazos() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT m FROM Mazo m");
        List<Mazo> mazos = query.getResultList();
        em.close();
        return mazos;
    }

    public static Mazo updateMazo(Mazo mazo) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Mazo updatedMazo = em.merge(mazo);
        em.getTransaction().commit();
        em.close();
        return updatedMazo;
    }

    public static void deleteMazo(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Mazo mazo = em.find(Mazo.class, id);
        if (mazo != null) {
            em.remove(mazo);
        }
        em.getTransaction().commit();
        em.close();
    }
}
