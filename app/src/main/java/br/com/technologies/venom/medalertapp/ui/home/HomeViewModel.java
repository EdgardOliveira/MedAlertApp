package br.com.technologies.venom.medalertapp.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import br.com.technologies.venom.medalertapp.AppRepository;

public class HomeViewModel extends AndroidViewModel {

    private AppRepository appRepository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
    }
}