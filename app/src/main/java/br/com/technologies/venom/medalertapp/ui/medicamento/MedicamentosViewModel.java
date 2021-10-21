package br.com.technologies.venom.medalertapp.ui.medicamento;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.technologies.venom.medalertapp.AppRepository;
import br.com.technologies.venom.medalertapp.MedAlertApplication;
import br.com.technologies.venom.medalertapp.models.Medicamento;
import br.com.technologies.venom.medalertapp.models.MedicamentoDetalheResp;

public class MedicamentosViewModel extends AndroidViewModel {
    private AppRepository appRepository;

    public MedicamentosViewModel(@NonNull Application application) {
        super(application);
        appRepository = MedAlertApplication.getAppRepository(getApplication());
    }

    public LiveData<List<Medicamento>> recuperarMedicamentos(String receitaId) {
        //Carrega os dados do banco de dados
        return appRepository.listarMedicamentosPorReceitaId(receitaId);
    }

    public LiveData<MedicamentoDetalheResp> consultarMedicamentoPorCodigo(String codigo){
        return appRepository.consultarMedicamentoAPI(codigo);
    }
}