package br.com.technologies.venom.medalertapp.models;

import androidx.room.Ignore;

public class DiaHora {
    private String dia;
    private String hora;

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Ignore
    public DiaHora() {
    }

    public DiaHora(String dia, String hora) {
        this.dia = dia;
        this.hora = hora;
    }
}
