package br.com.technologies.venom.medalertapp.models;

public class Consulta {
    private Long id;
    private String dataHora;
    private Empresa empresa;
    private Medico medico;
    private Paciente paciente;
    private Receita receita;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
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

    public Consulta(Long id, String dataHora, Empresa empresa, Medico medico, Paciente paciente, Receita receita) {
        this.id = id;
        this.dataHora = dataHora;
        this.empresa = empresa;
        this.medico = medico;
        this.paciente = paciente;
        this.receita = receita;
    }
}
