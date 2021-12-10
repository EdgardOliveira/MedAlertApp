package br.com.technologies.venom.medalertapp.ui.dispensador;

import static br.com.technologies.venom.medalertapp.utils.Constantes.TAG;
import static br.com.technologies.venom.medalertapp.utils.Rotinas.trocarTelaComExtra;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.royrodriguez.transitionbutton.TransitionButton;

import br.com.technologies.venom.medalertapp.R;
import br.com.technologies.venom.medalertapp.databinding.FragmentDispensadorBinding;
import br.com.technologies.venom.medalertapp.databinding.FragmentHomeBinding;
import br.com.technologies.venom.medalertapp.models.Gerenciamento;
import br.com.technologies.venom.medalertapp.models.GerenciamentoResp;
import br.com.technologies.venom.medalertapp.models.UsuarioResp;
import br.com.technologies.venom.medalertapp.ui.home.HomeViewModel;
import br.com.technologies.venom.medalertapp.ui.splash.SplashScreenFragment;

public class DispensadorFragment extends Fragment implements View.OnClickListener {

    private DispensadorViewModel dispensadorViewModel;
    private FragmentDispensadorBinding binding;
    private CardView cvRecipiente01, cvRecipiente02, cvRecipiente03;
    private View view;
    private ProgressBar progressBar1, progressBar2, progressBar3;
    private ImageView ivOK01, ivOK02, ivOK03;
    private int recipiente;

    public static DispensadorFragment newInstance() {
        return new DispensadorFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        dispensadorViewModel = new ViewModelProvider(this).get(DispensadorViewModel.class);

        binding = FragmentDispensadorBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        cvRecipiente01 = binding.cvRecipiente01;
        cvRecipiente02 = binding.cvRecipiente02;
        cvRecipiente03 = binding.cvRecipiente03;
        progressBar1 = binding.progressBar1;
        progressBar2 = binding.progressBar2;
        progressBar3 = binding.progressBar3;
        ivOK01 = binding.ivOK01;
        ivOK02 = binding.ivOK02;
        ivOK03 = binding.ivOK03;

        progressBar1.setVisibility(View.INVISIBLE);
        progressBar2.setVisibility(View.INVISIBLE);
        progressBar3.setVisibility(View.INVISIBLE);
        recipiente = 0;

        configurarActionBar();

        cvRecipiente01.setOnClickListener(this);
        cvRecipiente02.setOnClickListener(this);
        cvRecipiente03.setOnClickListener(this);

        return view;
    }

    private void configurarActionBar() {
        String titulo = "Dispensador";
        String subtitulo = "gerenciamento do dispositivo";
        Drawable icone = getResources().getDrawable(R.drawable.ic_menu_camera);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);              // Ligando a seta que volta para Activity pai
        actionBar.setTitle(titulo);                             // Configurar o título
        actionBar.setSubtitle(subtitulo);                       // Configurar o sub-título
        actionBar.setDisplayShowHomeEnabled(true);              // Configurar a Home
        actionBar.setLogo(icone);                               // Configurar o ícone que será exibido
        actionBar.setDisplayUseLogoEnabled(false);              // Habilitar a exibição do ícone
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cvRecipiente01:
                gerenciarMedicamento(1);
                break;
            case R.id.cvRecipiente02:
                gerenciarMedicamento(2);
                break;
            case R.id.cvRecipiente03:
                gerenciarMedicamento(3);
                break;
        }
    }

    private void gerenciarMedicamento(int recipiente){
        switch (recipiente){
            case 1:
                cvRecipiente01.setEnabled(false);
                progressBar1.setVisibility(View.VISIBLE);
                break;
            case 2:
                cvRecipiente02.setEnabled(false);
                progressBar2.setVisibility(View.VISIBLE);
                break;
            case 3:
                cvRecipiente03.setEnabled(false);
                progressBar3.setVisibility(View.VISIBLE);
                break;
        }

        Gerenciamento gerenciamento = new Gerenciamento(
                "1",
                "615cab1300355e830069bd12",
                "615cb4c400a2fbc8009da74a",
                "2021-12-09T19:30:00.000Z",
                recipiente
        );

        gerenciarMedicamentoAPI(gerenciamento);
    }

    private void gerenciarMedicamentoAPI(Gerenciamento gerenciamento) {
        dispensadorViewModel.gerenciarMedicamento(gerenciamento).observe(getViewLifecycleOwner(), new Observer<GerenciamentoResp>() {

            @Override
            public void onChanged(GerenciamentoResp gerenciamentoResp) {
                if (gerenciamentoResp.isSucesso()) {
                    switch (gerenciamentoResp.getGerenciamento().getRecipiente()){
                        case 1:
                            Log.d(TAG, "onChanged: 1");
                            cvRecipiente01.setEnabled(true);
                            exibirStatusOK(1);
                            break;
                        case 2:
                            Log.d(TAG, "onChanged: 2");
                            cvRecipiente02.setEnabled(true);
                            exibirStatusOK(2);
                            break;
                        case 3:
                            Log.d(TAG, "onChanged: 3");
                            cvRecipiente03.setEnabled(true);
                            exibirStatusOK(3);
                            break;
                    }
                }
            }
        });
    }

    private void setRecipiente(int recipiente){
        this.recipiente = recipiente;
        exibirStatusOK(recipiente);
    }
    private void ocultarProgresso(int recipiente){
        switch (recipiente){
            case 1:
                progressBar1.setVisibility(View.INVISIBLE);
                break;
            case 2:
                progressBar2.setVisibility(View.INVISIBLE);
                break;
            case 3:
                progressBar3.setVisibility(View.INVISIBLE);
                break;

        }
        progressBar1.setVisibility(View.INVISIBLE);
    }

    private void exibirStatusOK(int recipiente){
        switch (recipiente){
            case 1:
                ocultarProgresso(1);
                ivOK01.setVisibility(View.VISIBLE);
                ocultarStatusOK(1);
                break;
            case 2:
                ocultarProgresso(2);
                ivOK02.setVisibility(View.VISIBLE);
                ocultarStatusOK(2);
                break;
            case 3:
                ocultarProgresso(3);
                ivOK03.setVisibility(View.VISIBLE);
                ocultarStatusOK(3);
                break;
        }
    }

    private void ocultarStatusOK(int recipiente) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                switch (recipiente){
                    case 1:
                        ivOK01.setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        ivOK02.setVisibility(View.INVISIBLE);
                        break;
                    case 3:
                        ivOK03.setVisibility(View.INVISIBLE);
                        break;
                }
            }
        }, 1500);
    }
}