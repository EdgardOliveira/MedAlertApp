package br.com.technologies.venom.medalertapp;

import static br.com.technologies.venom.medalertapp.utis.Constantes.DATABASE_NAME;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.com.technologies.venom.medalertapp.dao.EmpresaDAO;
import br.com.technologies.venom.medalertapp.dao.MedicamentoDAO;
import br.com.technologies.venom.medalertapp.dao.PacienteDAO;
import br.com.technologies.venom.medalertapp.dao.ReceitaDAO;
import br.com.technologies.venom.medalertapp.models.Empresa;
import br.com.technologies.venom.medalertapp.models.Medicamento;
import br.com.technologies.venom.medalertapp.models.Paciente;
import br.com.technologies.venom.medalertapp.models.Receita;

@Database(
        entities = {
                Empresa.class,
                Paciente.class,
                Receita.class,
                Medicamento.class
        },
        version = 1,
        exportSchema = false
)

//@TypeConverters(Conversor.class)
public abstract class AppDatabase extends RoomDatabase {

    // marcando a instância como volátil para garantir o acesso atômico à variável
    private static volatile AppDatabase INSTANCE;
    private static final int NUMERO_DE_THREADS = 4;
    public static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(NUMERO_DE_THREADS);

    public abstract EmpresaDAO getEmpresaDAO();
    public abstract PacienteDAO getPacienteDAO();
    public abstract ReceitaDAO getReceitaDAO();
    public abstract MedicamentoDAO getMedicamentoDAO();

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DATABASE_NAME)
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback callback = new Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            EXECUTOR_SERVICE.execute(() -> {
                //Vai executar isso toda vez que abrir o banco de dados
            });
        }


        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            EXECUTOR_SERVICE.execute(() -> {
                //Vai executar isso quando o banco de dados for criado pela primeira vez
                popularBD(INSTANCE);
            });
        }
    };

    private static void popularBD(AppDatabase instance) {
        instance.runInTransaction(() -> {

        });
    }
}
