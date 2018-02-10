package ru.job4j.Sorting;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {
    @Test
    public void AgeSortingFrom() {
        SortUser sort = new SortUser();
        List<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(new User(90, "Ilya"), new User(45,"Hanna"), new User(32, "Zena"), new User(18, "Bob"), (new User(20, "Anna"))));
        Set<User> rsl = sort.sort(list);
        Set<User> expect = new TreeSet<>();
        expect.addAll(Arrays.asList(new User(18, "Bob"), new User(20,"Anna"), new User(32, "Zena"), new User(45, "Hanna"), (new User(90, "Ilya")) ));
        assertThat(rsl, is(expect));
    }


}
