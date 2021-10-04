package br.com.technologies.venom.medalertapp;

import static android.content.ContentValues.TAG;
import static br.com.technologies.venom.medalertapp.utils.Constantes.API_BASE_URL;

import android.app.Application;
import android.util.Log;

import androidx.constraintlayout.widget.Constraints;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import br.com.technologies.venom.medalertapp.dao.MedicamentoDAO;
import br.com.technologies.venom.medalertapp.dao.PacienteDAO;
import br.com.technologies.venom.medalertapp.dao.ReceitaDAO;
import br.com.technologies.venom.medalertapp.models.Consulta;
import br.com.technologies.venom.medalertapp.models.ConsultasResp;
import br.com.technologies.venom.medalertapp.models.Medicamento;
import br.com.technologies.venom.medalertapp.models.MedicamentoDetalheResp;
import br.com.technologies.venom.medalertapp.models.Paciente;
import br.com.technologies.venom.medalertapp.models.Receita;
import br.com.technologies.venom.medalertapp.models.Usuario;
import br.com.technologies.venom.medalertapp.models.UsuarioResp;
import br.com.technologies.venom.medalertapp.services.RESTService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

import static br.com.technologies.venom.medalertapp.utils.Globais.token;

public class AppRepository {
    private RESTService restService;
    private AppDatabase appDatabase;
    private PacienteDAO pacienteDAO;
    private MedicamentoDAO medicamentoDAO;
    private ReceitaDAO receitaDAO;
    private MutableLiveData<UsuarioResp> usuarioAutenticado = new MutableLiveData<>();
    private MutableLiveData<MedicamentoDetalheResp> detalhesMedicamento = new MutableLiveData<>();

    //construtor
    public AppRepository(Application application) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        restService = new retrofit2.Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RESTService.class);
        appDatabase = AppDatabase.getDatabase(application.getApplicationContext());
        pacienteDAO = appDatabase.getPacienteDAO();
        medicamentoDAO = appDatabase.getMedicamentoDAO();
        receitaDAO = appDatabase.getReceitaDAO();
    }

    /**
     * Objetivo: Função responsável por verificar as credenciais informadas pelo usuário e se tudo
     *           estiver correto, devolve o token de acesso
     * @param usuario
     * @return
     */
    public LiveData<UsuarioResp> fazerLogin(Usuario usuario) {
        Log.d(Constraints.TAG, "fazerLogin: consultando as credenciais enviadas...");
        Call<UsuarioResp> call = restService.fazerLogin(usuario);

        call.enqueue(new Callback<UsuarioResp>() {
            UsuarioResp usuarioAut = new UsuarioResp();

            @Override
            public void onResponse(Call<UsuarioResp> call, Response<UsuarioResp> response) {

                switch (response.code()) {
                    case 200: //Código 200 - Sucesso
                        Log.d(Constraints.TAG, "onResponse: convertendo a resposta em uma lista de objetos...");
                        Log.d(Constraints.TAG, "onResponse: Usuário autenticado com sucesso!");
                        usuarioAut = response.body();
                        break;
                    case 401: //Código 401 - Falhou na autenticação
                        Log.d(Constraints.TAG, "onResponse: Credenciais inválidas!");
                        usuarioAut.setMensagem("Credenciais inválidas");
                        break;
                    case 503: //Código 503 - Serviço indisponível
                        Log.d(Constraints.TAG, "onResponse: Servidor/Serviço da API Indisponível no momento!");
                        usuarioAut.setMensagem("Servidor/Serviço indisponível");
                        break;
                }
                usuarioAutenticado.postValue(usuarioAut);
            }

            @Override
            public void onFailure(Call<UsuarioResp> call, Throwable t) {
                Log.d(Constraints.TAG, "onFailure: Ocorreu um erro ao tentar requisitar dados de atualizações:" + t.getMessage() +
                        "\nRequisição: " + call.request().toString() +
                        "\nCausa: " + t.getCause() +
                        "\nErro: " + t.getMessage()
                );

                switch (t.getMessage()) {
                    case "timeout":
                        Log.d(Constraints.TAG, "onResponse: O tempo expirou e não houve resposta do servidor!");
                        usuarioAut.setMensagem("O tempo expirou e o servidor não respondeu");
                        break;
                    default:
                        Log.d(Constraints.TAG, "onResponse: Ocorreu um erro ao tentar fazer a requisição de fazer login");
                        usuarioAut.setMensagem(t.getLocalizedMessage());
                }
                usuarioAutenticado.postValue(usuarioAut);
            }
        });

        return usuarioAutenticado;
    }

    /**
     * Objetivo: Função responsável por obter os dados de consultas médicas (Receitas e Medicamentos)
     */
    public void obterConsultasAPI(){
        Call<ConsultasResp> call = restService.obterConsultas(token);

        call.enqueue(new Callback<ConsultasResp>() {
            @Override
            public void onResponse(Call<ConsultasResp> call, Response<ConsultasResp> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: A API enviou uma resposta...");
                    try {
                        List<Consulta> consultaList = response.body().getConsultas();
                        for (Consulta consulta : consultaList) {
                            Paciente paciente = consulta.getPaciente();
                            cadastrarPaciente(paciente);

                            Receita receita = consulta.getReceita();
                            cadastrarReceita(receita);
                            for (Medicamento medicamento : receita.getMedicamentos()) {
                                cadastrarMedicamento(medicamento);
                            }
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "onResponse: Ocorreu um erro ao tentar salvar os dados", e);
                    }
                } else {
                    Log.d(TAG, "onResponse: Ocorreu um erro ao tentar ler a resposta.\nErro:" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ConsultasResp> call, Throwable t) {
                Log.e(TAG, "onFailure: Ocorreu um erro ao requisitar os dados de consultas de paciente", t);
            }
        });
    }

    /**
     * Objetivo: Função responsável por enviar o codigo de barras a API Cosmos e devolver os dados sobre
     *           o medicamento associado (Fabricante, foto, preços (minimo, médio, máximo)
     * @param gtin
     * @return
     */
    public LiveData<MedicamentoDetalheResp> consultarMedicamentoAPI(String gtin) {
        Call<MedicamentoDetalheResp> call = restService.consultarMedicamento(token, gtin);

        call.enqueue(new Callback<MedicamentoDetalheResp>() {
            MedicamentoDetalheResp medicamentoDetalheResp = new MedicamentoDetalheResp();

            @Override
            public void onResponse(Call<MedicamentoDetalheResp> call, Response<MedicamentoDetalheResp> response) {

                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: Resposta recebida com sucesso!");
                    try {
                        medicamentoDetalheResp = response.body();
                        detalhesMedicamento.postValue(medicamentoDetalheResp);
                    } catch (Exception e) {
                        Log.e(TAG, "onResponse: Ocorreu um erro ao tentar salvar os dados", e);
                    }
                } else {
                    Log.d(TAG, "onResponse: Ocorreu um erro ao tentar ler a resposta.\nErro:" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<MedicamentoDetalheResp> call, Throwable t) {
                Log.e(TAG, "onFailure: Ocorreu um erro ao requisitar os dados da consulta", t);
                detalhesMedicamento.postValue(medicamentoDetalheResp);
            }
        });

        return detalhesMedicamento;
    }


    //CRUD - CREATE
    private void cadastrarPaciente(Paciente paciente) {
        Log.d(TAG, "cadastrarPaciente: Cadastrando paciente no banco de dados..");
        AppDatabase.EXECUTOR_SERVICE.execute(() -> {
            pacienteDAO.inserir(paciente);
        });
    }

    private void cadastrarReceita(Receita receita) {
        Log.d(TAG, "cadastrarReceita: Cadastrando receita no banco de dados...");
        AppDatabase.EXECUTOR_SERVICE.execute(() -> {
            receitaDAO.inserir(receita);
        });
    }

    private void cadastrarMedicamento(Medicamento medicamento) {
        Log.d(TAG, "cadastrarMedicamento: Cadastrando medicamento no banco de dados...");
        AppDatabase.EXECUTOR_SERVICE.execute(() -> {
            medicamentoDAO.inserir(medicamento);
        });
    }


    //CRUD - READ
    public LiveData<List<Paciente>> listarPacientes() {
        Log.d(TAG, "listarPacientes: Listando pacientes cadastrados no banco de dados...");
        LiveData<List<Paciente>> listaRecuperada = null;
        Future<LiveData<List<Paciente>>> futureList = AppDatabase.EXECUTOR_SERVICE.submit(new Callable() {
            @Override
            public LiveData<List<Paciente>> call() {
                return pacienteDAO.listar();
            }
        });

        try {
            listaRecuperada = futureList.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return listaRecuperada;
    }

    public LiveData<List<Medicamento>> listarMedicamentos() {
        Log.d(TAG, "listarMedicamentos: Listando medicamentos cadastrados no banco de dados...");
        LiveData<List<Medicamento>> listaRecuperada = null;
        Future<LiveData<List<Medicamento>>> futureList = AppDatabase.EXECUTOR_SERVICE.submit(new Callable() {
            @Override
            public LiveData<List<Medicamento>> call() {
                return medicamentoDAO.listar();
            }
        });

        try {
            listaRecuperada = futureList.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return listaRecuperada;
    }

    public LiveData<List<Medicamento>> listarMedicamentosPorReceitaId(String receitaId) {
        Log.d(TAG, "listarMedicamentosPorReceitaId: Listando medicamentos cadastrados no banco de dados...");
        LiveData<List<Medicamento>> listaRecuperada = null;
        Future<LiveData<List<Medicamento>>> futureList = AppDatabase.EXECUTOR_SERVICE.submit(new Callable() {
            @Override
            public LiveData<List<Medicamento>> call() {
                return medicamentoDAO.listarPorReceitaId(receitaId);
            }
        });

        try {
            listaRecuperada = futureList.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return listaRecuperada;
    }

    public LiveData<List<Receita>> listarReceitas() {
        Log.d(TAG, "listarReceitas: Listando receitas cadastradas no banco de dados...");
        LiveData<List<Receita>> listaRecuperada = null;
        Future<LiveData<List<Receita>>> futureList = AppDatabase.EXECUTOR_SERVICE.submit(new Callable() {
            @Override
            public LiveData<List<Receita>> call() {
                return receitaDAO.listar();
            }
        });

        try {
            listaRecuperada = futureList.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return listaRecuperada;
    }

    //CRUD - UPDATE


    //CRUD - DELETE
    private void excluirPaciente() {
        Log.d(TAG, "excluirPaciente: Excluindo os pacientes cadastrados no banco de dados...");
        AppDatabase.EXECUTOR_SERVICE.execute(() -> {
            pacienteDAO.excluirTodos();
        });
    }
    private void excluirMedicamento() {
        Log.d(TAG, "excluirMedicamento: Excluindo os medicamentos cadastrados no banco de dados...");
        AppDatabase.EXECUTOR_SERVICE.execute(() -> {
            medicamentoDAO.excluirTodos();
        });
    }
}