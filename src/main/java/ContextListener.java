/**
 * Created by Администратор on 27.05.2016.
 */

import ua.nure.serdiuk.Params;
import ua.nure.serdiuk.entity.User;
import ua.nure.serdiuk.repository.UserRepository;
import ua.nure.serdiuk.service.impl.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        populateDb();

        context.setAttribute(Params.USER_SERVICE, new UserServiceImpl());

        System.out.println("Context initialized");
    }

    private void populateDb() {
        UserRepository repository = UserRepository.getInstance();

        User user = new User();
        user.setLogin("daria");
        user.setPassword("1234");
        repository.add(user);

        user = new User();
        user.setLogin("user");
        user.setPassword("user");
        repository.add(user);
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }

}
