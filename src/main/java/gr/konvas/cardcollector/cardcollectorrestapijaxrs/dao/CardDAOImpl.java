package gr.konvas.cardcollector.cardcollectorrestapijaxrs.dao;

import gr.konvas.cardcollector.cardcollectorrestapijaxrs.model.Card;
import gr.konvas.cardcollector.cardcollectorrestapijaxrs.service.util.JPAHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ext.Provider;
import java.util.List;

@Provider
@ApplicationScoped
public class CardDAOImpl implements ICardDAO{
    @Override
    public Card insertCard(Card card) {
        EntityManager em = getEntityManager();
        em.persist(card);
        return card;
    }

    @Override
    public Card updateCard(Card card) {
        getEntityManager().merge(card);
        return card;
    }

    @Override
    public void deleteCard(Long id) {
        EntityManager em = getEntityManager();
        Card cardToDelete = em.find(Card.class, id);
        em.remove(cardToDelete);
    }

    @Override
    public List<Card> getAllCards() {
        EntityManager em = getEntityManager();
        return em.createQuery("SELECT c FROM Card c", Card.class).getResultList();
    }

    @Override
    public Card getCardById(Long id) {
        EntityManager em = getEntityManager();
        return em.find(Card.class, id);
    }

    @Override
    public List<Card> getCardsBySet(String setName) {
        EntityManager em = getEntityManager();
        TypedQuery<Card> query = em.createQuery("SELECT c FROM Card c WHERE c.setName = :setName", Card.class);
        query.setParameter("setName", setName);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    private EntityManager getEntityManager() {
        return JPAHelper.getEntityManager();
    }
}
