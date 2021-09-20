package br.com.technologies.venom.medalertapp.models;

import java.util.List;

public class Consultas {
    private List<Consulta> consultas = null;

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    public Consultas() {
    }

    public Consultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }
}
