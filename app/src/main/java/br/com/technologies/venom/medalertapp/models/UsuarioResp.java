package br.com.technologies.venom.medalertapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Ignore;

public class UsuarioResp implements Parcelable {
    private boolean sucesso;
    private String mensagem;
    private String token;

    public boolean isSucesso() {
        return sucesso;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UsuarioResp() {
    }

    @Ignore
    public UsuarioResp(boolean sucesso, String mensagem, String token) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
        this.token = token;
    }

    @Ignore
    public UsuarioResp(boolean sucesso, String mensagem) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.sucesso ? (byte) 1 : (byte) 0);
        dest.writeString(this.mensagem);
        dest.writeString(this.token);
    }

    protected UsuarioResp(Parcel in) {
        this.sucesso = in.readByte() != 0;
        this.mensagem = in.readString();
        this.token = in.readString();
    }

    public static final Parcelable.Creator<UsuarioResp> CREATOR = new Parcelable.Creator<UsuarioResp>() {
        @Override
        public UsuarioResp createFromParcel(Parcel source) {
            return new UsuarioResp(source);
        }

        @Override
        public UsuarioResp[] newArray(int size) {
            return new UsuarioResp[size];
        }
    };
}
