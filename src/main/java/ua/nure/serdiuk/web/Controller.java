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
import java.util.Base64;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(Controller.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        String password = req.getParameter("password");

        Keygen keygen = (Keygen) req.getSession().getAttribute(Params.KEYGEN);
        System.out.println(keygen.getPublicKeys());
        System.out.println(String.format("js: %s", password));

        System.out.println(keygen.encrypt("chris".getBytes()));

        byte[] array = keygen.encrypt("chris".getBytes()).getBytes();

        String base64 = Base64.getEncoder().encodeToString(array);
        System.out.println(base64);

        String decoded = new String(Base64.getDecoder().decode(password.getBytes()));
        System.out.println(String.format("Decode frm base64: %s", decoded));


        System.out.println(String.format("encrypted on server side: %s", keygen.encrypt("chris"
                .getBytes())));

//        System.out.println(keygen.encrypt("chris".getBytes("utf-8")));


//        System.out.println(keygen.decrypt(password));
    }
}
