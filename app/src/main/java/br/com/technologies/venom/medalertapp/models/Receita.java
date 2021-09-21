package br.com.technologies.venom.medalertapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(
        tableName = "receitas"
)
public class Receita implements Parcelable {
    @PrimaryKey
    @NonNull
    private Long codigo;
    @Ignore
    private List<Medicamento> medicamentos = null;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public Receita() {
    }

    @Ignore
    public Receita(Long codigo, List<Medicamento> medicamentos) {
        this.codigo = codigo;
        this.medicamentos = medicamentos;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.codigo);
        dest.writeList(this.medicamentos);
    }

    protected Receita(Parcel in) {
        this.codigo = (Long) in.readValue(Long.class.getClassLoader());
        this.medicamentos = new ArrayList<Medicamento>();
        in.readList(this.medicamentos, Medicamento.class.getClassLoader());
    }

    public static final Parcelable.Creator<Receita> CREATOR = new Parcelable.Creator<Receita>() {
        @Override
        public Receita createFromParcel(Parcel source) {
            return new Receita(source);
        }

        @Override
        public Receita[] newArray(int size) {
            return new Receita[size];
        }
    };
}
