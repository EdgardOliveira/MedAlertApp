package br.com.technologies.venom.medalertapp.ui.cartao;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.technologies.venom.medalertapp.AppRepository;
import br.com.technologies.venom.medalertapp.models.Paciente;

public class CartaoViewModel extends AndroidViewModel {

    private AppRepository appRepository;
    private LiveData<List<Paciente>> pacientes;

    public CartaoViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(getApplication());

        appRepository.obterConsultasAPI();
        pacientes = appRepository.listarPacientes();
    }

    public LiveData<List<Paciente>> getPaciente() {
        return pacientes;
    }
}