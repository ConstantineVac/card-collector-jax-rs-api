package gr.konvas.cardcollector.cardcollectorrestapijaxrs.dao;

import gr.konvas.cardcollector.cardcollectorrestapijaxrs.model.Card;
import gr.konvas.cardcollector.cardcollectorrestapijaxrs.model.User;

import java.util.List;

public interface IUserDAO {
    User insertUser(User user);
    User updateUser(User user);
    void deleteUser(Long id);
    List<User> getAllUsers();
    User getUserById(Long id);
    User getUserByEmail(String email);

//    // Additional methods for working with the user's portfolio
//    List<Card> getUserPortfolio(Long userId); // Get the list of cards in a user's portfolio
//    void addUserToPortfolio(Long userId, Card card); // Add a card to a user's portfolio
//    void removeUserFromPortfolio(Long userId, Card card); // Remove a card from a user's portfolio
}

