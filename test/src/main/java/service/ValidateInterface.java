package service;

import model.User;

public interface ValidateInterface {
    void addUser(User user);
    User findUserByName(String firstName);
    User updateLastName(User user);
}