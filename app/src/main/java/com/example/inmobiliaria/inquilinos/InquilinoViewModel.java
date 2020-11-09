package com.example.inmobiliaria.inquilinos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.inmobiliaria.modelo.Contrato;
import com.example.inmobiliaria.modelo.Inmueble;
import com.example.inmobiliaria.modelo.Inquilino;
import com.example.inmobiliaria.modelo.Pago;
import com.example.inmobiliaria.request.ApiClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinoViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<Inquilino> inquilino;
    private Context context;

    public InquilinoViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<Inquilino> getInquilino() {
        if (inquilino == null) {
            inquilino = new MutableLiveData<>();
        }
        return inquilino;
    }

    public void cargarInquilino(Bundle bundle) {
        //Acá recibiríamos un inmueble o Id de inmueble, y buscaríamos en la base de datos el contrato vigente de ese inmueble y su inquilino
        //En caso de no existir, la vista mostraría un mensaje (Si partimos de la pestaña de contratos se supone que hay un inquilino
        Inmueble inmueble = (Inmueble) bundle.get("inmueble");
        SharedPreferences sharedPreferences = context.getSharedPreferences("datos", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        Call<Inquilino> callInquilino = ApiClient.getMyApiClient().getInquilino(token, inmueble.getId());
        callInquilino.enqueue(new Callback<Inquilino>() {
            @Override
            public void onResponse(Call<Inquilino> call, Response<Inquilino> response) {
                if(response.isSuccessful()) {
                    inquilino.postValue(response.body());
                } else {
                    try {
                        Toast.makeText(context, response.errorBody().string(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<Inquilino> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        /*Inquilino inquilino = new Inquilino();
        switch (inmueble.getId()) {
            case 1:
            {
                inquilino = new Inquilino(1, "38752605", "Juan Ignacio", "Balaguer Gasull", "ULP", "balaguerjuani@gmail.com", "2664403891", "Un amigo", "Telefono del amigo");
                break;
            }
            case 2:
            {
                inquilino = new Inquilino(2, "12345678", "Pedro", "Perez", "Work", "pedro@gmail.com", "2664121230", "Hermano", "Telefono del hermano");
                break;
            }
            case 3:
            {
                inquilino = new Inquilino(3, "91234567", "Carlos", "Gonzales", "McDonalds", "carlitos10@gmail.com", "2664101010", "Esposa", "Telefono la esposa");
                break;
            }
        }

        this.inquilino.setValue(inquilino);
    }*/
    }
}