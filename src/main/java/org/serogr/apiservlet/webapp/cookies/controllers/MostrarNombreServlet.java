package org.serogr.apiservlet.webapp.cookies.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet({"/nombre.html", ""})
public class MostrarNombreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = (String) req.getAttribute("nombre");
        resp.setContentType("text/html;charset=UTF8");
        /*
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("   <head>");
            out.println("       <meta charset=\"UTF-8\">");
            out.println("           <title>Tarea 7 Listener</title>");
            out.println("   </head>");
            out.println("   <body>");
            out.println("       <h1>Tarea 7 Listener</h1>");
            out.println("       <h2> Mi nombre es " + nombre + "</h2>");
            out.println("   </body>");
            out.println("</html>");
        }*/
    }
}
