package org.serogr.apiservlet.webapp.cookies.services;

import org.serogr.apiservlet.webapp.cookies.models.Categoria;
import org.serogr.apiservlet.webapp.cookies.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoServiceI {
    List<Producto> listar();
    Optional<Producto> findById(Long id);
    Optional<Producto> buscarProducto(String nombre);
    void guardar(Producto producto);
    void eliminar(Long id);

    List<Categoria> listarCategoria();
    Optional<Categoria> findByIdCategoria(Long id);
    //void guardarCategoria(Categoria categoria);
    //void eliminarCategoria(Long id);
}
