package br.com.technologies.venom.medalertapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import br.com.technologies.venom.medalertapp.models.Paciente;

@Dao
public abstract class PacienteDAO implements DAOGenerico<Paciente> {
    @Query("SELECT * FROM pacientes")
    public abstract LiveData<List<Paciente>> listar();

    @Query("DELETE FROM pacientes")
    public abstract void excluirTodos();
}
