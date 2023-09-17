package gr.konvas.cardcollector.cardcollectorrestapijaxrs.dao;

import gr.konvas.cardcollector.cardcollectorrestapijaxrs.model.Card;
import gr.konvas.cardcollector.cardcollectorrestapijaxrs.model.User;
import gr.konvas.cardcollector.cardcollectorrestapijaxrs.service.util.JPAHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ext.Provider;
import java.util.List;



@Provider
@ApplicationScoped
public class UserDAOImpl implements IUserDAO{

    @Override
    public User insertUser(User user) {
        EntityManager em = getEntityManager();
        em.persist(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        getEntityManager().merge(user);
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        EntityManager em = getEntityManager();
        User userToDelete = em.find(User.class, id);
        em.remove(userToDelete);
    }

    @Override
    public User getUserById(Long id) {
        EntityManager em = getEntityManager();
        return em.find(User.class, id);
    }



    @Override
    public User getUserByEmail(String email) {
        EntityManager em = getEntityManager();
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
        query.setParameter("email", email);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // Return null if no user is found
        }
    }

    @Override
    public List<User> getAllUsers() {
        EntityManager em = getEntityManager();
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

//    @Override
//    public List<Card> getUserPortfolio(Long userId) {
//        EntityManager em = getEntityManager();
//        try {
//            User user = em.find(User.class, userId);
//            if (user != null) {
//                return user.getPortfolio();
//            }
//            return null; // User not found
//        } finally {
//            em.close();
//        }
//    }
//
//    @Override
//    public void addUserToPortfolio(Long userId, Card card) {
//        EntityManager em = getEntityManager();
//        try {
//            User user = em.find(User.class, userId);
//            if (user != null) {
//                user.getPortfolio().add(card);
//                em.merge(user);
//            }
//        } finally {
//            em.close();
//        }
//    }
//
//    @Override
//    public void removeUserFromPortfolio(Long userId, Card card) {
//        EntityManager em = getEntityManager();
//        try {
//            User user = em.find(User.class, userId);
//            if (user != null) {
//                user.getPortfolio().remove(card);
//                em.merge(user);
//            }
//        } finally {
//            em.close();
//        }
//    }

    private EntityManager getEntityManager() {
        return JPAHelper.getEntityManager();
    }
}
