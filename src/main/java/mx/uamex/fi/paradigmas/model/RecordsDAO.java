package mx.uamex.fi.paradigmas.model;

import mx.uamex.fi.paradigmas.model.data.Record;
import java.util.List;

public interface RecordsDAO {

    void insertar(Record record);

    List<Record> consultar();

    void actualizar(Record record);

    void borrar(Record record);
}
