package com.example.inmobiliaria.contratos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.inmobiliaria.modelo.Contrato;
import com.example.inmobiliaria.modelo.Inmueble;
import com.example.inmobiliaria.modelo.Inquilino;
import com.example.inmobiliaria.request.ApiClient;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratoViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<Contrato> contrato;
    private Context context;
    public ContratoViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<Contrato> getContrato() {
        if (contrato == null) {
            contrato = new MutableLiveData<>();
        }
        return contrato;
    }

    public void cargarContrato(Bundle bundle) {
        //Acá recibiríamos un inmueble o Id de inmueble, y buscaríamos en la base de datos el contrato vigente de ese inmueble
        //En caso de no existir, la vista mostraría un mensaje (Si partimos de la pestaña de contratos se supone que el inmueble tiene un contrato vigente)
        Inmueble inmueble = (Inmueble) bundle.get("inmueble");
        Log.d("IdInmueble", inmueble.getId()+"");
        SharedPreferences sharedPreferences = context.getSharedPreferences("datos", 0);
        String token = sharedPreferences.getString("token", "");
        Log.d("Token", token);
        Call<Contrato> callContrato = ApiClient.getMyApiClient().getContrato(token, inmueble.getId());
        callContrato.enqueue(new Callback<Contrato>() {
            @Override
            public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                Log.d("Response", response.toString());
                if(response.isSuccessful()) {
                    contrato.postValue(response.body());
                } else {
                    try {
                        Toast.makeText(context, response.errorBody().string(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Contrato> call, Throwable t) {
                Toast.makeText(context, t.getCause().toString(), Toast.LENGTH_LONG).show();
            }
        });
        /*Contrato contrato = new Contrato();
        switch (inmueble.getId()) {
            case 1:
            {
                Calendar inicio = Calendar.getInstance();
                inicio.set(Calendar.YEAR, 2020);
                inicio.set(Calendar.MONTH, Calendar.MAY);
                inicio.set(Calendar.DAY_OF_MONTH, 18);
                Calendar fin = Calendar.getInstance();
                fin.set(Calendar.YEAR, 2020);
                fin.set(Calendar.MONTH, Calendar.DECEMBER);
                fin.set(Calendar.DAY_OF_MONTH, 26);
                Inquilino inquilino = new Inquilino();
                inquilino.setNombre("Juan Ignacio");
                inquilino.setApellido("Balaguer Gasull");
                contrato = new Contrato(1, inicio.getTime(), fin.getTime(), inmueble.getPrecio(), 1, inmueble.getId(), inquilino, inmueble);
                break;
            }
            case 2:
             {
                Calendar inicio = Calendar.getInstance();
                inicio.set(Calendar.YEAR, 2019);
                inicio.set(Calendar.MONTH, Calendar.JUNE);
                inicio.set(Calendar.DAY_OF_MONTH, 28);
                Calendar fin = Calendar.getInstance();
                fin.set(Calendar.YEAR, 2020);
                fin.set(Calendar.MONTH, Calendar.JANUARY);
                fin.set(Calendar.DAY_OF_MONTH, 25);
                 Inquilino inquilino = new Inquilino();
                 inquilino.setNombre("Pedro");
                 inquilino.setApellido("Perez");
                contrato = new Contrato(1, inicio.getTime(), fin.getTime(), inmueble.getPrecio(), 1, inmueble.getId(), inquilino, inmueble);
                break;
            }
            case 3:
            {
                Calendar inicio = Calendar.getInstance();
                inicio.set(Calendar.YEAR, 2020);
                inicio.set(Calendar.MONTH, Calendar.DECEMBER);
                inicio.set(Calendar.DAY_OF_MONTH, 1);
                Calendar fin = Calendar.getInstance();
                fin.set(Calendar.YEAR, 2021);
                fin.set(Calendar.MONTH, Calendar.MAY);
                fin.set(Calendar.DAY_OF_MONTH, 5);
                Inquilino inquilino = new Inquilino();
                inquilino.setNombre("Carlos");
                inquilino.setApellido("Gonzales");
                contrato = new Contrato(1, inicio.getTime(), fin.getTime(), inmueble.getPrecio(), 1, inmueble.getId(), inquilino, inmueble);
                break;
            }
        }

        this.contrato.setValue(contrato);*/
    }

}