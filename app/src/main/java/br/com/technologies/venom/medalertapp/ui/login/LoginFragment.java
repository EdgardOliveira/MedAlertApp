package br.com.technologies.venom.medalertapp.ui.login;

import static br.com.technologies.venom.medalertapp.utils.Constantes.TAG;
import static br.com.technologies.venom.medalertapp.utils.Rotinas.trocarTela;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.royrodriguez.transitionbutton.TransitionButton;

import br.com.technologies.venom.medalertapp.MainActivity;
import br.com.technologies.venom.medalertapp.R;
import br.com.technologies.venom.medalertapp.databinding.FragmentLoginBinding;
import br.com.technologies.venom.medalertapp.models.Medicamento;
import br.com.technologies.venom.medalertapp.models.Preferencia;
import br.com.technologies.venom.medalertapp.models.Usuario;
import br.com.technologies.venom.medalertapp.models.UsuarioResp;

public class LoginFragment extends Fragment implements View.OnClickListener{

    private LoginViewModel loginViewModel;
    private View view;
    private FragmentLoginBinding binding;
    private Usuario usuario = new Usuario();
    private TextInputLayout layEmail, laySenha;
    private TextInputEditText etEmail, etSenha;
    private TransitionButton btnVerificar;
    private Preferencia preferencias;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);

        layEmail = binding.layLoginEmail;
        laySenha = binding.layLoginSenha;
        etEmail = binding.etLoginEmail;
        etSenha = binding.etLoginSenha;
        btnVerificar = binding.btnVerificar;

        verificarPreferencias();

        btnVerificar.setOnClickListener(this);

        return view;
    }

    private void verificarPreferencias() {
        try{
            preferencias = Preferencia.recuperar(getContext());
            if (preferencias.getEmail() != null && preferencias.getSenha() != null){
                etEmail.setText(preferencias.getEmail());
                etSenha.setText(preferencias.getSenha());
                fazerLogin();
            }
        }catch (Exception e){
            Log.d(TAG, "verificarPreferencias: Ocorreu um erro ao tentar recuperar as preferências.\nErro:" + e.getMessage());
        }
    }

    private boolean validarDados() {
        boolean status = true;
        usuario = new Usuario(
                etEmail.getText().toString().trim(),
                etSenha.getText().toString().trim()
        );

        if (usuario.getEmail().isEmpty()) {
            status = false;
            layEmail.setError("Você precisa fornecer um e-mail válido.");
        } else {
            layEmail.setError(null);
        }

        if (usuario.getSenha().isEmpty()) {
            status = false;
            laySenha.setError("Você precisa fornecer uma senha válida.");
        } else {
            laySenha.setError(null);
        }
        return status;
    }

    private void verificarCredencias() {
        loginViewModel.verificarCredenciais(usuario).observe(getViewLifecycleOwner(), new Observer<UsuarioResp>() {

            @Override
            public void onChanged(UsuarioResp usuarioResp) {
                if (usuarioResp.isSucesso()) {
                    usuario.setToken(usuarioResp.getToken());
                    preferencias.salvar(usuario);
                    Toast.makeText(getContext(), "Usuário autenticado com sucesso!", Toast.LENGTH_SHORT).show();
                    btnVerificar.stopAnimation(TransitionButton.StopAnimationStyle.EXPAND, new TransitionButton.OnAnimationStopEndListener() {
                        @Override
                        public void onAnimationStopEnd() {
                            trocarTela(getParentFragment(), R.id.action_nav_login_to_nav_home);
                        }
                    });
                } else{
                    btnVerificar.stopAnimation(TransitionButton.StopAnimationStyle.SHAKE, null);
                    Toast.makeText(getContext(), usuarioResp.getMensagem(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void fazerLogin() {
        btnVerificar.startAnimation();
        if (validarDados()) {
            verificarCredencias();
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnVerificar:
                fazerLogin();
                break;
        }
    }
}