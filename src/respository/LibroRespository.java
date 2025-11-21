package respository;
import db.Conexion;

import model.Libro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LibroRespository{

    public List<Libro> listarLibros() {
        //listar libros
        String sql = "SELECT * FROM libros";
        List<Libro> libros = null;
        try (Connection connection = Conexion.getConnection()) {
            libros = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String isbn = resultSet.getString("isbn");
                String titulo = resultSet.getString("titulo");
                int autorId = resultSet.getInt("autor_id");
                int año_publicacion = resultSet.getInt("año_publicacion");
                int cantidadTotal = resultSet.getInt("cantidad_total");
                int cantidadDisponible = resultSet.getInt("cantidad_disponible");

                Libro libro = new Libro(isbn, titulo, autorId, año_publicacion, cantidadTotal, cantidadDisponible);
                libros.add(libro);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return libros;
    }
    public void agregarLibro(Libro libro) {
        String sql = "INSERT INTO libros (isbn, titulo, autor_id, año_publicacion, cantidad_total, cantidad_disponible) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = Conexion.getConnection()) {
            var preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, libro.getIsbn());
            preparedStatement.setString(2, libro.getTitulo());
            preparedStatement.setInt(3, libro.getAutorId());
            preparedStatement.setInt(4, libro.getAñoPublicacion());
            preparedStatement.setInt(5, libro.getCantidadTotal());
            preparedStatement.setInt(6, libro.getCantidadDisponible());

            preparedStatement.executeUpdate();

            System.out.println("Libro agregado correctamente");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void eliminarLibro(String isbn) {
        String sql = "DELETE FROM libros WHERE isbn = ?";
        try (Connection connection = Conexion.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, isbn);
            preparedStatement.executeUpdate();

            System.out.println("Libro eliminado correctamente");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void actualizarLibro(Libro libro) {
        String sql = "UPDATE libros SET titulo = ?, autor_id = ?, año_publicacion = ?, cantidad_total = ?, cantidad_disponible = ? WHERE isbn = ?";
        try (Connection connection = Conexion.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, libro.getTitulo());
            preparedStatement.setInt(2, libro.getAutorId());
            preparedStatement.setInt(3, libro.getAñoPublicacion());
            preparedStatement.setInt(4, libro.getCantidadTotal());
            preparedStatement.setInt(5, libro.getCantidadDisponible());
            preparedStatement.setString(6, libro.getIsbn());

            preparedStatement.executeUpdate();

            System.out.println("Libro actualizado correctamente");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Libro buscarLibroPorIsbn(String isbn) {
        String sql = "SELECT * FROM libros WHERE isbn = ?";
        Libro libroEncontrado = null;
        try (Connection connection = Conexion.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, isbn);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String titulo = resultSet.getString("titulo");
                int autorId = resultSet.getInt("autor_id");
                int año_publicacion = resultSet.getInt("año_publicacion");
                int cantidadTotal = resultSet.getInt("cantidad_total");
                int cantidadDisponible = resultSet.getInt("cantidad_disponible");

                libroEncontrado = new Libro(isbn, titulo, autorId, año_publicacion, cantidadTotal, cantidadDisponible);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return libroEncontrado;
    }
    public List<Libro> filtrarLibrosPorTitulo(String titulo){
        // Usar LIKE para buscar coincidencias parciales en el título
        String sql = "SELECT * FROM libros WHERE titulo LIKE ?";
        List<Libro> librosFiltrados = new ArrayList<>();
        try (Connection connection = Conexion.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + titulo + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String isbn = resultSet.getString("isbn");
                String tituloEncontrado = resultSet.getString("titulo");
                int autorId = resultSet.getInt("autor_id");
                int año_publicacion = resultSet.getInt("año_publicacion");
                int cantidadTotal = resultSet.getInt("cantidad_total");
                int cantidadDisponible = resultSet.getInt("cantidad_disponible");

                // Crear el objeto Libro con el título de la base de datos
                Libro libroEncontrado = new Libro(isbn, tituloEncontrado, autorId, año_publicacion, cantidadTotal, cantidadDisponible);
                librosFiltrados.add(libroEncontrado);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return librosFiltrados;
    }

}
