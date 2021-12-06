package br.com.technologies.venom.medalertapp.ui.home;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import br.com.technologies.venom.medalertapp.R;
import br.com.technologies.venom.medalertapp.databinding.FragmentHomeBinding;
import br.com.technologies.venom.medalertapp.models.Paciente;
import br.com.technologies.venom.medalertapp.models.Usuario;

import static br.com.technologies.venom.medalertapp.utils.Rotinas.trocarTela;

public class HomeFragment extends Fragment implements View.OnClickListener{

    private HomeViewModel homeViewModel;
    private View view;
    private FragmentHomeBinding binding;
    private CardView cvReceitas, cvCartao, cvDispositivo, cvSair;
    private TextView tvNomePaciente;
    private Paciente paciente;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        cvReceitas = binding.cvReceitas;
        cvCartao = binding.cvCartao;
        cvDispositivo = binding.cvDispenser;
        cvSair = binding.cvSair;
        tvNomePaciente = binding.tvNomePaciente;

        cvReceitas.setOnClickListener(this);
        cvCartao.setOnClickListener(this);
        cvSair.setOnClickListener(this);

        return view;
    }

    private void atualizarDadosTela(){
        String nome[] = paciente.getUsuario().getNome().split("");

        if (!nome[0].isEmpty())
            tvNomePaciente.setText(nome[0]);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cvReceitas:
                trocarTela(getParentFragment(), R.id.action_nav_home_to_nav_receitas);
                break;
            case R.id.cvCartao:
                trocarTela(getParentFragment(), R.id.action_nav_home_to_nav_cartao);
                break;
            case R.id.cvSair:
                homeViewModel.limparDados();
                trocarTela(getParentFragment(), R.id.action_nav_home_to_nav_login);
        }
    }
}