package br.com.technologies.venom.medalertapp.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.sql.Time;
import java.util.Date;

@Entity(
        tableName = "horarios",
        indices = {
                @Index("medicamentoId"),
                @Index("dataHora"),
                @Index("status")
        }
)
public class Horario {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String medicamentoId;
    private Date dataHora;
    private int status;

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

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Ignore
    public Horario() {

    }

    public Horario(String medicamentoId, Date dataHora, int status) {
        this.medicamentoId = medicamentoId;
        this.dataHora = dataHora;
        this.status = status;
    }
}
