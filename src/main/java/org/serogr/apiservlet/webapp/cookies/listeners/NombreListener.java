package org.serogr.apiservlet.webapp.cookies.listeners;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;

public class NombreListener implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        sre.getServletRequest().setAttribute("nombre", "Sebastián Rojas Guerrero");
    }
}
