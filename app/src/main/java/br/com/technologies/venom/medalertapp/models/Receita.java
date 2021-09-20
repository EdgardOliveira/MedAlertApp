package br.com.technologies.venom.medalertapp.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(
        tableName = "receitas"
)
public class Receita {
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
}
