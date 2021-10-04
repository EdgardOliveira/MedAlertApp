package br.com.technologies.venom.medalertapp.models;

import androidx.annotation.NonNull;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

public class Convenio {
    @PrimaryKey
    @NonNull
    private String id;
    private String codigoIdentificacao;
    private String produto;
    private String plano;
    private String cobertura;
    private String acomodacao;
    private String cns;
    private String empresa;
    private Date validade;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigoIdentificacao() {
        return codigoIdentificacao;
    }

    public void setCodigoIdentificacao(String codigoIdentificacao) {
        this.codigoIdentificacao = codigoIdentificacao;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getPlano() {
        return plano;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }

    public String getCobertura() {
        return cobertura;
    }

    public void setCobertura(String cobertura) {
        this.cobertura = cobertura;
    }

    public String getAcomodacao() {
        return acomodacao;
    }

    public void setAcomodacao(String acomodacao) {
        this.acomodacao = acomodacao;
    }

    public String getCns() {
        return cns;
    }

    public void setCns(String cns) {
        this.cns = cns;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public Convenio() {
    }

    @Ignore
    public Convenio(String id, String codigoIdentificacao, String produto, String plano, String cobertura, String acomodacao, String cns, String empresa, Date validade) {
        this.id = id;
        this.codigoIdentificacao = codigoIdentificacao;
        this.produto = produto;
        this.plano = plano;
        this.cobertura = cobertura;
        this.acomodacao = acomodacao;
        this.cns = cns;
        this.empresa = empresa;
        this.validade = validade;
    }
}
