package respository;

import db.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AutorRespository {

    public static void listarAutores() {
        String sql = "SELECT * FROM autores";
        /*Consulta a la base de datos
         *
         * Probamos la conexion usando la clase creada Conexion*/
        try (Connection connection = Conexion.getConnection()) {
            Statement statement = connection.createStatement();
            var resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String nacionalidad = resultSet.getString("nacionalidad");

                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Nacionalidad: " + nacionalidad);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void InsertarAutor(String nombre, String nacionalidad) {
        String sql = "INSERT INTO autores (nombre, nacionalidad) VALUES (?, ?)";
        /*Consulta a la base de datos
         *
         * Probamos la conexion usando la clase creada Conexion*/
        try (Connection connection = Conexion.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, nacionalidad);

            preparedStatement.executeUpdate();

            System.out.println("Autor insertado correctamente");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void EliminarAutor(int id) {
        String sql = "DELETE FROM autores WHERE id = ?";
        /*Consulta a la base de datos
         *
         * Probamos la conexion usando la clase creada Conexion*/
        try (Connection connection = Conexion.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            System.out.println("Autor eliminado correctamente");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void ActualizarAutor(int id, String nombre, String nacionalidad) {
        String sql = "UPDATE autores SET nombre = ?, nacionalidad = ? WHERE id = ?";
        /*Consulta a la base de datos
         *
         * Probamos la conexion usando la clase creada Conexion*/
        try (Connection connection = Conexion.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, nacionalidad);
            preparedStatement.setInt(3, id);

            preparedStatement.executeUpdate();

            System.out.println("Autor actualizado correctamente");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void BuscarAutorPorId(int id) {
        String sql = "SELECT * FROM autores WHERE id = ?";
        /*Consulta a la base de datos
         *
         * Probamos la conexion usando la clase creada Conexion*/
        try (Connection connection = Conexion.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String nacionalidad = resultSet.getString("nacionalidad");

                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Nacionalidad: " + nacionalidad);
            } else {
                System.out.println("Autor no encontrado con ID: " + id);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void BuscarAutorPorNombre(String nombre) {
        String sql = "SELECT * FROM autores WHERE nombre LIKE ?";
        /*Consulta a la base de datos
         *
         * Probamos la conexion usando la clase creada Conexion*/
        try (Connection connection = Conexion.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + nombre + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nacionalidad = resultSet.getString("nacionalidad");

                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Nacionalidad: " + nacionalidad);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void BuscarAutorPorNacionalidad(String nacionalidad) {
        String sql = "SELECT * FROM autores WHERE nacionalidad LIKE ?";
        /*Consulta a la base de datos
         *
         * Probamos la conexion usando la clase creada Conexion*/
        try (Connection connection = Conexion.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + nacionalidad + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");

                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Nacionalidad: " + nacionalidad);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}