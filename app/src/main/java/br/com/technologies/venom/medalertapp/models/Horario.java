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
                @Index("data"),
                @Index("hora"),
                @Index("status")
        }
)
public class Horario {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String medicamentoId;
    private String data;
    private String hora;
    private String status;

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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Ignore
    public Horario() {
    }

    public Horario(String medicamentoId, String data, String hora, String status) {
        this.medicamentoId = medicamentoId;
        this.data = data;
        this.hora = hora;
        this.status = status;
    }
}
