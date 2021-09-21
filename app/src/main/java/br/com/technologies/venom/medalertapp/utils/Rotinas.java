package br.com.technologies.venom.medalertapp.utils;

import android.os.Bundle;
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
            Log.e(TAG, "trocarTela: Ocorreu um erro ao tentar trocar para a tela", e);
        }
    }

    public static void trocarTelaComExtra(Fragment fragment, int tela, Bundle bundle) {
        try{
            NavHostFragment.findNavController(fragment).navigate(tela, bundle);
        }catch (Exception e){
            Log.e(TAG, "trocarTelaComExtra: Ocorreu um erro ao tentar trocar para a tela com extras", e);
        }
    }
}
