package br.com.technologies.venom.medalertapp.models;

public class ReceitaWrapper {
    private boolean sucesso;
    private String mensagem;
    private DadosReceita receita;

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

    public DadosReceita getReceita() {
        return receita;
    }

    public void setReceita(DadosReceita receita) {
        this.receita = receita;
    }

    public ReceitaWrapper() {
    }

    public ReceitaWrapper(boolean sucesso, String mensagem, DadosReceita receita) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
        this.receita = receita;
    }
}
