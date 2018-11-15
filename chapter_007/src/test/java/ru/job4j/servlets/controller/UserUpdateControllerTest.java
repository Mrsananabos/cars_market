package ru.job4j.servlets.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.servlets.model.User;
import ru.job4j.servlets.model.ValidateService;
import ru.job4j.servlets.model.ValidateStub;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserUpdateControllerTest {
  /*  @Test
    public void whenAddUserAndUpdateThenStoreIt() throws ServletException, IOException {
        UserUpdateController updateController = new UserUpdateController();
        ValidateStub validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        Mockito.when(ValidateService.getInstance()).thenReturn(validate);
        HttpSession session =  mock(HttpSession.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        validate.add("lola", "user",  "lola@bk.ru", "123", "LA");
        User user = validate.findByLogin("lola");
        String id = String.valueOf(user.getId());
        when(req.getParameter("id")).thenReturn(id);
        when(req.getParameter("login")).thenReturn("katya");
        when(req.getParameter("role")).thenReturn("user");
        when(req.getParameter("email")).thenReturn("lola@bk.ru");
        when(req.getParameter("password")).thenReturn("123");
        when(req.getParameter("address")).thenReturn("LA");
        when(session.getAttribute("role")).thenReturn(user.getRole().name());
        when(req.getSession()).thenReturn(session);
        updateController.doPost(req, resp);
        validate.findAll();
        assertThat(validate.findAll().iterator().next().getLogin(), is("katya"));
    }
*/
}