package br.com.technologies.venom.medalertapp.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import br.com.technologies.venom.medalertapp.AppRepository;
import br.com.technologies.venom.medalertapp.models.Preferencia;

public class HomeViewModel extends AndroidViewModel {

    private AppRepository appRepository;

    private Preferencia preferencias;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
        preferencias = Preferencia.recuperar(application);
    }

    public void limparDados() {
        appRepository.excluirPaciente();
        preferencias.limpar();
    }
}