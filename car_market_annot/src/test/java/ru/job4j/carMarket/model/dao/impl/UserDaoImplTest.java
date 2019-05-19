package ru.job4j.carMarket.model.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import ru.job4j.carMarket.model.entity.User;
import ru.job4j.carMarket.model.service.UserValidate;
import ru.job4j.carMarket.model.service.impl.UserValidateImpl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UserValidateImpl.class)
public class UserDaoImplTest {

    @Test
    public void testAddCustomer_returnsNewCustomer() {
        UserValidate validate = mock(UserValidateImpl.class);
        Whitebox.setInternalState(UserValidateImpl.class, "INSTANCE", validate);
        when(validate.addUser(anyString(), anyString())).thenReturn(new User());
        assertNotNull(validate.addUser("login", "password"));

    }


}