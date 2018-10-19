package ru.job4j.servlets.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.servlets.model.ValidateService;
import ru.job4j.servlets.model.ValidateStub;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserCreateControllerTest {
    @Test
    public void whenAddUserThenStoreIt() throws ServletException, IOException {
        UserCreateController createController = new UserCreateController();
        ValidateStub validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        Mockito.when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("login")).thenReturn("lola");
        when(req.getParameter("role")).thenReturn("user");
        when(req.getParameter("email")).thenReturn("lola@bk.ru");
        when(req.getParameter("password")).thenReturn("123");
        when(req.getParameter("address")).thenReturn("LA");
        createController.doPost(req, resp);
        assertThat(validate.findAll().iterator().next().getLogin(), is("lola"));
    }
}