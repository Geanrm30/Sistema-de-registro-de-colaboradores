package ni.edu.uam.sistema_de_registro_de_colaboradores.dao;

import ni.edu.uam.sistema_de_registro_de_colaboradores.interfaces.CRUD;
import ni.edu.uam.sistema_de_registro_de_colaboradores.models.Colaboradores;

import java.util.ArrayList;
import java.util.List;

public class ColaboradorDAO implements CRUD<Colaboradores> {
    private final List<Colaboradores> colaboradores;

    public ColaboradorDAO() {
        colaboradores = new ArrayList<>();
    }

    @Override
    public void agregar(Colaboradores entidad) {
        colaboradores.add(entidad);
    }

    @Override
    public List<Colaboradores> obtenerRegistros() {
        return colaboradores;
    }

    public void eliminar(Colaboradores entidad) {
        colaboradores.remove(entidad);
    }
}