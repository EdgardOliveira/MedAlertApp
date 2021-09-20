package br.com.technologies.venom.medalertapp.models;

public class Consulta {
    private String dataHora;
    private Medico medico;
    private Paciente paciente;
    private Receita receita;

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }

    public Consulta() {
    }

    public Consulta(String dataHora, Medico medico, Paciente paciente, Receita receita) {
        this.dataHora = dataHora;
        this.medico = medico;
        this.paciente = paciente;
        this.receita = receita;
    }
}
