package mx.uamex.fi.paradigmas.model;

import mx.uamex.fi.paradigmas.model.data.Jugador;
import java.util.List;

public interface JugadoresDAO {

    void insertar(Jugador jugador);

    List<Jugador> consultar();

    void actualizar(Jugador jugador);

    void borrar(Jugador jugador);
}
