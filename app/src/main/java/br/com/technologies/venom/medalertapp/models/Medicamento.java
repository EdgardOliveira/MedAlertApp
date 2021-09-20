package br.com.technologies.venom.medalertapp.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "medicamentos",
        indices = {
                @Index("uso"),
                @Index("tratamento"),
                @Index("formula")
        }
)
public class Medicamento {
    @PrimaryKey
    private Integer id;
    private String uso;
    private String tratamento;
    private String formula;
    private String dosagem;
    private String concentracao;
    private Integer quantidade;
    private Integer dias;
    private Integer frequenciaH;
    private String orientacoes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public String getTratamento() {
        return tratamento;
    }

    public void setTratamento(String tratamento) {
        this.tratamento = tratamento;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public String getConcentracao() {
        return concentracao;
    }

    public void setConcentracao(String concentracao) {
        this.concentracao = concentracao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public Integer getFrequenciaH() {
        return frequenciaH;
    }

    public void setFrequenciaH(Integer frequenciaH) {
        this.frequenciaH = frequenciaH;
    }

    public String getOrientacoes() {
        return orientacoes;
    }

    public void setOrientacoes(String orientacoes) {
        this.orientacoes = orientacoes;
    }

    public Medicamento() {
    }

    @Ignore
    public Medicamento(Integer id, String uso, String tratamento, String formula, String dosagem,
                       String concentracao, Integer quantidade, Integer dias, Integer frequenciaH,
                       String orientacoes) {
        this.id = id;
        this.uso = uso;
        this.tratamento = tratamento;
        this.formula = formula;
        this.dosagem = dosagem;
        this.concentracao = concentracao;
        this.quantidade = quantidade;
        this.dias = dias;
        this.frequenciaH = frequenciaH;
        this.orientacoes = orientacoes;
    }
}
