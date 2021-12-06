package br.com.technologies.venom.medalertapp.ui.medicamento;

import static br.com.technologies.venom.medalertapp.utils.Rotinas.abrirPagina;

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

import br.com.technologies.venom.medalertapp.R;
import br.com.technologies.venom.medalertapp.adapters.HorarioAdapter;
import br.com.technologies.venom.medalertapp.adapters.MedicamentoAdapter;
import br.com.technologies.venom.medalertapp.databinding.FragmentMedicamentoDetalheBinding;
import br.com.technologies.venom.medalertapp.models.Dia;
import br.com.technologies.venom.medalertapp.models.DiaHora;
import br.com.technologies.venom.medalertapp.models.Horario;
import br.com.technologies.venom.medalertapp.models.Medicamento;
import br.com.technologies.venom.medalertapp.models.MedicamentoDetalhe;
import br.com.technologies.venom.medalertapp.models.MedicamentoDetalheResp;
import br.com.technologies.venom.medalertapp.models.Receita;

import static br.com.technologies.venom.medalertapp.utils.Constantes.TAG;
import static br.com.technologies.venom.medalertapp.utils.Rotinas.formatarDataHumano;
import static br.com.technologies.venom.medalertapp.utils.Rotinas.formatarHoraHumano;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    private List<Horario> horariosList = new ArrayList<>();
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
                consultarHorarios();
                break;
        }
    }

    private void iniciarTratamento(Calendar dataInicial) {
        int frequenciaAoDia = (Integer) 24 / medicamento.getFrequenciaH();
        int dias = medicamento.getDias();
        Calendar dataCalculada = dataInicial;
        List<Calendar> horariosList = new ArrayList<>();
        Horario horario;
        Log.d(TAG, "iniciarTratamento: O remédio será tomado de " + medicamento.getFrequenciaH() + "h/" + medicamento.getFrequenciaH() + "h durante " + dias  + "dia(s)");

        for (int i = 0; i < dias; i++) {
            //Incrementa os dias
            dataCalculada.set(Calendar.MINUTE, 0);
            dataCalculada.set(Calendar.SECOND, 0);
            dataCalculada.set(Calendar.DATE, Calendar.DATE + i);
            switch (frequenciaAoDia - 1) {
                case 0: //Tomar 1x ao dia
                    Log.d(TAG, "iniciarTratamento: Tomar 1x ao dia...");
                    //Antes de dormir as 22h?
                    dataCalculada.set(Calendar.HOUR, 22);
                    horariosList.add(dataCalculada);
                    break;
                case 1: //Tomar 2x ao dia (12h/12h)
                    //11h e 23h
                    Log.d(TAG, "iniciarTratamento: Tomar 2x ao dia...");
                    dataCalculada.set(Calendar.HOUR, 11);
                    horariosList.add(dataCalculada);
                    dataCalculada.set(Calendar.HOUR, 23);
                    horariosList.add(dataCalculada);
                    break;
                case 2: //Tomar 3x ao dia (8h/8h)
                    //7h, 15h e 23h
                    Log.d(TAG, "iniciarTratamento: Tomar 3x ao dia...");
                    dataCalculada.set(Calendar.HOUR, 7);
                    horario = new Horario(
                            medicamento.getId(),
                            dataCalculada.getTime(),
                            0
                    );
                    medicamentosViewModel.cadastrarHorario(horario);
                    horariosList.add(dataCalculada);
                    dataCalculada.set(Calendar.HOUR, 15);
                    horario = new Horario(
                            medicamento.getId(),
                            dataCalculada.getTime(),
                            0
                    );
                    medicamentosViewModel.cadastrarHorario(horario);
                    horariosList.add(dataCalculada);
                    dataCalculada.set(Calendar.HOUR, 23);
                    horario = new Horario(
                            medicamento.getId(),
                            dataCalculada.getTime(),
                            0
                    );
                    medicamentosViewModel.cadastrarHorario(horario);
                    horariosList.add(dataCalculada);
                    break;
                case 3: //Tomar 4x ao dia (6h/6h)
                    //6h, 12h, 18h e 24h
                    Log.d(TAG, "iniciarTratamento: Tomar 4x ao dia...");
                    dataCalculada.set(Calendar.HOUR, 0);
                    horariosList.add(dataCalculada);
                    dataCalculada.set(Calendar.HOUR, 6);
                    horariosList.add(dataCalculada);
                    dataCalculada.set(Calendar.HOUR, 12);
                    horariosList.add(dataCalculada);
                    dataCalculada.set(Calendar.HOUR, 18);
                    horariosList.add(dataCalculada);
                    break;
                case 5: //Tomar 6x ao dia (4h/4h)
                    //2h, 6h, 10h, 14h, 18h e 22h
                    Log.d(TAG, "iniciarTratamento: Tomar 6x ao dia...");
                    dataCalculada.set(Calendar.HOUR, 2);
                    horariosList.add(dataCalculada);
                    dataCalculada.set(Calendar.HOUR, 6);
                    horariosList.add(dataCalculada);
                    dataCalculada.set(Calendar.HOUR, 10);
                    horariosList.add(dataCalculada);
                    dataCalculada.set(Calendar.HOUR, 14);
                    horariosList.add(dataCalculada);
                    dataCalculada.set(Calendar.HOUR, 18);
                    horariosList.add(dataCalculada);
                    dataCalculada.set(Calendar.HOUR, 22);
                    horariosList.add(dataCalculada);
                    break;
            }
        }

        cadastrarHorarios(horariosList);
    }

    private void cadastrarHorarios(List<Calendar> horariosList) {
        for (Calendar dtCalc : horariosList) {
            Horario horario = new Horario(
                    medicamento.getId(),
                    dtCalc.getTime(),
                    0
            );
//            medicamentosViewModel.cadastrarHorario(horario);
        }
    }

    private void consultarHorarios() {

        medicamentosViewModel.listarPorMedicamentoId(medicamento.getId()).observe(getViewLifecycleOwner(), new Observer<List<Horario>>() {

            @Override
            public void onChanged(List<Horario> horariosList) {
                if (horariosList.size() > 0) {
                    setHorariosList(horariosList);
                    getHorariosList();
                }
            }
        });
    }

    public void setHorariosList(List<Horario> horariosList) {
        this.horariosList = horariosList;
    }

    public void getHorariosList() {
        List<DiaHora> diaHoraList = new ArrayList<>();
        for (Horario horario : this.horariosList) {
            DiaHora diaHora = new DiaHora(
                    formatarDataHumano.format(horario.getDataHora()),
                    formatarHoraHumano.format(horario.getDataHora())
            );
            diaHoraList.add(diaHora);
            Log.d(TAG, "onChanged: Data: " + diaHora.getDia() +
                    " Hora: " + diaHora.getHora());
        }
        List<String> datasList = new ArrayList<>();
        for (Horario horario : this.horariosList) {
            String data = formatarDataHumano.format(horario.getDataHora());
            String hora = formatarHoraHumano.format(horario.getDataHora());
            if (!datasList.contains(data)) {
                Log.d(TAG, "getHorariosList: Adicionado data... " + data);
                Log.d(TAG, "getHorariosList: Adicionando hora..." + hora);
                datasList.add(data);
            }
        }

        for (String data : datasList) {
            List<String> dias = datasList.stream().filter(data::equalsIgnoreCase).collect(Collectors.toList());
            for (String dia : dias) {
                Log.d(TAG, "getHorariosList: Horas: " + dia);
                //filtrar as horas que aparecem em uma data
                if (diaHoraList.contains(dias))
                    Log.d(TAG, "getHorariosList: Encontrei...");
            }
////            Dia dia = new Dia(
////                    data,
////                    horarios.get(0),
////                    horarios.get(1),
////                    horarios.get(2),
////                    horarios.get(3),
////                    horarios.get(4),
////                    horarios.get(5)
////            );
////            diaList.add(dia);
        }
//
//        adapter.submitList(diaList);
//        adapter.notifyDataSetChanged();
    }

    private void consultar(String codigo) {
        medicamentosViewModel.consultarMedicamentoPorCodigo(codigo).observe(getViewLifecycleOwner(), new Observer<MedicamentoDetalheResp>() {
            @Override
            public void onChanged(MedicamentoDetalheResp medicamentoDetalheResp) {
                if (medicamentoDetalheResp.getSucesso()) {
                    Log.d(TAG, "onChanged: Preescrição médica associada à um medicamento com sucesso!");
                    setMedicamento(medicamentoDetalheResp.getMedicamentoDetalhe());
                } else {
                    Log.d(TAG, "onChanged: Não conseguimos dadso deste medicamento...");
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