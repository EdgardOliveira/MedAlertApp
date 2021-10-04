package br.com.technologies.venom.medalertapp.models;

import androidx.room.Ignore;
import androidx.room.PrimaryKey;


public class Medico {
    @PrimaryKey
    private String id;
    private String nome;
    private String crm;
    private String especialidadeId;

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

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidadeId() {
        return especialidadeId;
    }

    public void setEspecialidadeId(String especialidadeId) {
        this.especialidadeId = especialidadeId;
    }

    public Medico() {
    }

    @Ignore
    public Medico(String id, String nome, String crm, String especialidadeId) {
        this.id = id;
        this.nome = nome;
        this.crm = crm;
        this.especialidadeId = especialidadeId;
    }
}
