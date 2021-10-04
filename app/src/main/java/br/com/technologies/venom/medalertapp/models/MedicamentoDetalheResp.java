package br.com.technologies.venom.medalertapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Ignore;

public class MedicamentoDetalheResp implements Parcelable {
    private Boolean sucesso;
    private String mensagem;
    private MedicamentoDetalhe remedio;

    public Boolean getSucesso() {
        return sucesso;
    }

    public void setSucesso(Boolean sucesso) {
        this.sucesso = sucesso;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public MedicamentoDetalhe getMedicamentoDetalhe() {
        return remedio;
    }

    public void setMedicamentoDetalhe(MedicamentoDetalhe remedio) {
        this.remedio = remedio;
    }

    public MedicamentoDetalheResp() {
    }

    @Ignore
    public MedicamentoDetalheResp(Boolean sucesso, String mensagem, MedicamentoDetalhe remedio) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
        this.remedio = remedio;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.sucesso);
        dest.writeString(this.mensagem);
        dest.writeParcelable(this.remedio, flags);
    }

    protected MedicamentoDetalheResp(Parcel in) {
        this.sucesso = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mensagem = in.readString();
        this.remedio = in.readParcelable(MedicamentoDetalhe.class.getClassLoader());
    }

    public static final Creator<MedicamentoDetalheResp> CREATOR = new Creator<MedicamentoDetalheResp>() {
        @Override
        public MedicamentoDetalheResp createFromParcel(Parcel source) {
            return new MedicamentoDetalheResp(source);
        }

        @Override
        public MedicamentoDetalheResp[] newArray(int size) {
            return new MedicamentoDetalheResp[size];
        }
    };
}
