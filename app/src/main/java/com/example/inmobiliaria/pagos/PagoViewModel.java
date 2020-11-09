package com.example.inmobiliaria.pagos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.inmobiliaria.modelo.Contrato;
import com.example.inmobiliaria.modelo.Inmueble;
import com.example.inmobiliaria.modelo.Inquilino;
import com.example.inmobiliaria.modelo.Pago;
import com.example.inmobiliaria.request.ApiClient;

import java.io.IOException;
import java.lang.reflect.Array;
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

public class PagoViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<ArrayList<Pago>> pagos;
    private Context context;
    public PagoViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<ArrayList<Pago>> getPagos () {
        if (pagos == null) {
            pagos = new MutableLiveData<>();
        }
        return pagos;
    }
    public void cargarPagos(Bundle bundle) {
        //Acá recibiríamos un inmueble o Id de inmueble, y buscaríamos en la base de datos el contrato vigente de ese inmueble y sus pagos
        //En caso de no existir, la vista mostraría un mensaje (Si partimos de la pestaña de contratos se supone que el inmueble tiene un contrato y pagos vigente)
        Inmueble inmueble = (Inmueble) bundle.get("inmueble");
        SharedPreferences sharedPreferences = context.getSharedPreferences("datos", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        Call<ArrayList<Pago>> callPagos = ApiClient.getMyApiClient().getPagos(token, inmueble.getId());
        callPagos.enqueue(new Callback<ArrayList<Pago>>() {
            @Override
            public void onResponse(Call<ArrayList<Pago>> call, Response<ArrayList<Pago>> response) {
                if(response.isSuccessful()) {
                    pagos.postValue(response.body());
                } else {
                    try {
                        Toast.makeText(context, response.errorBody().string(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Pago>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        /*
        ArrayList<Pago> pagos = new ArrayList<>();
        switch (inmueble.getId()) {
            case 1:
            {
                Calendar fecha1 = Calendar.getInstance();
                fecha1.set(Calendar.YEAR, 2020);
                fecha1.set(Calendar.MONTH, Calendar.JANUARY);
                fecha1.set(Calendar.DAY_OF_MONTH, 1);
                Calendar fecha2 = Calendar.getInstance();
                fecha2.set(Calendar.YEAR, 2020);
                fecha2.set(Calendar.MONTH, Calendar.FEBRUARY);
                fecha2.set(Calendar.DAY_OF_MONTH, 1);
                Calendar fecha3 = Calendar.getInstance();
                fecha3.set(Calendar.YEAR, 2020);
                fecha3.set(Calendar.MONTH, Calendar.MARCH);
                fecha3.set(Calendar.DAY_OF_MONTH, 1);

                pagos.add(new Pago(1, 1, 1, null, 20000, fecha1.getTime()));
                pagos.add(new Pago(2, 2, 1, null, 20000, fecha2.getTime()));
                pagos.add(new Pago(3, 3, 1, null, 20000, fecha3.getTime()));
                break;
            }
            case 2:
            {
                pagos = new ArrayList<>();
                Calendar fecha1 = Calendar.getInstance();
                fecha1.set(Calendar.YEAR, 2020);
                fecha1.set(Calendar.MONTH, Calendar.MAY);
                fecha1.set(Calendar.DAY_OF_MONTH, 18);
                Calendar fecha2 = Calendar.getInstance();
                fecha2.set(Calendar.YEAR, 2020);
                fecha2.set(Calendar.MONTH, Calendar.JUNE);
                fecha2.set(Calendar.DAY_OF_MONTH, 18);
                Calendar fecha3 = Calendar.getInstance();
                fecha3.set(Calendar.YEAR, 2020);
                fecha3.set(Calendar.MONTH, Calendar.JULY);
                fecha3.set(Calendar.DAY_OF_MONTH, 18);

                pagos.add(new Pago(4, 1, 2, null, 10000, fecha1.getTime()));
                pagos.add(new Pago(5, 2, 2, null, 10000, fecha2.getTime()));
                pagos.add(new Pago(6, 3, 2, null, 10000, fecha3.getTime()));
                break;
            }
            case 3:
            {
                pagos = new ArrayList<>();
                Calendar fecha1 = Calendar.getInstance();
                fecha1.set(Calendar.YEAR, 2020);
                fecha1.set(Calendar.MONTH, Calendar.AUGUST);
                fecha1.set(Calendar.DAY_OF_MONTH, 3);
                Calendar fecha2 = Calendar.getInstance();
                fecha2.set(Calendar.YEAR, 2020);
                fecha2.set(Calendar.MONTH, Calendar.SEPTEMBER);
                fecha2.set(Calendar.DAY_OF_MONTH, 3);
                Calendar fecha3 = Calendar.getInstance();
                fecha3.set(Calendar.YEAR, 2020);
                fecha3.set(Calendar.MONTH, Calendar.NOVEMBER);
                fecha3.set(Calendar.DAY_OF_MONTH, 3);

                pagos.add(new Pago(7, 1, 3, null, 30000, fecha1.getTime()));
                pagos.add(new Pago(8, 2, 3, null, 30000, fecha2.getTime()));
                pagos.add(new Pago(9, 3, 3, null, 30000, fecha3.getTime()));
                break;
            }

        }

        this.pagos.setValue(pagos);*/
    }

}