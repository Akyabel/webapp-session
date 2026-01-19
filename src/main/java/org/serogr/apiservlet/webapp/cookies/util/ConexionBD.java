package org.serogr.apiservlet.webapp.cookies.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static String url = "jdbc:mysql://localhost:3306/java_curso?serverTimeZone=America/Mexico_City";
    private static String username = "root";
    private static String password = "5804220";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
