package br.com.technologies.venom.medalertapp.services;

import br.com.technologies.venom.medalertapp.models.ConsultaWrapper;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RESTService {

    //Consultar receitas de um paciente específico (passando o código dele)
    @GET("receitas/{codigo}")
    Call<ConsultaWrapper> consultarReceita(@Path("codigo") String codigo);
}
