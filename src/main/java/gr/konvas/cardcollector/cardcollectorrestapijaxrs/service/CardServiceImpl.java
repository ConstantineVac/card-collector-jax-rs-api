package gr.konvas.cardcollector.cardcollectorrestapijaxrs.service;

import gr.konvas.cardcollector.cardcollectorrestapijaxrs.dao.ICardDAO;
import gr.konvas.cardcollector.cardcollectorrestapijaxrs.dao.IUserDAO;
import gr.konvas.cardcollector.cardcollectorrestapijaxrs.dto.CardInsertDTO;
import gr.konvas.cardcollector.cardcollectorrestapijaxrs.dto.CardUpdateDTO;
import gr.konvas.cardcollector.cardcollectorrestapijaxrs.dto.UserInsertDTO;
import gr.konvas.cardcollector.cardcollectorrestapijaxrs.model.Card;
import gr.konvas.cardcollector.cardcollectorrestapijaxrs.model.User;
import gr.konvas.cardcollector.cardcollectorrestapijaxrs.service.util.JPAHelper;
import jakarta.persistence.EntityNotFoundException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.ext.Provider;
import java.util.List;

@Provider
@ApplicationScoped
public class CardServiceImpl implements ICardService{

    @Inject
    private ICardDAO cardDAO;

    @Override
    public Card insertCard(CardInsertDTO dto) throws Exception {
        Card card;
        try {
            JPAHelper.beginTransaction();
            card = mapInsert(dto);
            card = cardDAO.insertCard(card);
            if (card.getId() == null) {
                throw new Exception("Insert Error");
            }
            JPAHelper.commitTransaction();
        } catch (Exception e) {
            JPAHelper.rollbackTransaction();
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }

        return card;
    }

    private Card mapInsert(CardInsertDTO dto) {
        Card card = new Card();
        card.setCardName(dto.getCardName());
        card.setSetName(dto.getSetName());
        card.setSetNumber(dto.getSetNumber());
        card.setCopies(dto.getCopies());
        card.setPrice(dto.getPrice());
        card.setRarity(dto.getRarity());
        return card;
    }

    @Override
    public Card updateCard(CardUpdateDTO dto) throws EntityNotFoundException {
        Card cardToUpdate;
        try {
            JPAHelper.beginTransaction();
            cardToUpdate = mapUpdate(dto);
            if (cardDAO.getCardById(cardToUpdate.getId()) == null) {
                throw new EntityNotFoundException();
            }
            cardDAO.updateCard(cardToUpdate);
            JPAHelper.commitTransaction();
        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }

        return cardToUpdate;
    }

    private Card mapUpdate(CardUpdateDTO dto) {
        Card card = new Card();
        card.setId(dto.getId());
        card.setCardName(dto.getCardName());
        card.setSetName(dto.getSetName());
        card.setSetNumber(dto.getSetNumber());
        card.setCopies(dto.getCopies());
        card.setPrice(dto.getPrice());
        card.setRarity(dto.getRarity());
        return card;
    }

    @Override
    public void deleteCard(Long id) throws EntityNotFoundException {
        try {
            JPAHelper.beginTransaction();
            if (cardDAO.getCardById(id) == null) {
                throw new EntityNotFoundException();
            }
            cardDAO.deleteCard(id);
            JPAHelper.commitTransaction();
        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
    }

    @Override
    public List<Card> getAllCards() throws EntityNotFoundException {
        List<Card> cards;
        try {
            JPAHelper.beginTransaction();
            cards = cardDAO.getAllCards();
            if (cards.size() == 0) {
                throw new EntityNotFoundException();
            }
            JPAHelper.commitTransaction();
        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
        return cards;
    }

    @Override
    public Card getCardById(Long id) throws EntityNotFoundException {
        Card card;
        try {
            JPAHelper.beginTransaction();
            card = cardDAO.getCardById(id);
            if (card == null) {
                throw new EntityNotFoundException();
            }
            JPAHelper.commitTransaction();
        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }

        return card;
    }

    @Override
    public List<Card> getCardsBySet(String setName) throws EntityNotFoundException {
        List<Card> cards;
        try {
            JPAHelper.beginTransaction();
            cards = cardDAO.getCardsBySet(setName);
            if (cards.isEmpty()) {
                throw new EntityNotFoundException();
            }
            JPAHelper.commitTransaction();
        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
        return cards;
    }
}
