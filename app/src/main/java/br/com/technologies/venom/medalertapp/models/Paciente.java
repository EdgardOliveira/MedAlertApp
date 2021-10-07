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
                @Index("dataNascimento"),
        }
)
public class Paciente {
    @PrimaryKey
    @NonNull
    private String id;
    private Date dataNascimento;
    @Embedded(prefix = "conv_")
    private Convenio convenio;
    @Embedded(prefix = "end_")
    private Endereco endereco;
    @Embedded(prefix = "usr_")
    private Usuario usuario;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Paciente() {
    }

    @Ignore
    public Paciente(@NonNull String id, Date dataNascimento, Convenio convenio, Endereco endereco, Usuario usuario) {
        this.id = id;
        this.dataNascimento = dataNascimento;
        this.convenio = convenio;
        this.endereco = endereco;
        this.usuario = usuario;
    }
}
