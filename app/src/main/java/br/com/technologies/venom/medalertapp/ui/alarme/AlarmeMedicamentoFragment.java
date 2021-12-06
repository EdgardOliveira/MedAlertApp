package br.com.technologies.venom.medalertapp.ui.alarme;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.technologies.venom.medalertapp.R;

public class AlarmeMedicamentoFragment extends Fragment {

    private AlarmeMedicamentoViewModel mViewModel;

    public static AlarmeMedicamentoFragment newInstance() {
        return new AlarmeMedicamentoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_alarme_medicamento, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AlarmeMedicamentoViewModel.class);
        // TODO: Use the ViewModel
    }

}