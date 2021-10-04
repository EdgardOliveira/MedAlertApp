package br.com.technologies.venom.medalertapp.ui.cartao;

import androidx.lifecycle.ViewModelProvider;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.technologies.venom.medalertapp.R;
import br.com.technologies.venom.medalertapp.databinding.FragmentCartaoBinding;
import br.com.technologies.venom.medalertapp.databinding.FragmentHomeBinding;

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
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Re-configura o layout para ficar de acordo com o sensor de orientaçãp
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }
}