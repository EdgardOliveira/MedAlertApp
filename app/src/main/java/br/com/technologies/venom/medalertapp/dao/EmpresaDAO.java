package br.com.technologies.venom.medalertapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import br.com.technologies.venom.medalertapp.models.Empresa;

@Dao
public abstract class EmpresaDAO implements DAOGenerico<Empresa> {
    @Query("SELECT * FROM empresas")
    public abstract LiveData<List<Empresa>> listar();

    @Query("SELECT * FROM empresas WHERE cnpj = :cnpj")
    public abstract LiveData<Empresa> listarPorCNPJ(String cnpj);

    @Query("DELETE FROM empresas")
    public abstract void excluirTodos();

}
