package br.com.technologies.venom.medalertapp.models;

import java.util.List;

public class ConsultaWrapper {
    private boolean sucesso;
    private String mensagem;
    private Consultas dados;

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

    public Consultas getDados() {
        return dados;
    }

    public void setDados(Consultas dados) {
        this.dados = dados;
    }

    public ConsultaWrapper() {
    }

    public ConsultaWrapper(boolean sucesso, String mensagem, Consultas dados) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
        this.dados = dados;
    }
}
