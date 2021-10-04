package br.com.technologies.venom.medalertapp.services;

import br.com.technologies.venom.medalertapp.models.ConsultasResp;
import br.com.technologies.venom.medalertapp.models.MedicamentoDetalheResp;
import br.com.technologies.venom.medalertapp.models.Usuario;
import br.com.technologies.venom.medalertapp.models.UsuarioResp;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RESTService {

    //Fazer login na API passando login e senha no corpo
    @Headers({"Content-Type:application/json; charset=utf-8"})
    @POST("autenticacao/login")
    Call<UsuarioResp> fazerLogin(@Body Usuario usuario);

    //Obter as consultas do paciente
    @Headers({"Content-Type:application/json; charset=utf-8"})
    @GET("consultas")
    Call<ConsultasResp> obterConsultas(@Header("Authorization") String token);


    //Consultar um medicamento específico
    @Headers({"Content-Type:application/json; charset=utf-8"})
    @GET("remedios/{codigo}")
    Call<MedicamentoDetalheResp> consultarMedicamento(
            @Header("Authorization") String token,
            @Path("codigo") String gtin
    );
}
