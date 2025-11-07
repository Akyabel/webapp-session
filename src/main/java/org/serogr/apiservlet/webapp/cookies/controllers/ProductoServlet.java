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
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos.html"})
public class ProductoServlet extends HttpServlet {
    /*
     * Este servlet nos sirve para poder exportar los datos a un documento tipo .xls (Excel)
     * */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoServiceI serviceI = new ProductoServiceImpl();
        List<Producto> productos = serviceI.listar();

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

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
                            + "/agregar-carro?id=" + p.getId()
                            + "\">Agregar al carro</a></td>");
                }
                out.println("</tr>");
            });
            out.println("       </table>");
            out.println("   </body>");
            out.println("</html>");
        }
    }
}
