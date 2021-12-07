package br.com.technologies.venom.medalertapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import br.com.technologies.venom.medalertapp.models.Dia;
import br.com.technologies.venom.medalertapp.models.Horario;

@Dao
public abstract class HorarioMedicamentoDAO implements DAOGenerico<Dia> {
    @Query("SELECT * FROM horarios_medicamentos")
    public abstract LiveData<List<Dia>> listar();

    @Query("SELECT * FROM horarios_medicamentos where medicamentoId = :medicamentoId order by hora01, hora02, hora03, hora04, hora05, hora06")
    public abstract LiveData<List<Dia>> listarPorMedicamentoId(String medicamentoId);

    @Query("DELETE FROM horarios")
    public abstract void excluirTodos();
}