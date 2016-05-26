package ua.nure.serdiuk.web;

import org.apache.log4j.Logger;
import ua.nure.serdiuk.Params;
import ua.nure.serdiuk.RSA;
import ua.nure.serdiuk.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RSA rsa = (RSA) req.getSession().getAttribute(Params.RSA);

        String login = req.getParameter("login");
        String password = req.getParameter("passwordHidden");
        String decrypted = rsa.decrypt(password);

        UserService userService = (UserService) req.getServletContext().getAttribute(Params.USER_SERVICE);
        if (userService.auth(login, decrypted)) {
            System.out.println("Login successful");
            resp.sendRedirect("success.jsp");
        } else {
            req.getRequestDispatcher("").forward(req, resp);
        }
    }
}
