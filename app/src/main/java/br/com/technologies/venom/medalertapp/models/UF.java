package br.com.technologies.venom.medalertapp.models;

import androidx.annotation.NonNull;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

public class UF {
    @PrimaryKey
    @NonNull
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

    public UF() {
    }

    @Ignore
    public UF(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
