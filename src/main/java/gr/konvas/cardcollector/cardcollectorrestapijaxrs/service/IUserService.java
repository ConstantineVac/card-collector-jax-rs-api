package gr.konvas.cardcollector.cardcollectorrestapijaxrs.service;

import gr.konvas.cardcollector.cardcollectorrestapijaxrs.dto.UserInsertDTO;
import gr.konvas.cardcollector.cardcollectorrestapijaxrs.dto.UserUpdateDTO;
import gr.konvas.cardcollector.cardcollectorrestapijaxrs.model.Card;
import gr.konvas.cardcollector.cardcollectorrestapijaxrs.model.User;
import jakarta.persistence.EntityNotFoundException;


import java.util.List;

public interface IUserService {
    User insertUser(UserInsertDTO dto) throws Exception;
    User updateUser(UserUpdateDTO dto) throws EntityNotFoundException;
    void deleteUser(Long id) throws EntityNotFoundException;
    User getUserById(Long id) throws EntityNotFoundException;
    User getUserByEmail(String email)throws EntityNotFoundException;
    List<User> getAllUsers() throws EntityNotFoundException;

//    // Additional methods for managing user's portfolio
//    List<Card> getUserPortfolio(Long userId) throws EntityNotFoundException;
//    void addUserToUserPortfolio(Long userId, Long cardId) throws EntityNotFoundException;
//    void removeUserFromUserPortfolio(Long userId, Long cardId) throws EntityNotFoundException;
}
