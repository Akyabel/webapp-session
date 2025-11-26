package org.serogr.apiservlet.webapp.cookies.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.serogr.apiservlet.webapp.cookies.services.LoginService;
import org.serogr.apiservlet.webapp.cookies.services.LoginServiceSessionImpl;

import java.io.IOException;
import java.util.Optional;

// Acá se es especifíca sobre que Servlet se va a utilizar este Filter
//Como van a ser varias rutas se usan las llaves
@WebFilter({"/ver-carro", "/agregar-carro", "/actualizar-carro"})
public class LoginFiltro implements Filter {
    //Todas estas validaciones se ejecutan ANTES de llegar a ejecutar los SERVLETS en cuestión.
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LoginService service = new LoginServiceSessionImpl();
        Optional<String> usernameOptional =  service.getUsername((HttpServletRequest) servletRequest);
        if (usernameOptional.isPresent()){
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, "No está autorizado!");
        }
    }
}
