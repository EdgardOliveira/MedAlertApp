package br.com.technologies.venom.medalertapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Ignore;

public class Usuario implements Parcelable {
    private String email;
    private String senha;
    private String token;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Usuario() {
    }

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    @Ignore
    public Usuario(String email, String senha, String token) {
        this.email = email;
        this.senha = senha;
        this.token = token;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.email);
        dest.writeString(this.senha);
        dest.writeString(this.token);
    }

    protected Usuario(Parcel in) {
        this.email = in.readString();
        this.senha = in.readString();
        this.token = in.readString();
    }

    public static final Parcelable.Creator<Usuario> CREATOR = new Parcelable.Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel source) {
            return new Usuario(source);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };
}
