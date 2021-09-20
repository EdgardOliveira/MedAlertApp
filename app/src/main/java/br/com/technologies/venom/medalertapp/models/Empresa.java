package br.com.technologies.venom.medalertapp.models;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "empresas",
        indices = {
                @Index("nome"),
                @Index("nomeFantasia"),
                @Index("telefone")
        }
)
public class Empresa {
    @PrimaryKey
    @NonNull
    private String cnpj;
    private String nome;
    private String nomeFantasia;
    private String telefone;
    @Embedded(prefix = "empresa_")
    private Endereco endereco;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Empresa() {
    }

    @Ignore
    public Empresa(String cnpj, String nome, String nomeFantasia, Endereco endereco, String telefone) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.nomeFantasia = nomeFantasia;
        this.endereco = endereco;
        this.telefone = telefone;
    }
}
