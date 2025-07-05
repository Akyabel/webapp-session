package org.serogr.apiservlet.webapp.cookies.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.serogr.apiservlet.webapp.cookies.services.LoginService;
import org.serogr.apiservlet.webapp.cookies.services.LoginServiceCookieImpl;
import org.serogr.apiservlet.webapp.cookies.services.LoginServiceSessionImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    /*
    * Se agrega el Link para cerrar el formulario
    * */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> username = auth.getUsername(req);

        if (username.isPresent()){
            HttpSession session = req.getSession();
            session.invalidate();
        }

        resp.sendRedirect(req.getContextPath() + "/index.html");
    }
}
