package br.com.technologies.venom.medalertapp.ui.dispensador;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import br.com.technologies.venom.medalertapp.AppRepository;
import br.com.technologies.venom.medalertapp.models.Gerenciamento;
import br.com.technologies.venom.medalertapp.models.GerenciamentoResp;

public class DispensadorViewModel extends AndroidViewModel {
    private AppRepository appRepository;


    public DispensadorViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
    }

    public LiveData<GerenciamentoResp> gerenciarMedicamento(Gerenciamento gerenciamento){
        return appRepository.cadastrarGerenciamentoAPI(gerenciamento);
    }
}