package ua.nure.serdiuk.web;

import ua.nure.serdiuk.Params;
import ua.nure.serdiuk.RSA;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/myserv")
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RSA rsa = new RSA();
        req.getSession().setAttribute(Params.RSA, rsa);
        String publicKey = rsa.getModule();

        String json = String.format("{\"public\":\"%s\"}", publicKey);
        resp.getWriter().write(json);
    }
}
