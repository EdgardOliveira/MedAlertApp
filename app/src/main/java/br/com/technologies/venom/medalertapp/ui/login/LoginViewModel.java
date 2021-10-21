package br.com.technologies.venom.medalertapp.ui.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import br.com.technologies.venom.medalertapp.AppRepository;
import br.com.technologies.venom.medalertapp.MedAlertApplication;
import br.com.technologies.venom.medalertapp.models.Usuario;
import br.com.technologies.venom.medalertapp.models.UsuarioResp;

public class LoginViewModel extends AndroidViewModel {
    private AppRepository appRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.appRepository = MedAlertApplication.getAppRepository(getApplication());
    }

    public LiveData<UsuarioResp> verificarCredenciais(Usuario usuario){
        return appRepository.fazerLogin(usuario);
    }
}