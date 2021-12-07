package br.com.technologies.venom.medalertapp.adapters;


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
import br.com.technologies.venom.medalertapp.models.Dia;

public class HorarioAdapter extends RecyclerView.Adapter<HorarioViewHolder> {
    private final AsyncListDiffer<Dia> mDiffer = new AsyncListDiffer(this, DIFF_CALLBACK);

    @Override
    public int getItemCount() {
        return mDiffer.getCurrentList().size();
    }

    public void submitList(List<Dia> list) {
        Log.d("teste", "submitList: " + list.size());
        mDiffer.submitList(list);
    }

    @NonNull
    @Override
    public HorarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_horario_item, parent, false);
        return new HorarioViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(HorarioViewHolder holder, int position) {
        Dia dia = mDiffer.getCurrentList().get(position);
        holder.bindTo(dia);
    }


    public static final DiffUtil.ItemCallback<Dia> DIFF_CALLBACK = new DiffUtil.ItemCallback<Dia>() {

        @Override
        public boolean areItemsTheSame(
                @NonNull Dia oldData, @NonNull Dia newData) {
            return oldData.getId() == (newData.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Dia oldData, @NonNull Dia newData) {
            return oldData.getId() == newData.getId();
        }
    };
}