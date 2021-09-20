package br.com.technologies.venom.medalertapp.utils;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import static br.com.technologies.venom.medalertapp.utils.Constantes.TAG;

public class Rotinas {
    /**
     * Tracar de uma tela para outra
     * @param fragment
     * @param tela
     */
    public static void trocarTela(Fragment fragment, int tela) {
        try{
            NavHostFragment.findNavController(fragment).navigate(tela);
        }catch (Exception e){
            Log.d(TAG, "trocarTela: Ocorreu um erro ao tentar trocar para a tela");
        }
    }
}
