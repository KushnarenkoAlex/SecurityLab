package ua.nure.serdiuk.web;

import ua.nure.serdiuk.Params;
import ua.nure.serdiuk.keygen.Keygen;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;

@WebServlet("/myserv")
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Keygen keygen = new Keygen("RSA");
        req.getSession().setAttribute(Params.KEYGEN, keygen);

        Map<String, BigInteger> params = keygen.getPublicKeys();
        String json = String.format("{\"keys\":{\"n\":\"%d\", \"e\":\"%d\"}}", params.get("n"), params.get("e"));

        System.out.println("writing json " + json);

        resp.getWriter().write(json);
    }
}
