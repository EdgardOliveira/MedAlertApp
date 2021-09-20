package br.com.technologies.venom.medalertapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import br.com.technologies.venom.medalertapp.models.Receita;

@Dao
public abstract class ReceitaDAO implements DAOGenerico<Receita> {
    @Query("SELECT * FROM receitas")
    public abstract LiveData<List<Receita>> listar();

    @Query("DELETE FROM receitas")
    public abstract void excluirTodos();
}