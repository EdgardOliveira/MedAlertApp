package br.com.technologies.venom.medalertapp.models;

import androidx.room.Ignore;

import java.sql.Time;
import java.util.Date;

public class Dia {
    private String data;
    private String hora01;
    private String hora02;
    private String hora03;
    private String hora04;
    private String hora05;
    private String hora06;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora01() {
        return hora01;
    }

    public void setHora01(String hora01) {
        this.hora01 = hora01;
    }

    public String getHora02() {
        return hora02;
    }

    public void setHora02(String hora02) {
        this.hora02 = hora02;
    }

    public String getHora03() {
        return hora03;
    }

    public void setHora03(String hora03) {
        this.hora03 = hora03;
    }

    public String getHora04() {
        return hora04;
    }

    public void setHora04(String hora04) {
        this.hora04 = hora04;
    }

    public String getHora05() {
        return hora05;
    }

    public void setHora05(String hora05) {
        this.hora05 = hora05;
    }

    public String getHora06() {
        return hora06;
    }

    public void setHora06(String hora06) {
        this.hora06 = hora06;
    }

    @Ignore
    public Dia(){

    }

    public Dia(String data, String hora01, String hora02, String hora03, String hora04, String hora05, String hora06) {
        this.data = data;
        this.hora01 = hora01;
        this.hora02 = hora02;
        this.hora03 = hora03;
        this.hora04 = hora04;
        this.hora05 = hora05;
        this.hora06 = hora06;
    }
}
