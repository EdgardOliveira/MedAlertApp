package br.com.technologies.venom.medalertapp.models;

import br.com.technologies.venom.medalertapp.models.Consulta;
import br.com.technologies.venom.medalertapp.models.Empresa;

public class DadosReceita {
    private Empresa empresa;
    private Consulta consulta;

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public DadosReceita() {
    }

    public DadosReceita(Empresa empresa, Consulta consulta) {
        this.empresa = empresa;
        this.consulta = consulta;
    }
}
