package org.serogr.apiservlet.webapp.cookies.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class LoginServiceSessionImpl implements LoginService{

    @Override
    public Optional<String> getUsername(HttpServletRequest request) {
        //Primero creamos el objeto de tipo HttpSession
        HttpSession session = request.getSession();
        // Luego obtenemos el valor del atributo "username" y lo guardamos
        String username = (String) session.getAttribute("username");
        //Validamos que no venga un objeto vacío
        if (username != null){
            return Optional.of(username);
        }
        return Optional.empty();
    }
}
