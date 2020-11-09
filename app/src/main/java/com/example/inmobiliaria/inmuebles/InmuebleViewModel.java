package com.example.inmobiliaria.inmuebles;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.inmobiliaria.modelo.Inmueble;
import com.example.inmobiliaria.request.ApiClient;

import java.io.IOException;
import java.util.ArrayList;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmuebleViewModel extends AndroidViewModel {
    private MutableLiveData<Inmueble> inmueble;
    Context context;
    public InmuebleViewModel(Application application)
    {
        super(application);
        this.context = application.getApplicationContext();
    }
    public LiveData<Inmueble> getInmueble() {
        if (inmueble == null) {
            inmueble = new MutableLiveData<>();
        }
        return inmueble;
    }

    public void cargarInmueble(Bundle bundle) {
            Inmueble inmueble = (Inmueble) bundle.getSerializable("inmueble");
            this.inmueble.setValue(inmueble);

        }
    public void cambiarDisponibilidad() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("datos", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        Inmueble inmuebleEditado = inmueble.getValue();
        inmuebleEditado.setEstado(!inmuebleEditado.isEstado());
        Call<Inmueble> callInmueble = ApiClient.getMyApiClient().putInmueble(token, inmuebleEditado);
        callInmueble.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                Toast.makeText(context, "Disponibilidad editada", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    }
