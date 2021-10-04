package br.com.technologies.venom.medalertapp.models;

import androidx.room.PrimaryKey;

import java.util.Date;

public class Consulta {
    @PrimaryKey
    private String id;
    private Date dataHora;
    private String empresaId;
    private Empresa empresa;
    private String medicoId;
    private Medico medico;
    private String pacienteId;
    private Paciente paciente;
    private String receitaId;
    private Receita receita;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public String getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(String empresaId) {
        this.empresaId = empresaId;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(String medicoId) {
        this.medicoId = medicoId;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(String pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getReceitaId() {
        return receitaId;
    }

    public void setReceitaId(String receitaId) {
        this.receitaId = receitaId;
    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }

    public Consulta() {
    }

    public Consulta(String id, Date dataHora, String empresaId, Empresa empresa, String medicoId, Medico medico, String pacienteId, Paciente paciente, String receitaId, Receita receita) {
        this.id = id;
        this.dataHora = dataHora;
        this.empresaId = empresaId;
        this.empresa = empresa;
        this.medicoId = medicoId;
        this.medico = medico;
        this.pacienteId = pacienteId;
        this.paciente = paciente;
        this.receitaId = receitaId;
        this.receita = receita;
    }
}
