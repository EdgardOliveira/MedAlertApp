package br.com.technologies.venom.medalertapp.ui.receita;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.technologies.venom.medalertapp.AppRepository;
import br.com.technologies.venom.medalertapp.MedAlertApplication;
import br.com.technologies.venom.medalertapp.models.Receita;

public class ReceitasViewModel extends AndroidViewModel {
    private AppRepository appRepository;

    public ReceitasViewModel(@NonNull Application application) {
        super(application);
        appRepository = MedAlertApplication.getAppRepository(getApplication());
    }

    public LiveData<List<Receita>> recuperarReceitas() {
        //Dispara uma requisição de dados pra API
        obterReceitas();

        //Carrega os dados do banco de dados
        return recuperarReceitasBD();
    }

    private void obterReceitas(){
        appRepository.obterConsultasAPI();
    }

    public LiveData<List<Receita>> recuperarReceitasBD(){
        return appRepository.listarReceitas();
    }
}