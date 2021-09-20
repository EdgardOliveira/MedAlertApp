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
                @Index("convenio"),
                @Index("idade")
        }
)
public class Paciente {
    @PrimaryKey
    @NonNull
    private String codigo;
    private String nome;
    private String convenio;
    private String acomodacao;
    private Date validade;
    private Integer idade;
    @Embedded(prefix = "paciente_")
    private Endereco endereco;

    @NonNull
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(@NonNull String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public String getAcomodacao() {
        return acomodacao;
    }

    public void setAcomodacao(String acomodacao) {
        this.acomodacao = acomodacao;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Paciente() {
    }

    public Paciente(@NonNull String codigo, String nome, String convenio, String acomodacao,
                    Date validade, Integer idade, Endereco endereco) {
        this.codigo = codigo;
        this.nome = nome;
        this.convenio = convenio;
        this.acomodacao = acomodacao;
        this.validade = validade;
        this.idade = idade;
        this.endereco = endereco;
    }
}
