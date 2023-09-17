package gr.konvas.cardcollector.cardcollectorrestapijaxrs.dao;

import gr.konvas.cardcollector.cardcollectorrestapijaxrs.model.Card;

import java.util.List;

public interface ICardDAO {
    // Create a new card
    Card insertCard(Card card);

    // Update an existing card
    Card updateCard(Card card);

    // Delete a card by its ID
    void deleteCard(Long id);

    // Retrieve a list of all cards
    List<Card> getAllCards();

    // Retrieve a card by its ID
    Card getCardById(Long id);

    // Retrieve a list of cards by set name
    List<Card> getCardsBySet(String setName);
}
