package br.com.technologies.venom.medalertapp.ui.receita;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.technologies.venom.medalertapp.AppRepository;
import br.com.technologies.venom.medalertapp.models.Receita;

public class ReceitasViewModel extends AndroidViewModel {
    private AppRepository appRepository;

    public ReceitasViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
    }

    public LiveData<List<Receita>> recuperarReceitas(String codigo) {
        //Dispara uma requisição de dados pra API
        obterReceitas(codigo);

        //Carrega os dados do banco de dados
        return recuperarReceitasBD();
    }

    private void obterReceitas(String codigo){
        appRepository.consultarReceitasAPI(codigo);
    }

    public LiveData<List<Receita>> recuperarReceitasBD(){
        return appRepository.listarReceitas();
    }
}