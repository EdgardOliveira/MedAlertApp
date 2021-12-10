package br.com.technologies.venom.medalertapp.ui.home;

import static br.com.technologies.venom.medalertapp.utils.Rotinas.trocarTela;

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
import br.com.technologies.venom.medalertapp.models.Usuario;

public class HomeFragment extends Fragment implements View.OnClickListener{

    private HomeViewModel homeViewModel;
    private View view;
    private FragmentHomeBinding binding;
    private CardView cvReceitas, cvCartao, cvDispositivo, cvSair;
    private TextView tvNomePaciente;
    private Usuario usuario;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        usuario = (Usuario) getArguments().getParcelable("usuario");

        cvReceitas = binding.cvReceitas;
        cvCartao = binding.cvCartao;
        cvDispositivo = binding.cvDispenser;
        cvSair = binding.cvSair;
        tvNomePaciente = binding.tvNomePaciente;

        configurarActionBar();

        atualizarDadosTela();

        cvReceitas.setOnClickListener(this);
        cvCartao.setOnClickListener(this);
        cvSair.setOnClickListener(this);
        cvDispositivo.setOnClickListener(this);

        return view;
    }

    private void configurarActionBar() {
        String titulo = "Home";
        String subtitulo = "Tela incial";
        Drawable icone = getResources().getDrawable(R.drawable.ic_menu_camera);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);              // Ligando a seta que volta para Activity pai
        actionBar.setTitle(titulo);                             // Configurar o título
        actionBar.setSubtitle(subtitulo);                       // Configurar o sub-título
        actionBar.setDisplayShowHomeEnabled(true);              // Configurar a Home
        actionBar.setLogo(icone);                               // Configurar o ícone que será exibido
        actionBar.setDisplayUseLogoEnabled(false);              // Habilitar a exibição do ícone
    }

    private void atualizarDadosTela(){
        if (usuario != null){
            String nome = pegarPrimeiroNomePaciente();
            if (nome != null )
                tvNomePaciente.setText("Olá "+ nome);
        }
    }

    private String pegarPrimeiroNomePaciente(){
        if (usuario.getNome() != null){
            //Array com nomes, separando pelo espaço
            String nome[] = usuario.getNome().split(" ");

            //A primeira posição do array vai armazenar o primeiro nome
            if (!nome[0].isEmpty())
                return nome[0];
        }

        return null;
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
            case R.id.cvDispenser:
                trocarTela(getParentFragment(), R.id.action_nav_home_to_nav_dispensador);
//                Calendar calendar = Calendar.getInstance();
//                calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE + 1));
//                configurarHoraMedicamento(calendar.get(Calendar.HOUR),calendar.get(Calendar.MINUTE), getContext());
                break;
            case R.id.cvSair:
                homeViewModel.limparDados();
//                trocarTela(getParentFragment(), R.id.action_nav_home_to_nav_login);
//                cancelarAlarme(getContext());
        }
    }
}