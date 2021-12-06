package br.com.technologies.venom.medalertapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import br.com.technologies.venom.medalertapp.models.Horario;

@Dao
public abstract class HorarioDAO implements DAOGenerico<Horario> {
    @Query("SELECT * FROM horarios")
    public abstract LiveData<List<Horario>> listar();

    @Query("SELECT * FROM horarios where medicamentoId = :medicamentoId and status = 0")
    public abstract LiveData<List<Horario>> listarPorMedicamentoId(String medicamentoId);

    @Query("DELETE FROM horarios")
    public abstract void excluirTodos();
}