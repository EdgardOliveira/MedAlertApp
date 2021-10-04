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
import br.com.technologies.venom.medalertapp.models.Receita;

public class ReceitaAdapter extends RecyclerView.Adapter<ReceitaViewHolder> {
    private final AsyncListDiffer<Receita> mDiffer = new AsyncListDiffer(this, DIFF_CALLBACK);
    boolean isShimmer = true;   //Ao criar já habilita o efeito
    int shimmerNumber = 10;     //vai exibir a quantidade de cards que irãi ficar em shimmerEffect

    @Override
    public int getItemCount() {
        return isShimmer ? shimmerNumber :  mDiffer.getCurrentList().size();
    }

    public void submitList(List<Receita> list) {
        mDiffer.submitList(list);
    }

    public void setShimmer(boolean isShimmer){
        this.isShimmer = isShimmer;
    }

    @NonNull
    @Override
    public ReceitaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_receitas_item, parent, false);
        return new ReceitaViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(ReceitaViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: o efeito está " + (isShimmer ? "ligado" : "desligado"));
        if (isShimmer){
            holder.shimmerFrameLayout.startShimmer();
        }else{
            Receita receita = mDiffer.getCurrentList().get(position);
            holder.bindTo(receita);
        }
    }

    public static final DiffUtil.ItemCallback<Receita> DIFF_CALLBACK = new DiffUtil.ItemCallback<Receita>() {

        @Override
        public boolean areItemsTheSame(
                @NonNull Receita oldReceita, @NonNull Receita newReceita) {
            return oldReceita.getId() == newReceita.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Receita oldReceita, @NonNull Receita newReceita) {
            return oldReceita.getId().equals(newReceita.getId());
        }
    };
}