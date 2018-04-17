package ru.job4j.Collections.Generic;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStoreTest {

    @Test
    public void whenCreateFourUsersThenReplaceOneThem() {
        UserStore userSt = new UserStore();
        userSt.add(new User("001"));
        userSt.add(new User("002"));
        userSt.add(new User("003"));
        userSt.add(new User("004"));
        User expect = new User("999");
        userSt.replace("002", expect);
        User rsl = userSt.getObject(1);
        assertThat(rsl, is(expect));
    }


    @Test
    public void whenCreateFourRolsThenReplaceOneOfThem() {
        RoleStore roleSt = new RoleStore();
        roleSt.add(new Role("001"));
        roleSt.add(new Role("002"));
        roleSt.add(new Role("003"));
        roleSt.add(new Role("004"));
        Role expect = new Role("999");
        roleSt.replace("002", expect);
        Role rsl = roleSt.getObject(1);
        assertThat(rsl, is(expect));
    }

    @Test
    public void whenCreateFourUsersThenDeleteTwoOfThem() {
        UserStore userSt = new UserStore();
        userSt.add(new User("001"));
        userSt.add(new User("002"));
        userSt.add(new User("003"));
        userSt.add(new User("004"));
        userSt.delete("002");
        userSt.delete("003");
        String rsl = userSt.getObject(1).getId();
        String expect = "004";
        assertThat(rsl, is(expect));
    }



    @Test
    public void whenCreateFourRolsThenDeleteTwoOfThem() {
        RoleStore roleSt = new RoleStore();
        roleSt.add(new Role("001"));
        roleSt.add(new Role("002"));
        roleSt.add(new Role("003"));
        roleSt.add(new Role("004"));
        roleSt.delete("002");
        roleSt.delete("004");
        String rsl = roleSt.getObject(1).getId();
        String expect = "003";
        assertThat(rsl, is(expect));
    }

}