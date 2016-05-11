package ua.nure.serdiuk.web;

import org.apache.log4j.Logger;
import ua.nure.serdiuk.Params;
import ua.nure.serdiuk.keygen.Keygen;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");

        Keygen keygen = (Keygen) req.getSession().getAttribute(Params.KEYGEN);
        System.out.println(password);

        System.out.println(keygen.encrypt("chris".getBytes()));

        byte[] array = keygen.encrypt("chris".getBytes()).getBytes();

        System.out.println(keygen.encrypt("chris".getBytes("utf-8")));


        System.out.println(keygen.decrypt(password));
    }
}
