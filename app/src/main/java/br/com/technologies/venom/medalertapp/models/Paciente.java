package br.com.technologies.venom.medalertapp.models;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(
        tableName = "pacientes",
        indices = {
                @Index("nome"),
        }
)
public class Paciente {
    @PrimaryKey
    @NonNull
    private String id;
    private String nome;
    private Date dataNascimento;
    @Embedded(prefix = "conv_")
    private Convenio convenio;
    @Embedded(prefix = "end_")
    private Endereco endereco;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Paciente() {
    }

    @Ignore
    public Paciente(@NonNull String id, String nome, Date dataNascimento, Convenio convenio, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.convenio = convenio;
        this.endereco = endereco;
    }
}
