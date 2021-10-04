package br.com.technologies.venom.medalertapp.models;

import androidx.annotation.NonNull;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

public class Empresa {
    @PrimaryKey
    @NonNull
    private String id;
    private String cnpj;
    private String nome;
    private String nomeFantasia;
    private String telefone;
    private String enderecoId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(String enderecoId) {
        this.enderecoId = enderecoId;
    }

    public Empresa() {
    }

    @Ignore
    public Empresa(String id, String cnpj, String nome, String nomeFantasia, String telefone, String enderecoId) {
        this.id = id;
        this.cnpj = cnpj;
        this.nome = nome;
        this.nomeFantasia = nomeFantasia;
        this.telefone = telefone;
        this.enderecoId = enderecoId;
    }
}
