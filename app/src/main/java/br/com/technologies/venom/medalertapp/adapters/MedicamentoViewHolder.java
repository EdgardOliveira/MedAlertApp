package br.com.technologies.venom.medalertapp.adapters;

import static br.com.technologies.venom.medalertapp.utils.Constantes.TAG;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;

import br.com.technologies.venom.medalertapp.R;
import br.com.technologies.venom.medalertapp.models.Medicamento;

public class MedicamentoViewHolder extends RecyclerView.ViewHolder {
    //atributos
    public TextView tvRemedio, tvRemedioTitulo, tvFormula, tvFormulaTitulo, tvDosagem, tvDosagemTitulo, tvHora01, tvHora02, tvHora03;
    public ImageView ivMedicamento;
    public Medicamento medicamento;
    public ShimmerFrameLayout shimmerFrameLayout;

    //getters
    public Medicamento getMedicamento() {
        return medicamento;
    }

    //setters
    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    //construtor
    public MedicamentoViewHolder(@NonNull View itemView) {
        super(itemView);
        shimmerFrameLayout = itemView.findViewById(R.id.shimmerFrameLayout);
        tvRemedio = itemView.findViewById(R.id.tvCVMedicamentoRemedio);
        tvRemedioTitulo = itemView.findViewById(R.id.tvCVMedicamentoRemedioTItulo);
        tvFormula = itemView.findViewById(R.id.tvCVMedicamentoFormula);
        tvFormulaTitulo = itemView.findViewById(R.id.tvCVMedicamentoFormulaTitulo);
        tvDosagem = itemView.findViewById(R.id.tvCVMedicamentoDosagem);
        tvDosagemTitulo = itemView.findViewById(R.id.tvCVMedicamentoDosagemTitulo);
        ivMedicamento = itemView.findViewById(R.id.ivCVMedicamento);
    }

    //métodos
    public void bindTo(@Nullable Medicamento medicamento) {
        try{
            this.medicamento = medicamento;
            shimmerFrameLayout.stopShimmer();
            shimmerFrameLayout.setShimmer(null);

            tvRemedioTitulo.setBackground(null);
            tvRemedio.setBackground(null);
            tvRemedio.setText("");

            tvFormulaTitulo.setBackground(null);
            tvFormula.setBackground(null);
            tvFormula.setText(medicamento.getFormula());

            tvDosagemTitulo.setBackground(null);
            tvDosagem.setBackground(null);
            tvDosagem.setText(medicamento.getDosagem());

            ivMedicamento.setBackground(null);
            ivMedicamento.setImageResource(R.drawable.ic_medicamentos);
        }catch (Exception e){
            Log.d(TAG, "bindTo: Ocorreu um erro ao tentar fazer binding.\nErro: " + e.getMessage());
        }
    }
}