package com.example.inmobiliaria.contratos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.inmobiliaria.R;
import com.example.inmobiliaria.modelo.Inmueble;
import com.example.inmobiliaria.modelo.Propietario;
import com.example.inmobiliaria.request.ApiClient;

import java.io.IOException;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratosViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Inmueble>> inmuebles;
    private Context context;
    public ContratosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();

    }

    public LiveData<ArrayList<Inmueble>> getInmuebles() {
        if (inmuebles == null) {
            inmuebles = new MutableLiveData<>();
        }
        return inmuebles;
    }

    public void cargarInmueblesConContrato() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("datos", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        Call<ArrayList<Inmueble>> callInmuebles = ApiClient.getMyApiClient().getInmueblesVigentes(token);
        callInmuebles.enqueue(new Callback<ArrayList<Inmueble>>() {
            @Override
            public void onResponse(Call<ArrayList<Inmueble>> call, Response<ArrayList<Inmueble>> response) {
                if(response.isSuccessful()) {
                    Log.d("Salida", response.body().get(0).getId()+"");
                    inmuebles.postValue(response.body());
                }
                else {
                    try {
                        Toast.makeText(context, response.errorBody().string(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Inmueble>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        //Acá traemos los inmuebles que tienen contratos vigentes en la base de datos
      /*  ArrayList<Inmueble> inmuebles = new ArrayList<> ();
        inmuebles.add(new Inmueble(1, "Rivadavia 123", "Residencial", "Vivienda", 4, 20000, 1, propietario, true, "hola"));
        inmuebles.add(new Inmueble(2, "Colón 456", "Comercial", "Departamento", 2, 10000, 2, propietario, true, "hola"));
        inmuebles.add(new Inmueble(3, "Las heras 789", "Residencial", "Vivienda", 6, 30000, 3, propietario, true, "hola"));
        this.inmuebles.setValue(inmuebles);*/
    }
}
