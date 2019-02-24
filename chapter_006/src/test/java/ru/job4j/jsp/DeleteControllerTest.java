package ru.job4j.jsp;

import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.crudServlets.model.service.ValidateService;


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