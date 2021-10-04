package br.com.technologies.venom.medalertapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "receitas")
public class Receita implements Parcelable {
    @PrimaryKey
    @NonNull
    private String id;
    @Ignore
    private List<Medicamento> medicamentos = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    public Receita(String id, List<Medicamento> medicamentos) {
        this.id = id;
        this.medicamentos = medicamentos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeTypedList(this.medicamentos);
    }

    protected Receita(Parcel in) {
        this.id = in.readString();
        this.medicamentos = in.createTypedArrayList(Medicamento.CREATOR);
    }

    public static final Creator<Receita> CREATOR = new Creator<Receita>() {
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
