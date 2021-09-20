package br.com.technologies.venom.medalertapp.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import br.com.technologies.venom.medalertapp.AppRepository;

public class HomeViewModel extends AndroidViewModel {

    private AppRepository appRepository;
    private MutableLiveData<String> mText;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
    }

    public void consultarReceita(){
        appRepository.consultarReceitas("123");
    }
}