package service;

import dao.DBStore;
import model.User;
import org.apache.log4j.Logger;

public class Validate implements ValidateInterface {
    private final DBStore store = DBStore.getInstance();
    private static final Logger LOGGER = Logger.getLogger(Validate.class);

    @Override
    public void addUser(User user) {
        if ((user.getFirstName() != null) && (user.getLastName() != null)) {
            if (!store.addUser(user)) {
                LOGGER.info("This first name is already taken, choose another");
            }
        } else {
            LOGGER.info("Enter a valid data");
        }
    }

    @Override
    public User findUserByName(String firstName) {
        User result = null;
        if (firstName != null) {
            result = store.findUserByName(firstName);
        } else {
            LOGGER.info("Enter a valid first name");
        }
        if (result == null) {
            LOGGER.info("User is not found");
        }
        return result;
    }

    @Override
    public User updateLastName(User user) {
        User result = null;
        if ((user.getFirstName() != null) && (user.getLastName() != null)) {
            result = store.updateLastName(user);
        } else {
            LOGGER.info("Enter a valid first data");
        }
        if (result == null) {
            LOGGER.info("User with this first name is not found");
        }
        return result;
    }

}
