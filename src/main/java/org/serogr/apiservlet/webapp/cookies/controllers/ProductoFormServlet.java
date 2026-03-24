package org.serogr.apiservlet.webapp.cookies.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.serogr.apiservlet.webapp.cookies.services.ProductoServiceI;
import org.serogr.apiservlet.webapp.cookies.services.ProductoServiceJDBCImpl;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/productos/form")
public class ProductoFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Presentar el formulario al usuario
        //1- Obtener el listado de categorías que van en la lista servlet del formulario.
        // Crear y establecer conección a la DB.
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoServiceI service = new ProductoServiceJDBCImpl(conn);
        req.setAttribute("categorias", service.listarCategoria());
        // Despachar la vista .jsp (el formulario con el nombre que le vamos a dar)
        getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
