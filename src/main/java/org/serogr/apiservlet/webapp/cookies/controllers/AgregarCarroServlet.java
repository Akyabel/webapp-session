package org.serogr.apiservlet.webapp.cookies.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.serogr.apiservlet.webapp.cookies.models.Carro;
import org.serogr.apiservlet.webapp.cookies.models.ItemCarro;
import org.serogr.apiservlet.webapp.cookies.models.Producto;
import org.serogr.apiservlet.webapp.cookies.services.ProductoServiceI;
import org.serogr.apiservlet.webapp.cookies.services.ProductoServiceImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/agregar-carro")
public class AgregarCarroServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        ProductoServiceI service = new ProductoServiceImpl();
        Optional<Producto> producto = service.findById(id);
        if (producto.isPresent()){
            ItemCarro item = new ItemCarro(1, producto.get());
            HttpSession session = req.getSession();
            Carro carro = (Carro) session.getAttribute("carro");
            carro.agregarItem(item);
        }
        resp.sendRedirect(req.getContextPath() + "/ver-carro");
    }
}
