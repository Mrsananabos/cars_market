package ru.job4j.Sorting;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {

    @Test
    public void AgeSortingFromSmallToLarge() {
        SortUser sort = new SortUser();
        List<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(new User(90, "Ilya"), new User(45,"Hanna"), new User(32, "Zena"), new User(18, "Bob"), (new User(20, "Anna"))));
        Set<User> rsl = sort.sort(list);
        Set<User> expect = new TreeSet<>();
        expect.addAll(Arrays.asList(new User(18, "Bob"), new User(20,"Anna"), new User(32, "Zena"), new User(45, "Hanna"), (new User(90, "Ilya")) ));
        assertThat(rsl, is(expect));
    }


    @Test
    public void NameLengthSortingFromSmallNameToLargeName() {
        SortUser sort = new SortUser();
        List<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(new User(90, "Ilya"), new User(45,"Bob"), new User (25, "Zinaida")));
        List<User> rsl = sort.sortNameLength(list);
        List<User> expect = new ArrayList<>();
        expect.addAll(Arrays.asList(new User(45, "Bob"), new User(90,"Ilya"), new User (25, "Zinaida")));
        assertThat(rsl, is(expect));
    }




    @Test
    public void SortingByNameAndAge() {
        SortUser sort = new SortUser();
        List<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(new User(90, "Ilya"), new User(45,"Bob"), new User(13, "Bob"),  new User (10, "Eva")));
        List<User> rsl = sort.sortByAllFields(list);
        List<User> expect = new ArrayList<>();
        expect.addAll(Arrays.asList(new User(13, "Bob"), new User(45, "Bob"), new User (10, "Eva"), new User(90,"Ilya")));
        assertThat(rsl, is(expect));
    }

}
