package com.example.inmobiliaria.request;

import com.example.inmobiliaria.Configuracion;
import com.example.inmobiliaria.modelo.Contrato;
import com.example.inmobiliaria.modelo.Inmueble;
import com.example.inmobiliaria.modelo.Inquilino;
import com.example.inmobiliaria.modelo.Pago;
import com.example.inmobiliaria.modelo.Propietario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public class ApiClient {

    private static final String PATH = Configuracion.getRuta() + "api/";
    private static MyApiInterface myApiInterface;

    public static MyApiInterface getMyApiClient() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PATH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        myApiInterface = retrofit.create(MyApiInterface.class);

        return myApiInterface;
    }

    public interface MyApiInterface {
        @FormUrlEncoded
        @POST("Propietarios/login")
        Call<String> Login(@Field("usuario") String usuario, @Field("contraseña") String contraseña);
        @GET("Propietarios/")
        Call<Propietario> getPropietario(@Header("Authorization") String token);
        @PUT("Propietarios/")
        Call<Propietario> putPropietario(@Header("Authorization") String token, @Body Propietario porpietario);
        @GET("Inmuebles")
        Call<ArrayList<Inmueble>> getInmuebles(@Header("Authorization") String token);
        @GET("Inmuebles/Vigentes")
        Call<ArrayList<Inmueble>> getInmueblesVigentes(@Header("Authorization") String token);
        @GET("Inquilinos/{id}") // Id del inmueble
        Call<Inquilino> getInquilino(@Header("Authorization") String token, @Path("id") int id);
        @GET("Contratos/{id}") // Id del inmueble
        Call<Contrato> getContrato(@Header("Authorization") String token, @Path("id") int id);
        @GET("Pagos/{id}") //Id del inmueble
        Call<ArrayList<Pago>> getPagos(@Header("Authorization") String token, @Path("id") int id);
        @PUT("Inmuebles")
        Call<Inmueble> putInmueble(@Header("Authentication") String token, @Body Inmueble inmueble);

    }



}