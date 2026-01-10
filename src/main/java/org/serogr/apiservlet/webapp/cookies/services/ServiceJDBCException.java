package org.serogr.apiservlet.webapp.cookies.services;

public class ServiceJDBCException extends RuntimeException{
    public ServiceJDBCException(String mensaje){
        super(mensaje);
    }

    public ServiceJDBCException(String message, Throwable cause) {
        super(message, cause);
    }
}
