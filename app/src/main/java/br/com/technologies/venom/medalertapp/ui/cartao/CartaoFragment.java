package br.com.technologies.venom.medalertapp.ui.cartao;

import androidx.lifecycle.ViewModelProvider;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import br.com.technologies.venom.medalertapp.databinding.FragmentCartaoBinding;
import br.com.technologies.venom.medalertapp.models.Paciente;

public class CartaoFragment extends Fragment {

    private CartaoViewModel cartaoViewModel;
    private View view;
    private FragmentCartaoBinding binding;
    public static CartaoFragment newInstance() {
        return new CartaoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        cartaoViewModel = new ViewModelProvider(this).get(CartaoViewModel.class);
        binding = FragmentCartaoBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        //Configura o layout para ficar em modo horizontal
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        cartaoViewModel.getPaciente().observe(this, pacientes -> {

            // somente pacientes com convênio
            List<Paciente> pacientesValidos = pacientes.stream().filter(pac -> pac.getConvenio() != null)
                    .collect(Collectors.toList());

            // exibe o primeiro paciente da lista
            if (!pacientesValidos.isEmpty()) {
                Paciente paciente = pacientesValidos.get(0);
                if (paciente.getUsuario() != null) {
                    if (paciente.getUsuario().getNome() != null) {
                        binding.tvNomePaciente.setText(paciente.getUsuario().getNome().toUpperCase());
                    }
                }
                if (paciente.getDataNascimento() != null) {
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    String formattedDate = dateFormat.format(paciente.getDataNascimento());
                    binding.tvDataNascimento.setText(formattedDate);
                }
                if (paciente.getConvenio() != null) {
                    binding.tvProduto.setText(paciente.getConvenio().getProduto());
                    binding.tvCodigoIdentificacao.setText(paciente.getConvenio().getCodigoIdentificacao());
                    binding.tvPlano.setText(paciente.getConvenio().getPlano());
                    binding.tvAcomodacao.setText(paciente.getConvenio().getAcomodacao());
                    binding.tvCns.setText(paciente.getConvenio().getCns());
                    binding.tvCobertura.setText(paciente.getConvenio().getCobertura());
                    binding.tvEmpresa.setText(paciente.getConvenio().getEmpresa());
                }
            } else {
                Log.d("CartaoFragment", "Sem pacientes válidos cadastrados!");
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Re-configura o layout para ficar de acordo com o sensor de orientaçãp
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }
}