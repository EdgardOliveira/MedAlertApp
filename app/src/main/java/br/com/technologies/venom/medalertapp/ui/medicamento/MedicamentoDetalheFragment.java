package br.com.technologies.venom.medalertapp.ui.medicamento;

import static br.com.technologies.venom.medalertapp.utils.Rotinas.abrirPagina;

import android.content.Intent;
import android.os.Bundle;
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

import com.bumptech.glide.Glide;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import br.com.technologies.venom.medalertapp.R;
import br.com.technologies.venom.medalertapp.databinding.FragmentMedicamentoDetalheBinding;
import br.com.technologies.venom.medalertapp.models.MedicamentoDetalhe;
import br.com.technologies.venom.medalertapp.models.MedicamentoDetalheResp;

public class MedicamentoDetalheFragment  extends Fragment implements View.OnClickListener {

    private MedicamentosViewModel medicamentosViewModel;
    private FragmentMedicamentoDetalheBinding binding;
    private ImageButton ibFoto;
    private Button btnBula;
    private TextView tvNome, tvFarmaceutica, tvPrecoMin, tvPrecoMed, tvPrecoMax;
    private MedicamentoDetalhe medicamentoDetalhe;
    private ProgressBar pbStatus;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        medicamentosViewModel = new ViewModelProvider(this).get(MedicamentosViewModel.class);

        binding = FragmentMedicamentoDetalheBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        medicamentoDetalhe = new MedicamentoDetalhe();

        ibFoto = binding.ibFoto;
        tvFarmaceutica = binding.tvFarmaceutica;
        tvNome = binding.tvNome;
        tvPrecoMin = binding.tvPrecoMin;
        tvPrecoMed = binding.tvPrecoMed;
        tvPrecoMax = binding.tvPrecoMax;
        btnBula = binding.btnBula;
        pbStatus = binding.progressBar;

        ibFoto.setOnClickListener(this);
        btnBula.setOnClickListener(this);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibFoto:
                lerCodigoBarrasQRCode();
                break;
            case R.id.btnBula:
                abrirBula();
                break;
        }
    }

    private void abrirBula() {
        abrirPagina(getActivity(), medicamentoDetalhe.getBula());
    }

    private void consultar(String codigo){
        medicamentosViewModel.consultarMedicamentoPorCodigo(codigo).observe(getViewLifecycleOwner(), new Observer<MedicamentoDetalheResp>() {
            @Override
            public void onChanged(MedicamentoDetalheResp medicamentoDetalheResp) {
                if (medicamentoDetalheResp.getSucesso()){
                    setMedicamento(medicamentoDetalheResp.getMedicamentoDetalhe());
                } else {
                    Toast.makeText(getActivity(), "Não conseguimos dados deste medicamento", Toast.LENGTH_SHORT).show();
                    pbStatus.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void setMedicamento(MedicamentoDetalhe medicamentoDetalhe){
        this.medicamentoDetalhe = medicamentoDetalhe;
        atualizarDadosTela();
    }

    private void atualizarDadosTela(){
        try{
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
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void lerCodigoBarrasQRCode() {
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
                //cancelado
                Toast.makeText(getActivity(), "Você cancelou o reconhecimento", Toast.LENGTH_SHORT).show();
            } else {
                pbStatus.setVisibility(View.VISIBLE);
                String codigo = intentResult.getContents();
                consultar(codigo);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}