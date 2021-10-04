package br.com.technologies.venom.medalertapp.models;

import androidx.room.PrimaryKey;

public class Especialidade {
    @PrimaryKey
    private String id;
    private String nome;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Especialidade() {
    }

    public Especialidade(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
