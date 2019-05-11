package ru.job4j.carMarket.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.Test;
import ru.job4j.carMarket.ConnectionRollback;
import ru.job4j.carMarket.model.entity.Car;
import ru.job4j.carMarket.model.entity.User;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class HiberStorageTest {

}
//
//    @Test
//    public void whenCreateAndFindUser() {
//        SessionFactory factory = ConnectionRollback.create(HibernateFactory.FACTORY);
//        Session session = factory.openSession();
//        HiberStorage store = new HiberStorage(factory);
//        User user = new User();
//        user.setLogin("login");
//        user.setPassword("password");
//        store.addUser(user);
//        User userRsl = store.findUserByLogin("login");
//        assertThat(
//                userRsl.getPassword(),
//                is("password")
//        );
//        session.clear();
//      //  assertThat(store.findUserByLogin("login").getLogin(), is("ttt"));
//        assertNull(store.findUserByLogin("login"));
//        factory.close();
//    }
//
//    @Test
//    public void whenAddCarToUser() {
//        SessionFactory factory = ConnectionRollback.create(HibernateFactory.FACTORY);
//        Session session = factory.openSession();
//        HiberStorage store = new HiberStorage(factory);
//        User user = new User();
//        user.setLogin("login");
//        user.setPassword("password");
//        user.setCars(new ArrayList<Car>());
//        store.addUser(user);
//        Car car = new Car("Kia", "Kia1", "mechanic", "sedan", 2006, 500000, "path.jpg", null);
//        store.addCarToUser(store.addUser(user), car);
//        List<Car> cars = store.getCars();
//        System.out.println(user);
//        session.clear();


        //        assertThat(
        ////                userRsl.getCars().get(0).getModel(),
        ////                is("Kia1")
        ////        );
        //  assertThat(store.findUserByLogin("login").getLogin(), is("ttt"));
     //   assertNull(store.findUserByLogin("login"));
//        factory.close();
//    }
//
//    @Test
//    public void whenAddCarToUser2() {
//        HiberStorage store = new HiberStorage();
//        User user = new User();
//        user.setLogin("login");
//        user.setPassword("password");
//        store.addUser(user);
//        Car car = new Car("Kia", "Kia1", "mechanic", "sedan", 2006, 500000, "path.jpg", null);
//        store.addCarToUser(user, car);
//        User userRsl = store.findUserByLogin("login");
////        assertThat(
////                userRsl.getCars().get(0).getModel(),
////                is("Kia1")
////        );
//        System.out.println(userRsl);
//        //  assertThat(store.findUserByLogin("login").getLogin(), is("ttt"));
//        assertNull(store.findUserByLogin("login"));
//
//    }
//
//    /**
//     * Clean tests data.
//     */
//    @AfterClass
//    public static void close() {
//        HibernateFactory.FACTORY.close();
//    }
//
//}