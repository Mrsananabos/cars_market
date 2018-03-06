package ru.job4j.Bank;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MenuTest {
    @Test
    public void WhenAddNewUsersThenDelete() {
        Map<User, ArrayList<Account>> basa = new HashMap<>();
        Menu m = new Menu(basa);
        User user1 = new User("Ivan", "001");
        User user2 = new User("Anna", "002");
        User user3 = new User("Oleg", "003");
        User user4 = new User("Ivan", "001");
        m.addUser(user1);
        m.addUser(user2);
        m.addUser(user3);
        m.addUser(user4);
        m.deleteUser(user2);
        Set<User> result = basa.keySet();
        Set<User> expect = new HashSet<User>();
       expect.add(user1);
        expect.add(user3);
       assertThat(result, is(expect));
    }

    @Test
    public void WhenTransferAmountFromUser1ToUser2ThenTrue() {
        Map<User, ArrayList<Account>> basa = new HashMap<>();
        Menu m = new Menu(basa);
        User user1 = new User("Ivan", "001");
        User user2 = new User("Anna", "002");
        Account account1 = new Account(1000, "901");
        Account account2 = new Account(500, "902");
        m.addUser(user1);
        m.addUser(user2);
        m.addAccountToUser("001", account1);
        m.addAccountToUser("002", account2);
        boolean result = m.transferMoney("001", "901", "002", "902", 700);
        boolean expect = true;
        assertThat(result, is(expect));
    }

    @Test
    public void WhenTransferAmountFromUser1ToUser2ThenFalse() {
        Map<User, ArrayList<Account>> basa = new HashMap<>();
        Menu m = new Menu(basa);
        User user1 = new User("Ivan", "001");
        User user2 = new User("Anna", "002");
        Account account1 = new Account(1000, "901");
        Account account2 = new Account(500, "902");
        m.addUser(user1);
        m.addUser(user2);
        m.addAccountToUser("001", account1);
        m.addAccountToUser("002", account2);
        boolean result = m.transferMoney("001", "901", "002", "902", 1700);
        boolean expect = false;
        assertThat(result, is(expect));
    }

}