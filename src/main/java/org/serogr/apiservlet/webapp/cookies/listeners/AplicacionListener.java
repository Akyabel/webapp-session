package org.serogr.apiservlet.webapp.cookies.listeners;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.serogr.apiservlet.webapp.cookies.models.Carro;

/*
* Esta clase no hereda de ninguna otra; solo se tiene que implementar interfaces e
* implementar el método cuando se inicializa y cuando se destruye.
*
* Si se quiere útilizar para un contexto general dentro de toda la aplicación como:
*  -Inicializar recursos globales
* -Configuraciones
* -Una conexión a la base de datos que sea común para toda la aplicación
* Se utiliza el 'ServletContextListener'
*
* Para peticiones se útiliza el 'ServletRequestListener'
*
* Para las sesiones se útiliza 'HttpSessionListener'
*
* Indicar que es un Listener de la APIServlet con la anotación '@WebListener'
* */
@WebListener
public class AplicacionListener implements ServletContextListener, ServletRequestListener, HttpSessionListener {
    //Se crea atributo del tipo ServletContext
    private ServletContext servletContext;

    //ServletContextListener
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("Inicializando la aplicación!");
        //Se inicializa el atributo de tipo ServletContextListener
        servletContext = sce.getServletContext();
        servletContext.setAttribute("mensaje", "algún valor global de la aplicación!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        servletContext.log("Destruyendo la aplicación!");
    }
    //ServletRequestListener
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        servletContext.log("Inicializando el Request!");
        sre.getServletRequest().setAttribute("mensaje", "Guardando algún valor para el request!");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        servletContext.log("Destruyendo el Request!");
    }
    //HttpSessionListener
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log("Inicializando la sesión Http!");
        Carro carro = new Carro();
        //Obtenemos la sesión
        HttpSession session = se.getSession();
        session.setAttribute("carro", carro);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("Destruyendo la sesión Http!");
    }
}
