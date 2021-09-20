package br.com.technologies.venom.medalertapp.adapters;


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
import br.com.technologies.venom.medalertapp.models.Receita;
import static br.com.technologies.venom.medalertapp.utils.Constantes.TAG;

public class ReceitaViewHolder extends RecyclerView.ViewHolder {
    //atributos
    public CardView cvReceita;
    public TextView tvId, tvIdTitulo, tvStatus, tvStatusTitulo, tvTratamento, tvTratamentoTitulo;
    public ImageView ivReceita;
    public Receita receita;
    public ShimmerFrameLayout shimmerFrameLayout;

    //getters
    public Receita getReceita() {
        return receita;
    }

    //setters
    public void setReceita(Receita receita) {
        this.receita = receita;
    }

    //construtor
    public ReceitaViewHolder(@NonNull View itemView) {
        super(itemView);
        shimmerFrameLayout = itemView.findViewById(R.id.shimmerFrameLayout);
        tvId = itemView.findViewById(R.id.tvCVReceitaId);
        tvIdTitulo = itemView.findViewById(R.id.tvCVReceitaIdTItulo);
        tvStatus = itemView.findViewById(R.id.tvCVReceitaStatus);
        tvStatusTitulo = itemView.findViewById(R.id.tvCVReceitaStatusTitulo);
        tvTratamento = itemView.findViewById(R.id.tvCVReceitaTratamento);
        tvTratamentoTitulo = itemView.findViewById(R.id.tvCVReceitaTratamentoTitulo);
        ivReceita = itemView.findViewById(R.id.ivCVReceita);
    }

    //métodos
    public void bindTo(@Nullable Receita receita) {
        try{
            this.receita = receita;
            shimmerFrameLayout.stopShimmer();
            shimmerFrameLayout.setShimmer(null);

            tvIdTitulo.setBackground(null);
            tvId.setBackground(null);
            tvId.setText(String.valueOf(receita.getCodigo()));

            tvStatusTitulo.setBackground(null);
            tvStatus.setBackground(null);
            tvStatus.setText("Em andamento");

            tvTratamentoTitulo.setBackground(null);
            tvTratamento.setBackground(null);
            String tratamentos = "";
            for (Medicamento medicamento : receita.getMedicamentos()) {
                tratamentos += medicamento.getTratamento() + " | ";
            }
            tvTratamento.setText(tratamentos);

            ivReceita.setBackground(null);
            ivReceita.setImageResource(R.drawable.ic_menu_camera);
        }catch (Exception e){
            Log.d(TAG, "bindTo: Ocorreu um erro ao tentar fazer binding.\nErro: " + e.getMessage());
        }
    }
}