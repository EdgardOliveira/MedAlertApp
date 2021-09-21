package br.com.technologies.venom.medalertapp.ui.medicamento;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.technologies.venom.medalertapp.AppRepository;
import br.com.technologies.venom.medalertapp.models.Medicamento;

public class MedicamentosViewModel extends AndroidViewModel {
    private AppRepository appRepository;

    public MedicamentosViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
    }

    public LiveData<List<Medicamento>> recuperarMedicamentos(Long codigo) {
        //Carrega os dados do banco de dados
        return appRepository.listarMedicamentosPorReceitaId(codigo);
    }
}