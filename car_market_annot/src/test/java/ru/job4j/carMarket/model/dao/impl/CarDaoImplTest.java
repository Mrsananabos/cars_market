package ru.job4j.carMarket.model.dao.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.job4j.carMarket.model.entity.Car;
import ru.job4j.carMarket.model.entity.User;
import ru.job4j.carMarket.model.service.ValidateServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class CarDaoImplTest {

    @Mock
    private UserDaoImpl daoMock;

    @InjectMocks
    private ValidateServiceImpl service;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddCustomer_returnsNewCustomer() {
        when(daoMock.addUser(any(User.class))).thenReturn(new User());
        List<Car> cars = new ArrayList<>();
        assertNotNull(service.addUser("login", "password"));
    }


}