package br.com.technologies.venom.medalertapp;

import static android.content.ContentValues.TAG;
import static br.com.technologies.venom.medalertapp.utis.Constantes.API_BASE_URL;

import android.app.Application;
import android.util.Log;

import androidx.constraintlayout.widget.Constraints;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import br.com.technologies.venom.medalertapp.dao.EmpresaDAO;
import br.com.technologies.venom.medalertapp.dao.MedicamentoDAO;
import br.com.technologies.venom.medalertapp.dao.PacienteDAO;
import br.com.technologies.venom.medalertapp.dao.ReceitaDAO;
import br.com.technologies.venom.medalertapp.models.Empresa;
import br.com.technologies.venom.medalertapp.models.Medicamento;
import br.com.technologies.venom.medalertapp.models.Medico;
import br.com.technologies.venom.medalertapp.models.Paciente;
import br.com.technologies.venom.medalertapp.models.Receita;
import br.com.technologies.venom.medalertapp.models.ReceitaWrapper;
import br.com.technologies.venom.medalertapp.services.RESTService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppRepository {
    private RESTService restService;
    private AppDatabase appDatabase;
    private EmpresaDAO empresaDAO;
    private PacienteDAO pacienteDAO;
    private MedicamentoDAO medicamentoDAO;
    private ReceitaDAO receitaDAO;

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
        empresaDAO = appDatabase.getEmpresaDAO();
        pacienteDAO = appDatabase.getPacienteDAO();
        medicamentoDAO = appDatabase.getMedicamentoDAO();
        receitaDAO = appDatabase.getReceitaDAO();
    }

    public void consultarReceitas(String codigo) {
        Call<ReceitaWrapper> call = restService.consultarReceita(codigo);

        call.enqueue(new Callback<ReceitaWrapper>() {
            @Override
            public void onResponse(Call<ReceitaWrapper> call, Response<ReceitaWrapper> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: Resposta recebida com sucesso!");
                    try {
                        Empresa empresa = response.body().getReceita().getEmpresa();
                        cadastrarEmpresa(empresa);
                        Paciente paciente = response.body().getReceita().getConsulta().getPaciente();
                        cadastrarPaciente(paciente);
                        Receita receita = response.body().getReceita().getConsulta().getReceita();
                        cadastrarReceita(receita);
                        List<Medicamento> medicamentoList = response.body().getReceita().getConsulta().getReceita().getMedicamentos();
                        for (Medicamento medicamento : medicamentoList) {
                            cadastrarMedicamento(medicamento);
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "onResponse: Ocorreu um erro", e);
                    }
                } else {
                    Log.d(TAG, "onResponse: Ocorreu um erro ao ter uma resposta. Erro:" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ReceitaWrapper> call, Throwable t) {
                Log.d(TAG, "onFailure: Ocorreu um erro ao requisitar a receita:" +
                        t.getMessage() + "\nRequisição: " + call.request().toString());
            }
        });
    }


    //CRUD - CREATE
    private void cadastrarEmpresa(Empresa empresa) {
        Log.d(TAG, "cadastrarEmpresa: Cadastrando empresa no banco de dados...");
        AppDatabase.EXECUTOR_SERVICE.execute(() -> {
            empresaDAO.inserir(empresa);
        });
    }

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
    public LiveData<List<Empresa>> listarEmpresas() {
        Log.d(TAG, "listarEmpresas: Listando as empresas cadastradas no banco de dados...");
        LiveData<List<Empresa>> listaRecuperada = null;
        Future<LiveData<List<Empresa>>> futureList = AppDatabase.EXECUTOR_SERVICE.submit(new Callable() {
            @Override
            public LiveData<List<Empresa>> call() {
                return empresaDAO.listar();
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
    private void excluirEmpresa() {
        Log.d(TAG, "excluirEmpresa: Excluindo as empresas cadastradas do banco de dados...");
        AppDatabase.EXECUTOR_SERVICE.execute(() -> {
            empresaDAO.excluirTodos();
        });
    }
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
    private void excluirRemedio() {
        Log.d(TAG, "excluirRemedio: Excluindo os remédios cadastrados no banco de dados...");
        AppDatabase.EXECUTOR_SERVICE.execute(() -> {
            empresaDAO.excluirTodos();
        });
    }
}
