package respository;

import v db.Conexion;
import model.Socio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SocioRepository {

    private Socio mapearSocio(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        String apellido = rs.getString("apellido");
        String dni = rs.getString("dni");
        String telefono = rs.getString("telefono");
        return new Socio(id, nombre, apellido, dni, telefono);
    }

    public void insertarSocio(Socio socio) {
        String sql = "INSERT INTO socios (nombre, apellido, dni, telefono) VALUES (?, ?, ?, ?)";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, socio.getNombre());
            preparedStatement.setString(2, socio.getApellido());
            preparedStatement.setString(3, socio.getDni());
            preparedStatement.setString(4, socio.getTelefono());

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("✅ Socio " + socio.getNombre() + " insertado correctamente.");
            }

        } catch (SQLException e) {
            // Código de error 23000 es común para UNIQUE constraint violation (DNI duplicado)
            if (e.getSQLState().startsWith("23")) {
                System.err.println("❌ Error: El DNI " + socio.getDni() + " ya existe en la base de datos (UNIQUE constraint).");
            } else {
                System.err.println("Error al insertar socio: " + e.getMessage());
            }
            e.printStackTrace();
        }
    }

    /**
     * Busca y devuelve un Socio por su número de DNI.
     * @param dni El DNI a buscar.
     * @return El objeto Socio encontrado o null si no existe.
     */
    public Socio buscarSocioPorDNI(String dni) {
        String sql = "SELECT id, nombre, apellido, dni, telefono FROM socios WHERE dni = ?";
        Socio socioEncontrado = null;

        try (Connection connection = Conexion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, dni);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    socioEncontrado = mapearSocio(resultSet);
                    // No imprimir aquí, la clase Main decide qué imprimir.
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar socio por DNI: " + e.getMessage());
            e.printStackTrace();
        }
        return socioEncontrado;
    }

    /**
     * Lista todos los socios de la base de datos.
     * @return Una lista de objetos Socio.
     */
    public List<Socio> listarSocios() {
        List<Socio> socios = new ArrayList<>();
        String sql = "SELECT id, nombre, apellido, dni, telefono FROM socios";

        try (Connection connection = Conexion.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                socios.add(mapearSocio(resultSet));
            }

        } catch (SQLException e) {
            System.err.println("Error al listar socios: " + e.getMessage());
            e.printStackTrace();
        }
        return socios;
    }

    /**
     * Modifica los datos de un socio existente.
     * @param socio Objeto Socio con los datos actualizados (se usa el ID para la búsqueda).
     */
    public void modificarSocio(Socio socio) {
        String sql = "UPDATE socios SET nombre = ?, apellido = ?, dni = ?, telefono = ? WHERE id = ?";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, socio.getNombre());
            preparedStatement.setString(2, socio.getApellido());
            preparedStatement.setString(3, socio.getDni());
            preparedStatement.setString(4, socio.getTelefono());
            preparedStatement.setInt(5, socio.getId());

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("✅ Socio con ID " + socio.getId() + " actualizado correctamente.");
            } else {
                System.out.println("⚠️ No se encontró un socio con ID " + socio.getId() + " para actualizar.");
            }

        } catch (SQLException e) {
            System.err.println("Error al modificar socio: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Da de baja un socio si no tiene préstamos activos.
     * @param idSocio El ID del socio a eliminar.
     */
    public void darBajaSocio(int idSocio) {
        // 1. Consulta para verificar préstamos activos ('PRESTADO' en la tabla prestamos)
        String sqlCheck = "SELECT COUNT(*) FROM prestamos WHERE socio_id = ? AND estado = 'PRESTADO'";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement checkStmt = connection.prepareStatement(sqlCheck)) {

            checkStmt.setInt(1, idSocio);

            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    System.err.println("❌ ERROR: El socio con ID " + idSocio + " tiene préstamos activos. No se puede dar de baja.");
                    return;
                }
            }

            // 2. Si no hay préstamos activos, proceder a eliminar
            String sqlDelete = "DELETE FROM socios WHERE id = ?";
            try (PreparedStatement deleteStmt = connection.prepareStatement(sqlDelete)) {
                deleteStmt.setInt(1, idSocio);

                int filasAfectadas = deleteStmt.executeUpdate();

                if (filasAfectadas > 0) {
                    System.out.println("✅ Socio con ID " + idSocio + " dado de baja exitosamente.");
                } else {
                    System.out.println("⚠️ No se encontró un socio con ID " + idSocio + " para dar de baja.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al dar de baja socio: " + e.getMessage());
            e.printStackTrace();
        }
    }
}