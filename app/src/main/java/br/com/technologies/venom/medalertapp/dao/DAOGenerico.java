package br.com.technologies.venom.medalertapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DAOGenerico<T> {
    /**
     * Função responsável por inserir um objeto na base de dados
     *
     * @param obj o objeto que será inserido
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long inserir(T obj);

    /**
     * Função responsável por inserir um array de objetos na base de dados
     *
     * @param obj o objeto a ser inserido na base de dados
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void inserir(List<T> obj);

    /**
     * Função responsável por atualizar um objeto na base de dados
     *
     * @param obj o objeto a ser atualizado
     */
    @Update
    void atualizar(T obj);

    /**
     * Função responsável por atualizar um array de objetos na base de dados
     *
     * @param obj o objeto a ser atualizado
     */
    @Update
    void atualizar(T... obj);

    /**
     * Função reponsável por excluir um objeto da base de dados
     *
     * @param obj objeto a ser ser excluído da base de dados
     */
    @Delete
    void excluir(T obj);
}
