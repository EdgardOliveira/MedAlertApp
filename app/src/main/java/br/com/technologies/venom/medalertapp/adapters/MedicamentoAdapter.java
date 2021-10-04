package br.com.technologies.venom.medalertapp.adapters;

import static br.com.technologies.venom.medalertapp.utils.Constantes.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.technologies.venom.medalertapp.R;
import br.com.technologies.venom.medalertapp.models.Medicamento;

public class MedicamentoAdapter extends RecyclerView.Adapter<MedicamentoViewHolder> {
    private final AsyncListDiffer<Medicamento> mDiffer = new AsyncListDiffer(this, DIFF_CALLBACK);
    boolean isShimmer = true;   //Ao criar já habilita o efeito
    int shimmerNumber = 10;     //vai exibir a quantidade de cards que irãi ficar em shimmerEffect

    @Override
    public int getItemCount() {
        return isShimmer ? shimmerNumber :  mDiffer.getCurrentList().size();
    }

    public void submitList(List<Medicamento> list) {
        mDiffer.submitList(list);
    }

    public void setShimmer(boolean isShimmer){
        this.isShimmer = isShimmer;
    }

    @NonNull
    @Override
    public MedicamentoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_medicamentos_item, parent, false);
        return new MedicamentoViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(MedicamentoViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: o efeito está " + (isShimmer ? "ligado" : "desligado"));
        if (isShimmer){
            holder.shimmerFrameLayout.startShimmer();
        }else{
            Medicamento medicamento = mDiffer.getCurrentList().get(position);
            holder.bindTo(medicamento);
        }
    }

    public static final DiffUtil.ItemCallback<Medicamento> DIFF_CALLBACK = new DiffUtil.ItemCallback<Medicamento>() {

        @Override
        public boolean areItemsTheSame(
                @NonNull Medicamento oldMedicamento, @NonNull Medicamento newMedicamento) {
            return oldMedicamento.getId() == newMedicamento.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Medicamento oldMedicamento, @NonNull Medicamento newMedicamento) {
            return oldMedicamento.getId().equals(newMedicamento.getId());
        }
    };
}
