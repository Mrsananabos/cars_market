package service;

import model.User;
import org.junit.Assert;
import org.junit.Test;

public class ValidateTest {
    private final Validate validate = new Validate();

    @Test
    public void whenReturnUserByFirstName() {
        String firstName = "Ivan";
        String lastName = "Hohlov";
        User expected = new User(firstName, lastName);
        validate.addUser(expected);
        User result = validate.findUserByName(firstName);
        Assert.assertEquals(result, expected);
    }

    @Test
    public void whenReturnNullByFirstName() {
        String firstName = "Olga";
        String lastName = "Hohlova";
        User expected = new User(firstName, lastName);
        validate.addUser(expected);
        User result = validate.findUserByName("Jane");
        Assert.assertEquals(result, null);
    }

    @Test
    public void whenUpdateLastName() {
        String firstName = "Valentin";
        String lastName = "Chumaev";
        String newLastName = "Kokorin";
        User user = new User(firstName, lastName);
        validate.addUser(user);
        User expected = new User(firstName, newLastName);
        User result = validate.updateLastName(expected);
        Assert.assertEquals(result, expected);
    }

    @Test
    public void whenUpdateLastNameThenReturnNull() {
        String firstName = "Valentina";
        String lastName = "Ydashkina";
        String newLastName = "Graf";
        User user = new User(firstName, lastName);
        validate.addUser(user);
        User expected = new User("Kolya", newLastName);
        User result = validate.updateLastName(expected);
        Assert.assertEquals(result, null);
    }




}