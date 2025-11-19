package respository;

import db.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LibroRepository {

    public static void listarLibros() {
        // Implementacion para listar libros

        String sql = "SELECT * FROM libros";

        try (Connection connection = Conexion.getConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String isbn = resultSet.getString("isbn");
                String titulo = resultSet.getString("titulo");
                String autor = resultSet.getString("autor");
                int anioPublicacion = resultSet.getInt("anio_publicacion");
                int cantidadTotal = resultSet.getInt("cantidad_total");
                int cantidadDisponible = resultSet.getInt("cantidad_disponible");
                System.out.println("ISBN: " + isbn + ", Titulo: " + titulo + ", Autor: " + autor +
                        ", Año Publicación: " + anioPublicacion + ", Cantidad Total: " + cantidadTotal +
                        ", Cantidad Disponible: " + cantidadDisponible);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
