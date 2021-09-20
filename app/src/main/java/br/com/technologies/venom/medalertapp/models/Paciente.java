package br.com.technologies.venom.medalertapp.models;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

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
    private String validade;
    private String idade;
    @Embedded(prefix = "paciente_")
    private Endereco endereco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
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

    @Ignore
    public Paciente(String nome, String codigo, String convenio, String acomodacao, String validade, String idade, Endereco endereco) {
        this.nome = nome;
        this.codigo = codigo;
        this.convenio = convenio;
        this.acomodacao = acomodacao;
        this.validade = validade;
        this.idade = idade;
        this.endereco = endereco;
    }
}
