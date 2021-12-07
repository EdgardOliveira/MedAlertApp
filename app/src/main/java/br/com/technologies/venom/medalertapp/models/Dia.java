package br.com.technologies.venom.medalertapp.models;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "horarios_medicamentos",
        indices = {
                @Index("data"),
                @Index("medicamentoId"),
                @Index("hora01Status"),
                @Index("hora02Status"),
                @Index("hora03Status"),
                @Index("hora04Status"),
                @Index("hora05Status"),
                @Index("hora06Status")
        }
)
public class Dia {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String medicamentoId;
    private String data;
    private String hora01;
    private String hora01Status;
    private String hora02;
    private String hora02Status;
    private String hora03;
    private String hora03Status;
    private String hora04;
    private String hora04Status;
    private String hora05;
    private String hora05Status;
    private String hora06;
    private String hora06Status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMedicamentoId() {
        return medicamentoId;
    }

    public void setMedicamentoId(String medicamentoId) {
        this.medicamentoId = medicamentoId;
    }

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

    public String getHora01Status() {
        return hora01Status;
    }

    public void setHora01Status(String hora01Status) {
        this.hora01Status = hora01Status;
    }

    public String getHora02() {
        return hora02;
    }

    public void setHora02(String hora02) {
        this.hora02 = hora02;
    }

    public String getHora02Status() {
        return hora02Status;
    }

    public void setHora02Status(String hora02Status) {
        this.hora02Status = hora02Status;
    }

    public String getHora03() {
        return hora03;
    }

    public void setHora03(String hora03) {
        this.hora03 = hora03;
    }

    public String getHora03Status() {
        return hora03Status;
    }

    public void setHora03Status(String hora03Status) {
        this.hora03Status = hora03Status;
    }

    public String getHora04() {
        return hora04;
    }

    public void setHora04(String hora04) {
        this.hora04 = hora04;
    }

    public String getHora04Status() {
        return hora04Status;
    }

    public void setHora04Status(String hora04Status) {
        this.hora04Status = hora04Status;
    }

    public String getHora05() {
        return hora05;
    }

    public void setHora05(String hora05) {
        this.hora05 = hora05;
    }

    public String getHora05Status() {
        return hora05Status;
    }

    public void setHora05Status(String hora05Status) {
        this.hora05Status = hora05Status;
    }

    public String getHora06() {
        return hora06;
    }

    public void setHora06(String hora06) {
        this.hora06 = hora06;
    }

    public String getHora06Status() {
        return hora06Status;
    }

    public void setHora06Status(String hora06Status) {
        this.hora06Status = hora06Status;
    }

    @Ignore
    public Dia() {
    }

    public Dia(String medicamentoId, String data, String hora01, String hora01Status, String hora02, String hora02Status, String hora03, String hora03Status, String hora04, String hora04Status, String hora05, String hora05Status, String hora06, String hora06Status) {
        this.medicamentoId = medicamentoId;
        this.data = data;
        this.hora01 = hora01;
        this.hora01Status = hora01Status;
        this.hora02 = hora02;
        this.hora02Status = hora02Status;
        this.hora03 = hora03;
        this.hora03Status = hora03Status;
        this.hora04 = hora04;
        this.hora04Status = hora04Status;
        this.hora05 = hora05;
        this.hora05Status = hora05Status;
        this.hora06 = hora06;
        this.hora06Status = hora06Status;
    }
}
