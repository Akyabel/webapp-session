package org.serogr.apiservlet.webapp.cookies.services;

import org.serogr.apiservlet.webapp.cookies.models.Producto;
import org.serogr.apiservlet.webapp.cookies.repositories.ProductoRepositoryJDBCImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoSerciveJDBCImpl implements ProductoServiceI{
    private ProductoRepositoryJDBCImpl repositoryJDBC;

    public ProductoSerciveJDBCImpl(Connection connection) {
        this.repositoryJDBC = new ProductoRepositoryJDBCImpl(connection);
    }

    @Override
    public List<Producto> listar() {
        try {
            return repositoryJDBC.listar();
        } catch (SQLException throwables) {
            throw new ServiceJDBCException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Producto> findById(Long id) {
        try {
            return Optional.ofNullable(repositoryJDBC.porID(id));
        } catch (SQLException throwables) {
            throw new ServiceJDBCException(throwables.getMessage(), throwables.getCause());
        }
    }
}
