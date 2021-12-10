package br.com.technologies.venom.medalertapp.models;

public class GerenciamentoResp {
    private boolean sucesso;
    private String mensagem;
    private Gerenciamento gerenciamento;

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

    public Gerenciamento getGerenciamento() {
        return gerenciamento;
    }

    public void setGerenciamento(Gerenciamento gerenciamento) {
        this.gerenciamento = gerenciamento;
    }

    public GerenciamentoResp() {
    }

    public GerenciamentoResp(boolean sucesso, String mensagem, Gerenciamento gerenciamento) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
        this.gerenciamento = gerenciamento;
    }
}
