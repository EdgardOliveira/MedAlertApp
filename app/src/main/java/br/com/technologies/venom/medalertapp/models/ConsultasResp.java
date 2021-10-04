package br.com.technologies.venom.medalertapp.models;

import androidx.room.Ignore;

import java.util.List;

public class ConsultasResp {
    private boolean sucesso;
    private String mensagem;
    private List<Consulta> consultas = null;

    public boolean isSucesso() {
        return sucesso;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    public ConsultasResp() {
    }

    @Ignore
    public ConsultasResp(boolean sucesso, String mensagem, List<Consulta> consultas) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
        this.consultas = consultas;
    }
}
