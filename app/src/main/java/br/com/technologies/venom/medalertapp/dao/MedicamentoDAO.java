package br.com.technologies.venom.medalertapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import br.com.technologies.venom.medalertapp.models.Medicamento;

@Dao
public abstract class MedicamentoDAO implements DAOGenerico<Medicamento> {
    @Query("SELECT * FROM medicamentos")
    public abstract LiveData<List<Medicamento>> listar();

    @Query("SELECT * FROM medicamentos where receitaId = :receitaId")
    public abstract LiveData<List<Medicamento>> listarPorReceitaId(Long receitaId);

    @Query("DELETE FROM medicamentos")
    public abstract void excluirTodos();
}