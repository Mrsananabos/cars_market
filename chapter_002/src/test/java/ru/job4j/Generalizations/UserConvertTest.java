package ru.job4j.Generalizations;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserConvertTest{
    @Test
    public void WhenListEnterAfterHashMapComes() {
        UserConvert user = new UserConvert();
        List<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(new User(1111, "Olga", "Moscow"), new User(1112, "Elena", "Chelyabinsk"),  new User(1113, "Lolita", "Ufa"),  new User(1114, "Maria", "Perm")));
        HashMap<Integer, User> resultMap = user.process(list);
        HashMap<Integer, User> expectMap = new HashMap<>();
        expectMap.put(1111, list.get(0));
        expectMap.put(1112, list.get(1));
        expectMap.put(1113, list.get(2));
        expectMap.put(1114, list.get(3));
        assertThat(resultMap, is(expectMap));

    }
}