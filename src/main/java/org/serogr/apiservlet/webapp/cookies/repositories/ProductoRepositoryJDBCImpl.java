package org.serogr.apiservlet.webapp.cookies.repositories;

import org.serogr.apiservlet.webapp.cookies.models.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoryJDBCImpl implements Repository<Producto> {
    /*Para generar la conexión a la BD es necesario una instancia de la conexión, en este caso la variable conn
    * al igual que el constructor para que reciba la conexión,
    * */
    private Connection conn;

    public ProductoRepositoryJDBCImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT p.*, c.nombre as categoria FROM productos as p " +
                    " INNER JOIN categorias as c ON (p.categoria_id = c.id)")){
            while (rs.next()){
                Producto producto = getProducto(rs);

                productos.add(producto);
            }
        }
        return productos;
    }

    @Override
    public Producto porID(Long id) throws SQLException {
        //Crear la instancia de la clase Producto
        Producto producto = null;
        try(PreparedStatement stmt = conn.prepareStatement("SELECT p.*, c.nombre as categoria FROM productos AS p " +
                "INNER JOIN categorias AS c ON (p.categoria_id = c.id) WHERE p.id = ?")){
            stmt.setLong(1, id);

            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    producto = getProducto(rs);
                }
            }
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }

    private Producto getProducto(ResultSet rs) throws SQLException {
        Producto producto = new Producto();
        producto.setId(rs.getLong("id"));
        producto.setNombre(rs.getString("nombre"));
        producto.setPrecio(rs.getInt("precio"));
        producto.setTipo(rs.getString("categoria"));
        return producto;
    }
}
