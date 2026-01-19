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
        String mensajeRequest = (String) req.getAttribute("mensaje");
        //Obtenemos el mensaje global de la app
        //Se crea una vez para toda la aplicación (Singleton) y se destruye al finalizar la aplicación
        String mensajeGlobal = (String) getServletContext().getAttribute("mensaje");
        resp.setContentType("text/html;charset=UTF8");

        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("   <head>");
            out.println("       <meta charset=\"UTF-8\">");
            out.println("           <title>Listado de productos</title>");
            out.println("   </head>");
            out.println("   <body>");
            out.println("       <h1>Listado de productos!</h1>");
            if (usernameOptional.isPresent()){
                out.println("<div style='color: blue;'> Hola " + usernameOptional.get() + "</div>");
            }
            out.println("       <table>");
            out.println("       <tr>");
            out.println("       <th>id</th>");
            out.println("       <th>Nombre producto</th>");
            out.println("       <th>Categoría</th>");
            if (usernameOptional.isPresent()) {
                out.println("       <th>Precio</th>");
                out.println("       <th>Agregar</th>");
            }
            out.println("       </tr>");
            productos.forEach(p -> {
                out.println("<tr>");
                out.println("   <td>" + p.getId() + "</td>");
                out.println("   <td>" + p.getNombre() + "</td>");
                out.println("   <td>" + p.getTipo() + "</td>");
                if (usernameOptional.isPresent()) {
                    out.println("   <td>" + p.getPrecio() + "</td>");
                    out.println("   <td><a href=\" " +req.getContextPath()
                            + "/carro/agregar?id=" + p.getId()
                            + "\">Agregar al carro</a></td>");
                }
                out.println("</tr>");
            });
            out.println("       </table>");
            out.println("       <p>" + mensajeGlobal + "</p>");
            out.println("       <p>" + mensajeRequest + "</p>");
            out.println("   </body>");
            out.println("</html>");
        }
    }
}
