package br.com.technologies.venom.medalertapp.ui.splash;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

import br.com.technologies.venom.medalertapp.R;

public class SplashScreenFragment extends Fragment {

    private SplashScreenViewModel splashScreenViewModel;
    private View view;

    public static SplashScreenFragment newInstance() {
        return new SplashScreenFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        splashScreenViewModel = new ViewModelProvider(this).get(SplashScreenViewModel.class);
        view = inflater.inflate(R.layout.fragment_splash_screen, container, false);

        atrasarTrocaTela();

        return view;
    }

    private void atrasarTrocaTela() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                NavHostFragment
                        .findNavController(SplashScreenFragment.this)
                        .navigate(R.id.action_nav_splash_screen_to_nav_login, null,
                                new NavOptions.Builder()
                                        .setPopUpTo(R.id.nav_splash_screen, true)
                                        .build());
            }
        }, 1000);
    }
}