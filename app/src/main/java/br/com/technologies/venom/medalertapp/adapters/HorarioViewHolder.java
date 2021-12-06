package br.com.technologies.venom.medalertapp.adapters;

import static br.com.technologies.venom.medalertapp.utils.Constantes.TAG;
import static br.com.technologies.venom.medalertapp.utils.Rotinas.formatarDataHumano;
import static br.com.technologies.venom.medalertapp.utils.Rotinas.formatarHoraHumano;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import br.com.technologies.venom.medalertapp.R;
import br.com.technologies.venom.medalertapp.models.Dia;
import br.com.technologies.venom.medalertapp.models.Horario;

public class HorarioViewHolder extends RecyclerView.ViewHolder {
    //atributos
    public TextView tvData, tvHora01, tvHora02, tvHora03, tvHora04, tvHora05, tvHora06;
    public Dia dia;

    //getters
    public Dia getDia() {
        return dia;
    }

    //setters
    public void setDia(Dia dia) {
        this.dia = dia;
    }

    //construtor
    public HorarioViewHolder(@NonNull View itemView) {
        super(itemView);
        tvData = itemView.findViewById(R.id.tvHorarioData);
        tvHora01 = itemView.findViewById(R.id.tvHorarioHora01);
        tvHora02 = itemView.findViewById(R.id.tvHorarioHora02);
        tvHora03 = itemView.findViewById(R.id.tvHorarioHora03);
        tvHora04 = itemView.findViewById(R.id.tvHorarioHora04);
        tvHora05 = itemView.findViewById(R.id.tvHorarioHora05);
        tvHora06 = itemView.findViewById(R.id.tvHorarioHora06);
    }

    //métodos
    public void bindTo(@Nullable Dia dia) {
        try {
            this.dia = dia;

            tvData.setText(dia.getData());
            tvHora01.setText(dia.getHora01());
            tvHora01.setVisibility(dia.getHora01() != "" ? View.VISIBLE : View.INVISIBLE);
            tvHora02.setText(dia.getHora02());
            tvHora02.setVisibility(dia.getHora02() != "" ? View.VISIBLE : View.INVISIBLE);
            tvHora03.setText(dia.getHora03());
            tvHora03.setVisibility(dia.getHora03() != "" ? View.VISIBLE : View.INVISIBLE);
            tvHora04.setText(dia.getHora04());
            tvHora04.setVisibility(dia.getHora04() != "" ? View.VISIBLE : View.INVISIBLE);
            tvHora05.setText(dia.getHora05());
            tvHora05.setVisibility(dia.getHora05() != "" ? View.VISIBLE : View.INVISIBLE);
            tvHora06.setText(dia.getHora06());
            tvHora06.setVisibility(dia.getHora06() != "" ? View.VISIBLE : View.INVISIBLE);

        } catch (Exception e) {
            Log.d(TAG, "bindTo: Ocorreu um erro ao tentar fazer binding.\nErro: " + e.getMessage());
        }
    }
}