package mx.uamex.fi.paradigmas.model;

import mx.uamex.fi.paradigmas.model.data.Jugador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JugadoresDAOPsqlImp extends AbstractSqlDAO implements JugadoresDAO {

    @Override
    public void insertar(Jugador jugador) {
        String sql = "INSERT INTO jugadores (login, password, correo, atributo) VALUES (?, ?, ?, ?)";

        conectar();
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, jugador.getLogin());
            ps.setString(2, jugador.getPassword());
            ps.setString(3, jugador.getCorreo());
            ps.setString(4, jugador.getAtributo());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar jugador", e);
        } finally {
            cerrarConexion();
        }
    }

    @Override
    public List<Jugador> consultar() {
        List<Jugador> lista = new ArrayList<>();
        String sql = "SELECT login, password, correo, atributo FROM jugadores";

        conectar();
        try (Statement st = conexion.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Jugador jugador = new Jugador(
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("correo"),
                        rs.getString("atributo")
                );
                lista.add(jugador);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar jugadores", e);
        } finally {
            cerrarConexion();
        }

        return lista;
    }

    @Override
    public void actualizar(Jugador jugador) {
        String sql = "UPDATE jugadores SET password = ?, correo = ?, atributo = ? WHERE login = ?";

        conectar();
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, jugador.getPassword());
            ps.setString(2, jugador.getCorreo());
            ps.setString(3, jugador.getAtributo());
            ps.setString(4, jugador.getLogin());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar jugador", e);
        } finally {
            cerrarConexion();
        }
    }

    @Override
    public void borrar(Jugador jugador) {
        String sql = "DELETE FROM jugadores WHERE login = ?";

        conectar();
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, jugador.getLogin());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al borrar jugador", e);
        } finally {
            cerrarConexion();
        }
    }
}
