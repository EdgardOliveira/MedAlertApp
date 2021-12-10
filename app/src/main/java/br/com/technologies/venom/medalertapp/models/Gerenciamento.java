package br.com.technologies.venom.medalertapp.models;

public class Gerenciamento {
    private String id;
    private String dispensadorId;
    private String pacienteId;
    private String receitaId;
    private String dataHora;
    private int recipiente;
    private String status;
    private String dataHoraConfirmacao;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDispensadorId() {
        return dispensadorId;
    }

    public void setDispensadorId(String dispensadorId) {
        this.dispensadorId = dispensadorId;
    }

    public String getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(String pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getReceitaId() {
        return receitaId;
    }

    public void setReceitaId(String receitaId) {
        this.receitaId = receitaId;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public int getRecipiente() {
        return recipiente;
    }

    public void setRecipiente(int recipiente) {
        this.recipiente = recipiente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataHoraConfirmacao() {
        return dataHoraConfirmacao;
    }

    public void setDataHoraConfirmacao(String dataHoraConfirmacao) {
        this.dataHoraConfirmacao = dataHoraConfirmacao;
    }

    public Gerenciamento(String dispensadorId, String pacienteId, String receitaId, String dataHora, int recipiente) {
        this.dispensadorId = dispensadorId;
        this.pacienteId = pacienteId;
        this.receitaId = receitaId;
        this.dataHora = dataHora;
        this.recipiente = recipiente;
    }

    public Gerenciamento(String id, String dispensadorId, String pacienteId, String receitaId, String dataHora, int recipiente, String status, String dataHoraConfirmacao) {
        this.id = id;
        this.dispensadorId = dispensadorId;
        this.pacienteId = pacienteId;
        this.receitaId = receitaId;
        this.dataHora = dataHora;
        this.recipiente = recipiente;
        this.status = status;
        this.dataHoraConfirmacao = dataHoraConfirmacao;
    }
}
