package mx.uamex.fi.paradigmas.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractSqlDAO {
    protected Connection conexion;
    private static final String URL = "jdbc:mysql://localhost:3306/pptls";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public AbstractSqlDAO() {
        conectar();
    }

    protected void conectar() {
        try {
            if (conexion == null || conexion.isClosed()) {
                conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar a la base de datos", e);
        }
    }

    protected void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al cerrar la conexión", e);
        }
    }

    public Connection getConexion() {
        return conexion;
    }
}
