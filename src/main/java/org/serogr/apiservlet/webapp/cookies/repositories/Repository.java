package org.serogr.apiservlet.webapp.cookies.repositories;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {
    //Lista los objetos (El método es de tipo genérico)
    List<T> listar() throws SQLException;
    // Obtiene el objeto por ID (El método es de tipo genérico)
    T porID(Long id) throws SQLException;
    //Guardar (El método es de tipo genérico)
    void guardar(T t) throws SQLException;
    //Eliminar (El método es de tipo genérico)
    void eliminar (Long id) throws SQLException;
}
