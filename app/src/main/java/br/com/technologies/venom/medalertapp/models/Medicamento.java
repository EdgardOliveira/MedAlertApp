package br.com.technologies.venom.medalertapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "medicamentos",
        indices = {
                @Index("uso"),
                @Index("tratamento"),
                @Index("formula")
        }
)
public class Medicamento implements Parcelable {
    @PrimaryKey
    @NonNull
    private String id;
    private String receitaId;
    private String uso;
    private String tratamento;
    private String formula;
    private String dosagem;
    private String concentracao;
    private Integer quantidade;
    private Integer dias;
    private Integer frequenciaH;
    private String orientacoes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReceitaId() {
        return receitaId;
    }

    public void setReceitaId(String receitaId) {
        this.receitaId = receitaId;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public String getTratamento() {
        return tratamento;
    }

    public void setTratamento(String tratamento) {
        this.tratamento = tratamento;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public String getConcentracao() {
        return concentracao;
    }

    public void setConcentracao(String concentracao) {
        this.concentracao = concentracao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public Integer getFrequenciaH() {
        return frequenciaH;
    }

    public void setFrequenciaH(Integer frequenciaH) {
        this.frequenciaH = frequenciaH;
    }

    public String getOrientacoes() {
        return orientacoes;
    }

    public void setOrientacoes(String orientacoes) {
        this.orientacoes = orientacoes;
    }

    public Medicamento() {
    }

    @Ignore
    public Medicamento(String id, String receitaId, String uso, String tratamento, String formula, String dosagem, String concentracao, Integer quantidade, Integer dias, Integer frequenciaH, String orientacoes) {
        this.id = id;
        this.receitaId = receitaId;
        this.uso = uso;
        this.tratamento = tratamento;
        this.formula = formula;
        this.dosagem = dosagem;
        this.concentracao = concentracao;
        this.quantidade = quantidade;
        this.dias = dias;
        this.frequenciaH = frequenciaH;
        this.orientacoes = orientacoes;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.receitaId);
        dest.writeString(this.uso);
        dest.writeString(this.tratamento);
        dest.writeString(this.formula);
        dest.writeString(this.dosagem);
        dest.writeString(this.concentracao);
        dest.writeValue(this.quantidade);
        dest.writeValue(this.dias);
        dest.writeValue(this.frequenciaH);
        dest.writeString(this.orientacoes);
    }

    protected Medicamento(Parcel in) {
        this.id = in.readString();
        this.receitaId = in.readString();
        this.uso = in.readString();
        this.tratamento = in.readString();
        this.formula = in.readString();
        this.dosagem = in.readString();
        this.concentracao = in.readString();
        this.quantidade = (Integer) in.readValue(Integer.class.getClassLoader());
        this.dias = (Integer) in.readValue(Integer.class.getClassLoader());
        this.frequenciaH = (Integer) in.readValue(Integer.class.getClassLoader());
        this.orientacoes = in.readString();
    }

    public static final Creator<Medicamento> CREATOR = new Creator<Medicamento>() {
        @Override
        public Medicamento createFromParcel(Parcel source) {
            return new Medicamento(source);
        }

        @Override
        public Medicamento[] newArray(int size) {
            return new Medicamento[size];
        }
    };
}
