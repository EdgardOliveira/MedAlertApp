package br.com.technologies.venom.medalertapp.ui.medicamento;

import static br.com.technologies.venom.medalertapp.utils.Constantes.TAG;
import static br.com.technologies.venom.medalertapp.utils.Rotinas.configurarHoraMedicamento;
import static br.com.technologies.venom.medalertapp.utils.Rotinas.formatarDataHumano;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.technologies.venom.medalertapp.R;
import br.com.technologies.venom.medalertapp.adapters.HorarioAdapter;
import br.com.technologies.venom.medalertapp.databinding.FragmentMedicamentoDetalheBinding;
import br.com.technologies.venom.medalertapp.models.Dia;
import br.com.technologies.venom.medalertapp.models.Horario;
import br.com.technologies.venom.medalertapp.models.Medicamento;
import br.com.technologies.venom.medalertapp.models.MedicamentoDetalhe;
import br.com.technologies.venom.medalertapp.models.MedicamentoDetalheResp;

public class MedicamentoDetalheFragment extends Fragment implements View.OnClickListener {

    private MedicamentosViewModel medicamentosViewModel;
    private FragmentMedicamentoDetalheBinding binding;
    private ImageButton ibFoto;
    private Button btnIniciarTratamento, btnListar;
    private TextView tvNome, tvFarmaceutica, tvPrecoMin, tvPrecoMed, tvPrecoMax;
    private MedicamentoDetalhe medicamentoDetalhe;
    private ProgressBar pbStatus;
    private Medicamento medicamento;
    private TextView tvUso, tvTratamento, tvFormula, tvDosagem, tvConcentracao, tvQuantidade, tvDias, tvfrequenciaH, tvOrientacoes;
    private List<Dia> diasList = new ArrayList<>();
    private HorarioAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private View view;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        medicamentosViewModel = new ViewModelProvider(this).get(MedicamentosViewModel.class);

        binding = FragmentMedicamentoDetalheBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        medicamento = (Medicamento) getArguments().getParcelable("medicamento");
        medicamentoDetalhe = new MedicamentoDetalhe();

        ibFoto = binding.ibFoto;
        tvFarmaceutica = binding.tvFarmaceutica;
        tvNome = binding.tvNome;
        tvPrecoMin = binding.tvPrecoMin;
        tvPrecoMed = binding.tvPrecoMed;
        tvPrecoMax = binding.tvPrecoMax;
        tvUso = binding.tvUso;
        tvTratamento = binding.tvTratamento;
        tvFormula = binding.tvFormula;
        tvDosagem = binding.tvDosagem;
        tvConcentracao = binding.tvConcentracao;
        tvQuantidade = binding.tvQuantidade;
        tvDias = binding.tvDias;
        tvfrequenciaH = binding.tvFrequenciaH;
        tvOrientacoes = binding.tvOrientacoes;
        pbStatus = binding.progressBar;
        btnIniciarTratamento = binding.btnIniciarTratamento;
        btnListar = binding.btnListar;
        recyclerView = binding.rvHorarios;


        atualizarDadosTela();

        ibFoto.setOnClickListener(this);
        btnIniciarTratamento.setOnClickListener(this);
        btnListar.setOnClickListener(this);
        return view;
    }

    private void configurarRecyclerView() {
        adapter = new HorarioAdapter();
        layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        consultarHorariosMedicamentos();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibFoto:
                lerCodigoBarrasQRCode();
                break;
            case R.id.btnIniciarTratamento:
                iniciarTratamento(Calendar.getInstance());
                break;
            case R.id.btnListar:
                consultarHorariosMedicamentos();
                break;
        }
    }

    private void iniciarTratamento(Calendar dataInicial) {
        int frequenciaAoDia = (Integer) 24 / medicamento.getFrequenciaH();
        int dias = medicamento.getDias();
        Calendar dataCalculada = dataInicial;
        Log.d(TAG, "iniciarTratamento: O remédio será tomado de " + medicamento.getFrequenciaH() + "h/" + medicamento.getFrequenciaH() + "h durante " + dias + "dia(s)");
        for (int i = 0; i < dias; i++) {
            String[] horas = {"", "", "", "", "", ""};
            //Incrementa os dias
            dataCalculada.set(Calendar.MINUTE, 0);
            dataCalculada.set(Calendar.SECOND, 0);
            switch (frequenciaAoDia - 1) {
                case 0: //Tomar 1x ao dia
                    Log.d(TAG, "iniciarTratamento: Tomar 1x ao dia...");
                    //Antes de dormir as 22h?
                    horas[0] = "22:00";
                    break;
                case 1: //Tomar 2x ao dia (12h/12h)
                    //11h e 23h
                    Log.d(TAG, "iniciarTratamento: Tomar 2x ao dia...");
                    horas[0] = "11:00";
                    horas[1] = "22:00";
                    break;
                case 2: //Tomar 3x ao dia (8h/8h)
                    //7h, 15h e 23h
                    horas[0] = "07:00";
                    horas[1] = "15:00";
                    horas[2] = "23:00";
                    break;
                case 3: //Tomar 4x ao dia (6h/6h)
                    //6h, 12h, 18h e 24h
                    Log.d(TAG, "iniciarTratamento: Tomar 4x ao dia...");
                    horas[0] = "06:00";
                    horas[1] = "12:00";
                    horas[2] = "18:00";
                    horas[3] = "23:59";
                    break;
                case 5: //Tomar 6x ao dia (4h/4h)
                    //2h, 6h, 10h, 14h, 18h e 22h
                    Log.d(TAG, "iniciarTratamento: Tomar 6x ao dia...");
                    horas[0] = "02:00";
                    horas[1] = "06:00";
                    horas[2] = "10:00";
                    horas[3] = "14:00";
                    horas[4] = "18:00";
                    horas[5] = "22:00";
                    break;
            }
            cadastrarHorariosDia(medicamento.getId(), formatarDataHumano.format(dataCalculada.getTime()), horas);
            //Incrementa o dia
            dataCalculada.add(Calendar.DATE, 1);
        }
    }

    private void cadastrarHorariosDia(String medicamentoId, String data, String[] horas){
        Dia dia = new Dia(
                medicamentoId,
                data,
                horas[0],
                horas[0].equals("") ? "I" : "P",
                horas[1],
                horas[1].equals("") ? "I" : "P",
                horas[2],
                horas[2].equals("") ? "I" : "P",
                horas[3],
                horas[3].equals("") ? "I" : "P",
                horas[4],
                horas[4].equals("") ? "I" : "P",
                horas[5],
                horas[5].equals("") ? "I" : "P"
        );
        medicamentosViewModel.cadastrarHorarioMedicamento(dia);

        for (String hora: horas) {
            if (!hora.equals("")){
                cadastrarHorarios(medicamentoId, data, hora);
            }
        }
    }

    private void cadastrarHorarios(String medicamentoId, String data, String hora){
        Horario horario = new Horario(medicamentoId, data, hora, "P");
        medicamentosViewModel.cadastrarHorario(horario);
    }

    private void consultarHorariosMedicamentos() {

        medicamentosViewModel.listarHorariosPorMedicamentoId(medicamento.getId()).observe(getViewLifecycleOwner(), new Observer<List<Dia>>() {

            @Override
            public void onChanged(List<Dia> diasList) {
                if (diasList.size() > 0) {
                    btnIniciarTratamento.setVisibility(View.GONE);
                    setDiasList(diasList);
                    configurarRecyclerView();
                    getDiasList();
                }
            }
        });
    }

    public void setDiasList(List<Dia> diasList) {
        this.diasList = diasList;
    }

    public void getDiasList() {
        //Pegar o dia, hora1, hora2, hora3, hora4, hora5, hora6
        adapter.submitList(diasList);
        adapter.notifyDataSetChanged();
    }

    private void consultar(String codigo) {
        medicamentosViewModel.consultarMedicamentoPorCodigo(codigo).observe(getViewLifecycleOwner(), new Observer<MedicamentoDetalheResp>() {
            @Override
            public void onChanged(MedicamentoDetalheResp medicamentoDetalheResp) {
                if (medicamentoDetalheResp.getSucesso()) {
                    Log.d(TAG, "onChanged: Preescrição médica associada à um medicamento com sucesso!");
                    setMedicamento(medicamentoDetalheResp.getMedicamentoDetalhe());
                } else {
                    Log.d(TAG, "onChanged: Não conseguimos dados deste medicamento...");
                    Toast.makeText(getActivity(), "Não conseguimos dados deste medicamento", Toast.LENGTH_SHORT).show();
                    pbStatus.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void setMedicamento(MedicamentoDetalhe medicamentoDetalhe) {
        this.medicamentoDetalhe = medicamentoDetalhe;
        atualizarDadosTela();
    }

    private void atualizarDadosTela() {
        Log.d(TAG, "atualizarDadosTela: Atualizando dados na tela");
        try {
            if (medicamento != null) {
                Log.d(TAG, "atualizarDadosTela: Tem dados preescritos pelo médico...");
                tvUso.setText("Uso: " + medicamento.getUso());
                tvTratamento.setText("Tratamento: " + medicamento.getTratamento());
                tvFormula.setText("Fórmula: " + medicamento.getFormula());
                tvDosagem.setText("Dosagem: " + medicamento.getDosagem());
                tvConcentracao.setText("Concentração: " + medicamento.getConcentracao());
                tvQuantidade.setText("Quantidade: " + String.valueOf(medicamento.getQuantidade()));
                tvDias.setText("Dias: " + String.valueOf(medicamento.getDias()));
                tvfrequenciaH.setText("Frequência de uso: " + String.valueOf(medicamento.getFrequenciaH()));
                tvOrientacoes.setText("Orientaçòes: " + medicamento.getOrientacoes());
            }

            if (medicamentoDetalhe != null) {
                Log.d(TAG, "atualizarDadosTela: Tem medicamento associado à preescrição médica...");
                //Colocar a foto da caixa no image button
                Glide.with(getParentFragment())
                        .load(medicamentoDetalhe.getImagemCaixa())
                        .placeholder(R.drawable.ic_medicamentos)
                        .fitCenter()
                        .into(ibFoto);
                tvFarmaceutica.setText(medicamentoDetalhe.getLaboratorio());
                tvNome.setText(medicamentoDetalhe.getNome());
                tvPrecoMin.setText(String.valueOf(medicamentoDetalhe.getPrecoMin()));
                tvPrecoMed.setText(String.valueOf(medicamentoDetalhe.getPrecoMed()));
                tvPrecoMax.setText(String.valueOf(medicamentoDetalhe.getPrecoMax()));
                pbStatus.setVisibility(View.INVISIBLE);
            }
        } catch (Exception e) {
            Log.d(TAG, "atualizarDadosTela: Ocorreu um erro ao tentar atualizar os dados na tela...\nErro: " + e.getMessage());
        }
    }

    private void lerCodigoBarrasQRCode() {
        Log.d(TAG, "lerCodigoBarrasQRCode: Preparando para fazer a leitura do código de barras da embalagem do medicamento...");
        IntentIntegrator integrator = new IntentIntegrator(getActivity());
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Aponte para um código de barras/qrcode");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(true);
        integrator.setTorchEnabled(true);
        integrator.setOrientationLocked(true);
        integrator.forSupportFragment(this).initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                Log.d(TAG, "onActivityResult: Você saiu sem reconhecer um código de barras...");
                //cancelado
                Toast.makeText(getActivity(), "Você cancelou o reconhecimento", Toast.LENGTH_SHORT).show();
            } else {
                Log.d(TAG, "onActivityResult: Iniciando a busca de um medicamento pelo código de barras...");
                pbStatus.setVisibility(View.VISIBLE);
                String codigo = intentResult.getContents();
                consultar(codigo);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}