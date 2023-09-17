package gr.konvas.cardcollector.cardcollectorrestapijaxrs.service.exceptions;

public class UserServiceException extends Exception{
    private static final long serialVersionUID = 123456L;

    public UserServiceException(Class<?> clazz, Long id) {
        super("Entity " + clazz.getSimpleName() + " with id " + id + " does not exist");

    }
}
