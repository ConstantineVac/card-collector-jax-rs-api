package gr.konvas.cardcollector.cardcollectorrestapijaxrs.service;

import gr.konvas.cardcollector.cardcollectorrestapijaxrs.dao.ICardDAO;
import gr.konvas.cardcollector.cardcollectorrestapijaxrs.dao.IUserDAO;

import gr.konvas.cardcollector.cardcollectorrestapijaxrs.dto.UserInsertDTO;
import gr.konvas.cardcollector.cardcollectorrestapijaxrs.dto.UserReadOnlyDTO;
import gr.konvas.cardcollector.cardcollectorrestapijaxrs.dto.UserUpdateDTO;
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
public class UserServiceImpl implements IUserService{

    @Inject
    private IUserDAO userDAO;

    @Inject
    private ICardDAO cardDAO;

    @Override
    public User insertUser(UserInsertDTO dto) throws Exception {
        User user;
        try {
            JPAHelper.beginTransaction();
            user = mapInsert(dto);
            user = userDAO.insertUser(user);
            if (user.getId() == null) {
                throw new Exception("Insert Error");
            }
            JPAHelper.commitTransaction();
        } catch (Exception e) {
            JPAHelper.rollbackTransaction();
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }

        return user; // Return the inserted user object
    }

    private User mapInsert(UserInsertDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        return user;
    }

    @Override
    public User updateUser(UserUpdateDTO dto) throws EntityNotFoundException {
        User userToUpdate;
        try {
            JPAHelper.beginTransaction();
            userToUpdate = mapUpdate(dto);
            if (userDAO.getUserById(userToUpdate.getId())== null) {
                throw new EntityNotFoundException();
            }
            userDAO.updateUser(userToUpdate);
            JPAHelper.commitTransaction();
        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
        return userToUpdate;
    }

    private User mapUpdate(UserUpdateDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        return user;
    }

    @Override
    public void deleteUser(Long id) throws EntityNotFoundException {
        try {
            JPAHelper.beginTransaction();
            if (userDAO.getUserById(id) == null) {
                throw new EntityNotFoundException();
            }
            userDAO.deleteUser(id);
            JPAHelper.commitTransaction();
        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
    }


    @Override
    public User getUserById(Long id) throws EntityNotFoundException {
        User user;
        try {
            JPAHelper.beginTransaction();
            user = userDAO.getUserById(id);
            if (user == null) {
                throw new EntityNotFoundException();
            }
            JPAHelper.commitTransaction();
        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) throws EntityNotFoundException {
        User user;
        try {
            JPAHelper.beginTransaction();
            user = userDAO.getUserByEmail(email);
            if (user == null) {
                throw new EntityNotFoundException();
            }
            JPAHelper.commitTransaction();
        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() throws EntityNotFoundException{
        List<User> users;
        try {
            JPAHelper.beginTransaction();
            users = userDAO.getAllUsers();
            if (users.size() == 0) {
                throw new EntityNotFoundException();
            }
            JPAHelper.commitTransaction();
        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
        return users;
    }
}
