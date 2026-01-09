package org.serogr.apiservlet.webapp.cookies.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.serogr.apiservlet.webapp.cookies.util.ConexionBD;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

//Como la conexión a la bd se va a aplicar a cualquier cosa que lo necesite, ponemos todas las rutas
@WebFilter("/*")
public class ConexionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try (Connection conn = ConexionBD.getConnection()){
            if (conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            try {
                servletRequest.setAttribute("conn", conn);
                filterChain.doFilter(servletRequest, servletResponse);
                conn.commit();
            } catch (SQLException e){
                conn.rollback();
                ((HttpServletResponse)servletResponse).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
