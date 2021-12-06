package br.com.technologies.venom.medalertapp.ui.medicamento;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.technologies.venom.medalertapp.AppRepository;
import br.com.technologies.venom.medalertapp.models.Horario;
import br.com.technologies.venom.medalertapp.models.Medicamento;
import br.com.technologies.venom.medalertapp.models.MedicamentoDetalheResp;

public class MedicamentosViewModel extends AndroidViewModel {
    private AppRepository appRepository;

    public MedicamentosViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
    }

    public LiveData<List<Medicamento>> recuperarMedicamentos(String receitaId) {
        //Carrega os dados do banco de dados
        return appRepository.listarMedicamentosPorReceitaId(receitaId);
    }

    public LiveData<MedicamentoDetalheResp> consultarMedicamentoPorCodigo(String codigo){
        return appRepository.consultarMedicamentoAPI(codigo);
    }

    public void cadastrarHorario(Horario horario){
        appRepository.cadastrarHorario(horario);
    }

    public LiveData<List<Horario>> listarPorMedicamentoId(String medicamentoId){
        return appRepository.listarHorariosPorMedicamentoId(medicamentoId);
    }
}