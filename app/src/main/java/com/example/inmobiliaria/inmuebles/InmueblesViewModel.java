package com.example.inmobiliaria.inmuebles;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.inmobiliaria.R;
import com.example.inmobiliaria.modelo.Inmueble;
import com.example.inmobiliaria.request.ApiClient;

import java.io.IOException;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmueblesViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Inmueble>> inmuebles;
    private Context context;
    public InmueblesViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();

    }

    public LiveData<ArrayList<Inmueble>> getInmuebles() {
        if (inmuebles == null) {
            inmuebles = new MutableLiveData<>();
        }
        return inmuebles;
    }

    public void cargarInmuebles() {
        //Acá traemos todos los inmuebles de la base de datos
        SharedPreferences sharedPreferences = context.getSharedPreferences("datos", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        Call<ArrayList<Inmueble>> callInmuebles = ApiClient.getMyApiClient().getInmuebles(token);
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

    }
    public void cargarInmueblesConContrato() {

    /*    ArrayList<Inmueble> inmuebles = new ArrayList<> ();
        inmuebles.add(new Inmueble(1, "Rivadavia 123", "Residencial", "Vivienda", 4, 20000, null, true, R.drawable.casa1));
        inmuebles.add(new Inmueble(2, "Colón 456", "Comercial", "Departamento", 2, 10000, null, true, R.drawable.casa2));
        inmuebles.add(new Inmueble(3, "Las heras 789", "Residencial", "Vivienda", 6, 30000, null, true, R.drawable.casa3));
        this.inmuebles.setValue(inmuebles);*/

    }
}