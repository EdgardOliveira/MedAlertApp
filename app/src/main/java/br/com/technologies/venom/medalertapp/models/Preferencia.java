package br.com.technologies.venom.medalertapp.models;

import static br.com.technologies.venom.medalertapp.utils.Constantes.TAG;
import static br.com.technologies.venom.medalertapp.utils.Globais.token;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class Preferencia {
    private Context context;
    private SharedPreferences sharedPreferences;
    private static String ARQUIVO_PREFERENCIA = "MedAlert";
    private static Usuario usuario;
    public static Preferencia preferencia = null;

    public String getEmail() {
        return usuario.getEmail();
    }

    public void setEmail(String email) {
        usuario.setEmail(email);
    }

    public String getSenha() {
        return usuario.getSenha();
    }

    public void setSenha(String senha) {
        usuario.setSenha(senha);
    }

    public Context getContext() {
        return context;
    }

    public Preferencia(Context context) {
        this.context = context;
    }

    public Preferencia(Context context, Usuario usuario){
        this.context = context;
        this.usuario = usuario;
    }

    /**
     * Responsável por recuperar as preferências salvas
     * @param context
     * @return
     */
    public static Preferencia recuperar(Context context){
        //Definindo o arquivo de preferências e o modo de acesso dos outros apps
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        Log.d(TAG, "recuperar: Tentando abrir o arquivo de preferências...");
        if (preferencia == null) {
            try {
                Log.d(TAG, "recuperar: Recuperando as preferências do arquivo...");
                usuario = new Usuario(
                        sharedPreferences.getString("nome", null),
                        sharedPreferences.getString("email", null),
                        sharedPreferences.getString("senha", null),
                        sharedPreferences.getString("token", null)
                );
                token = usuario.getToken();
                preferencia = new Preferencia(context, usuario);
                Log.d(TAG, "recuperar: As preferências foram recuperadas com sucesso...");
            } catch (Exception e) {
                Log.d(TAG, "recuperar: Erro ao tentar recuperar as configurações do arquivo de preferências: " + e.toString());
            }
        }
        return preferencia;
    }

    /**
     * Salva as preferências
     */
    public void salvar(Usuario usuario){
        //Definindo o arquivo de preferências e o modo de acesso dos outros apps
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        //Editando o arquivo de preferências
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //Gravando os dados de preferências
        editor.putString("nome",  usuario.getNome());
        editor.putString("email", usuario.getEmail());
        editor.putString("senha", usuario.getSenha());
        editor.putString("token", usuario.getToken());
        token = usuario.getToken();

        //Salvando as modificações no arquivo ocupando a thread princial
        editor.commit();

        Log.d(TAG, "salvar: As preferências foram salvas!");
    }

    /**
     * Ao fazer o logout, limpa as preferências
     */
    public void limpar(){
        //Definindo o arquivo de preferências e o modo de acesso dos outros apps
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        //Editando o arquivo de preferências
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //Removendo os dados de preferências
        editor.remove("nome");
        editor.remove("email");
        editor.remove("senha");
        editor.remove("token");
        token = null;

        //Salvando as modificações no arquivo
        editor.commit();

        preferencia = null;
        Log.d(TAG, "limpar: As preferências foram removidas");
    }
}
