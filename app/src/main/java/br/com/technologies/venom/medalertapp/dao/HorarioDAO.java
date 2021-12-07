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

    @Query("SELECT * FROM horarios where medicamentoId = :medicamentoId and status = 'P' order by data, hora")
    public abstract LiveData<List<Horario>> listarPorMedicamentoId(String medicamentoId);

    @Query("SELECT * FROM horarios where data = :data and status = 'P' and (hora between :horaInicial and :horaFinal) order by data, hora")
    public abstract LiveData<List<Horario>> listarPorDataHoraPendente(String data, String horaInicial, String horaFinal);

    @Query("DELETE FROM horarios")
    public abstract void excluirTodos();
}