package ru.job4j.bank;

import java.util.*;

public class Menu {

    private Map<User, ArrayList<Account>> bankBase = new HashMap<>();

    public Menu(Map<User, ArrayList<Account>> bankBase) {
        this.bankBase = bankBase;
    }

    private User searchOfUser(String passport) {
        Set<User> keys = new LinkedHashSet<User>();
        keys = this.bankBase.keySet();
        for (User key : keys) {
            if (passport.equals(key.getPassport())) {
                return key;
            }
        }
        return null;
    }

    ;

    private Account searchOfAccount(User user, String requisite) {
        for (Account account : this.bankBase.get(user)) {
            if (account.getRequisies().equals(requisite)) {
                return account;
            }
        }
        return null;
    }


    public void addUser(User user) { //добавление пользователя. Переопределить еквалс!!!!
        this.bankBase.putIfAbsent(user, new ArrayList<Account>());
    }


    public void deleteUser(User user) { //удаление пользователя.
        this.bankBase.remove(user);
    }


    public void addAccountToUser(String passport, Account account) { //добавить счёт пользователю.
        bankBase.get(searchOfUser(passport)).add(account);
    }


    public void deleteAccountFromUser(String passport, Account account) { //удалить один счёт пользователя
        this.bankBase.get(searchOfUser(passport)).remove(account);
    }


    public List<Account> getUserAccounts(String passport) {
        return this.bankBase.get(searchOfUser(passport));
    }


    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String dstRequisite, int amount) {
        User srcUser = searchOfUser(srcPassport);
        User destUser = searchOfUser(destPassport);
        Account srcAccount = searchOfAccount(srcUser, srcRequisite);
        Account destAccount = searchOfAccount(destUser, dstRequisite);

        if (srcAccount != null && destAccount != null && srcAccount.getValue() >= amount && amount > 0) {
            srcAccount.setValue(srcAccount.getValue() - amount);
            destAccount.setValue(destAccount.getValue() + amount);
            System.out.println(srcAccount + " " + destAccount);
            return true;
        } else {
            return false;
        }
    }

}