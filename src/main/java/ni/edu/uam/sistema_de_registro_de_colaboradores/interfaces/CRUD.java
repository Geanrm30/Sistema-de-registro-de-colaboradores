package ni.edu.uam.sistema_de_registro_de_colaboradores.interfaces;

import java.util.List;

public interface CRUD <T>{
    public void agregar(T entidad);

    public List<T> obtenerRegistros();

    public void eliminar(T entidad);
}
