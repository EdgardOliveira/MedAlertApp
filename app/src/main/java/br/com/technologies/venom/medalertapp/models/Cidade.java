package br.com.technologies.venom.medalertapp.models;


import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

public class Cidade {
    @PrimaryKey
    @NonNull
    private String id;
    private String nome;
    @Embedded(prefix = "uf_")
    private UF ufs;

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

    public UF getUfs() {
        return ufs;
    }

    public void setUfs(UF ufs) {
        this.ufs = ufs;
    }

    public Cidade() {
    }

    @Ignore
    public Cidade(@NonNull String id, String nome, UF ufs) {
        this.id = id;
        this.nome = nome;
        this.ufs = ufs;
    }
}
