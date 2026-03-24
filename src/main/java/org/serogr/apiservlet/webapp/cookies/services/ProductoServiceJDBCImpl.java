package org.serogr.apiservlet.webapp.cookies.services;

import org.serogr.apiservlet.webapp.cookies.models.Categoria;
import org.serogr.apiservlet.webapp.cookies.models.Producto;
import org.serogr.apiservlet.webapp.cookies.repositories.CategoriaRepositoryImpl;
import org.serogr.apiservlet.webapp.cookies.repositories.ProductoRepositoryJDBCImpl;
import org.serogr.apiservlet.webapp.cookies.repositories.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoServiceJDBCImpl implements ProductoServiceI{
    private Repository<Producto> repositoryJDBC;
    private Repository<Categoria> repositoryCategoriaJDBC;

    public ProductoServiceJDBCImpl(Connection connection) {
        this.repositoryJDBC = new ProductoRepositoryJDBCImpl(connection);
        this.repositoryCategoriaJDBC = new CategoriaRepositoryImpl(connection);
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

    @Override
    public Optional<Producto> buscarProducto(String nombre) {
        ProductoServiceI service = new ProductoServiceImpl();
        Optional<Producto> encontrado = service.listar().stream().filter(producto -> {
            if (nombre == null || nombre.isBlank()){
                return false;
            }
            return producto.getNombre().contains(nombre);
        }).findFirst();
        return encontrado;
    }

    @Override
    public void guardar(Producto producto) {
        try {
            repositoryJDBC.guardar(producto);
        } catch (SQLException throwables) {
            throw new ServiceJDBCException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
           repositoryJDBC.eliminar(id);
        } catch (SQLException throwables) {
            throw new ServiceJDBCException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public List<Categoria> listarCategoria() {
        try {
           return repositoryCategoriaJDBC.listar();
        } catch (SQLException throwables) {
            throw new ServiceJDBCException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Categoria> findByIdCategoria(Long id) {
        try {
            return Optional.ofNullable(repositoryCategoriaJDBC.porID(id));
        } catch (SQLException throwables) {
            throw new ServiceJDBCException(throwables.getMessage(), throwables.getCause());
        }
    }

}
