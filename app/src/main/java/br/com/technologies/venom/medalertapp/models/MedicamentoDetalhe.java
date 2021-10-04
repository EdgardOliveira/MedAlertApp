package br.com.technologies.venom.medalertapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class MedicamentoDetalhe implements Parcelable {
    private String gtin;
    private String nome;
    private String laboratorio;
    private String imagemCaixa;
    private String imagemCodigoBarras;
    private Double precoMin;
    private Double precoMed;
    private Double precoMax;
    private Integer idProduto;
    private String registro;
    private String bula;
    private String criacao;
    private String modificacao;
    private String status;

    public String getGtin() {
        return gtin;
    }

    public void setGtin(String gtin) {
        this.gtin = gtin;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getImagemCaixa() {
        return imagemCaixa;
    }

    public void setImagemCaixa(String imagemCaixa) {
        this.imagemCaixa = imagemCaixa;
    }

    public String getImagemCodigoBarras() {
        return imagemCodigoBarras;
    }

    public void setImagemCodigoBarras(String imagemCodigoBarras) {
        this.imagemCodigoBarras = imagemCodigoBarras;
    }

    public Double getPrecoMin() {
        return precoMin;
    }

    public void setPrecoMin(Double precoMin) {
        this.precoMin = precoMin;
    }

    public Double getPrecoMed() {
        return precoMed;
    }

    public void setPrecoMed(Double precoMed) {
        this.precoMed = precoMed;
    }

    public Double getPrecoMax() {
        return precoMax;
    }

    public void setPrecoMax(Double precoMax) {
        this.precoMax = precoMax;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getBula() {
        return bula;
    }

    public void setBula(String bula) {
        this.bula = bula;
    }

    public String getCriacao() {
        return criacao;
    }

    public void setCriacao(String criacao) {
        this.criacao = criacao;
    }

    public String getModificacao() {
        return modificacao;
    }

    public void setModificacao(String modificacao) {
        this.modificacao = modificacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MedicamentoDetalhe() {
    }

    public MedicamentoDetalhe(String gtin, String nome, String laboratorio, String imagemCaixa,
                              String imagemCodigoBarras, Double precoMin, Double precoMed,
                              Double precoMax, Integer idProduto, String registro, String bula,
                              String criacao, String modificacao, String status) {
        this.gtin = gtin;
        this.nome = nome;
        this.laboratorio = laboratorio;
        this.imagemCaixa = imagemCaixa;
        this.imagemCodigoBarras = imagemCodigoBarras;
        this.precoMin = precoMin;
        this.precoMed = precoMed;
        this.precoMax = precoMax;
        this.idProduto = idProduto;
        this.registro = registro;
        this.bula = bula;
        this.criacao = criacao;
        this.modificacao = modificacao;
        this.status = status;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.gtin);
        dest.writeString(this.nome);
        dest.writeString(this.laboratorio);
        dest.writeString(this.imagemCaixa);
        dest.writeString(this.imagemCodigoBarras);
        dest.writeValue(this.precoMin);
        dest.writeValue(this.precoMed);
        dest.writeValue(this.precoMax);
        dest.writeValue(this.idProduto);
        dest.writeString(this.registro);
        dest.writeString(this.bula);
        dest.writeString(this.criacao);
        dest.writeString(this.modificacao);
        dest.writeString(this.status);
    }

    protected MedicamentoDetalhe(Parcel in) {
        this.gtin = in.readString();
        this.nome = in.readString();
        this.laboratorio = in.readString();
        this.imagemCaixa = in.readString();
        this.imagemCodigoBarras = in.readString();
        this.precoMin = (Double) in.readValue(Double.class.getClassLoader());
        this.precoMed = (Double) in.readValue(Double.class.getClassLoader());
        this.precoMax = (Double) in.readValue(Double.class.getClassLoader());
        this.idProduto = (Integer) in.readValue(Integer.class.getClassLoader());
        this.registro = in.readString();
        this.bula = in.readString();
        this.criacao = in.readString();
        this.modificacao = in.readString();
        this.status = in.readString();
    }

    public static final Parcelable.Creator<MedicamentoDetalhe> CREATOR = new Parcelable.Creator<MedicamentoDetalhe>() {
        @Override
        public MedicamentoDetalhe createFromParcel(Parcel source) {
            return new MedicamentoDetalhe(source);
        }

        @Override
        public MedicamentoDetalhe[] newArray(int size) {
            return new MedicamentoDetalhe[size];
        }
    };
}
