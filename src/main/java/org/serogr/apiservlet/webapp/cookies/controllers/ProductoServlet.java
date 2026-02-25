package org.serogr.apiservlet.webapp.cookies.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.serogr.apiservlet.webapp.cookies.models.Producto;
import org.serogr.apiservlet.webapp.cookies.services.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos.html"})
public class ProductoServlet extends HttpServlet {
    /*
     * Este servlet nos sirve para poder exportar los datos a un documento tipo .xls (Excel)
     * */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Se obtiene la conección de los atributos del Request
        //Se tiene que hacer un cast a tipo Connection
        //En este caso es el nombre "conn" porque con ese nombre lo guardamos en el filtro de la conección
        Connection conn = (Connection) req.getAttribute("conn");

        ProductoServiceI serviceI = new ProductoServiceJDBCImpl(conn);
        List<Producto> productos = serviceI.listar();

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        //Obtener mensaje del request de la sesión
        //Se crea y se destruye con cada rquest (petición)
        //String mensajeRequest = (String) req.getAttribute("mensaje");
        //Obtenemos el mensaje global de la app
        //Se crea una vez para toda la aplicación (Singleton) y se destruye al finalizar la aplicación
        //String mensajeGlobal = (String) getServletContext().getAttribute("mensaje");

        //Desde el Servlet "ProductoServlet.java" pasamos los atributos a la vista "listar.jsp"
        req.setAttribute("productos", productos);
        req.setAttribute("username", usernameOptional);
        getServletContext().getRequestDispatcher("/listar.jsp").forward(req, resp);

    }
}
