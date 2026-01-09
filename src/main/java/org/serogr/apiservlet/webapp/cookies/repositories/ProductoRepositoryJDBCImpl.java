package org.serogr.apiservlet.webapp.cookies.repositories;

import org.serogr.apiservlet.webapp.cookies.models.Producto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    private Producto getProducto(ResultSet rs) throws SQLException {
        Producto producto = new Producto();
        producto.setId(rs.getLong("id"));
        producto.setNombre(rs.getString("nombre"));
        producto.setPrecio(rs.getInt("precio"));
        producto.setTipo(rs.getString("categoria"));
        return producto;
    }

    @Override
    public Producto porID(Long id) throws SQLException {
        return null;
    }

    @Override
    public void guardar(Producto producto) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }
}
