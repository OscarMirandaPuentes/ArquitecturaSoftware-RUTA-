package managers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.NoResultException;

import entities.User;

import java.util.List;

public class UserManager {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsuariosPU");

    public static User createUser(User user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
        return user;
    }

    public static User getUserById(Long id) {
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, id);
        em.close();
        return user;
    }

    public static User getUserByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        List<User> users  = getAllUsers();
        User u = new User();
        try {
            for (User user : users) {
                if (email.equals(user.getEmail())) {
                    u = user;
                }
            }
            return u;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return u;
    }

    public static List<User> getAllUsers() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT u FROM User u");
        List<User> users = query.getResultList();
        em.close();
        return users;
    }

    public static User updateUser(User user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User updatedUser = em.merge(user);
        em.getTransaction().commit();
        em.close();
        return updatedUser;
    }

    public static void deleteUser(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(user);
        }
        em.getTransaction().commit();
        em.close();
    }
}