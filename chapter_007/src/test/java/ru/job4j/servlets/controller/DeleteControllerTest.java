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
public class DeleteControllerTest {
   /* @Test
    public void whenAddThreeUsersAndDeleteTwoThenStoreIt() throws ServletException, IOException {
        AdminController controller = new AdminController();
        ValidateStub validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        Mockito.when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        validate.add("lola", "user",  "lola@bk.ru", "123", "LA");
        validate.add("ivan", "user",  "azaza@bk.ru", "4112", "RF");
        validate.add("olga", "admin",  "olga@bk.ru", "123456", "RF");
        User user = validate.findByLogin("lola");
        String id = String.valueOf(user.getId());
        when(req.getParameter("id")).thenReturn(id);
        controller.doPost(req, resp);
        User user1 = validate.findByLogin("ivan");
        String id1 = String.valueOf(user1.getId());
        when(req.getParameter("id")).thenReturn(id1);
        controller.doPost(req, resp);
        assertThat(validate.findAll().iterator().next().getLogin(), is("olga"));
    }
*/
}