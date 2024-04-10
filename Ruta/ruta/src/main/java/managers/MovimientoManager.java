package managers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import entities.Movimiento;
import java.util.List;

public class MovimientoManager {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsuariosUP");

    public static Movimiento createMovimiento(Movimiento movimiento) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(movimiento);
        et.commit();
        em.close();
        return movimiento;
    }

    public static Movimiento getMovimientoById(Long id) {
        EntityManager em = emf.createEntityManager();
        Movimiento movimiento = em.find(Movimiento.class, id);
        em.close();
        return movimiento;
    }

    public static List<Movimiento> getAllMovimientos() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT m FROM Movimiento m");
        List<Movimiento> movimientos = query.getResultList();
        em.close();
        return movimientos;
    }

    public static Movimiento updateMovimiento(Movimiento movimiento) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        Movimiento updatedMovimiento = em.merge(movimiento);
        et.commit();
        em.close();
        return updatedMovimiento;
    }

    public static void deleteMovimiento(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        Movimiento movimiento = em.find(Movimiento.class, id);
        if (movimiento != null) {
            em.remove(movimiento);
        }
        et.commit();
        em.close();
    }
}
