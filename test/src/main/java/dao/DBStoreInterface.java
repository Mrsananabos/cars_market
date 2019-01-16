package dao;

import model.User;

public interface DBStoreInterface {
    boolean addUser(User user);
    User findUserByName(String firstName);
    User updateLastName(User user);
    boolean ifExist(String firstName);
}
