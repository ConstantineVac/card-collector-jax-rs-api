package gr.konvas.cardcollector.cardcollectorrestapijaxrs.service;

import gr.konvas.cardcollector.cardcollectorrestapijaxrs.dto.CardInsertDTO;
import gr.konvas.cardcollector.cardcollectorrestapijaxrs.dto.CardUpdateDTO;
import gr.konvas.cardcollector.cardcollectorrestapijaxrs.model.Card;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface ICardService {
    Card insertCard(CardInsertDTO dto) throws Exception ;
    Card updateCard(CardUpdateDTO dto) throws EntityNotFoundException;
    void deleteCard(Long id) throws EntityNotFoundException;
    List<Card> getAllCards() throws EntityNotFoundException;
    Card getCardById(Long id) throws EntityNotFoundException;
    List<Card> getCardsBySet(String setName) throws EntityNotFoundException;

}
