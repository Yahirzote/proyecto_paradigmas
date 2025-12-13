package mx.uamex.fi.paradigmas.model;
import mx.uamex.fi.paradigmas.model.data.Record;
import mx.uamex.fi.paradigmas.model.data.Jugador;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecordsDAOPsqlImp extends AbstractSqlDAO implements RecordsDAO {
    @Override
    public void insertar(Record record) {
        String sql = "INSERT INTO records (jugador, juego, fecha) VALUES (?, ?, ?)";

        conectar();
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, record.getJugador().getLogin());
            ps.setString(2, record.getJuego());
            ps.setDate(3, new java.sql.Date(record.getFecha().getTime()));

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar record", e);
        } finally {
            cerrarConexion();
        }
    }
    @Override
    public List<Record> consultar() {
        List<Record> lista = new ArrayList<>();
        String sql = "SELECT jugador, juego, fecha FROM records";

        conectar();
        try (Statement st = conexion.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Jugador jugador = new Jugador();
                jugador.setLogin(rs.getString("jugador"));

                Record record = new Record(
                        jugador,
                        rs.getString("juego"),
                        rs.getDate("fecha")
                );

                lista.add(record);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar records", e);
        } finally {
            cerrarConexion();
        }

        return lista;
    }
    @Override
    public void actualizar(Record record) {
        String sql = "UPDATE records SET juego = ?, fecha = ? WHERE jugador = ?";

        conectar();
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, record.getJuego());
            ps.setDate(2, new java.sql.Date(record.getFecha().getTime()));
            ps.setString(3, record.getJugador().getLogin());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar record", e);
        } finally {
            cerrarConexion();
        }
    }
    @Override
    public void borrar(Record record) {
        String sql = "DELETE FROM records WHERE jugador = ?";

        conectar();
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, record.getJugador().getLogin());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al borrar record", e);
        } finally {
            cerrarConexion();
        }
    }
}