package com.example.inmobiliaria.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.inmobiliaria.modelo.Propietario;
import com.example.inmobiliaria.request.ApiClient;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {

    private MutableLiveData<Propietario> mutablePropietario;
    private ApiClient apiClient;
    private Context context;
    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }
    public LiveData<Propietario> getPropietario() {
        if (mutablePropietario == null) {
            mutablePropietario = new MutableLiveData<>();
        }
        return mutablePropietario;
    }

    public void cargarPropietario(boolean login) {
        //Log.d("Salida:", String.valueOf(login));
        //ApiClient.MyApiInterface myApiInterface = apiClient.getMyApiClient();
        SharedPreferences sharedPreferences = context.getSharedPreferences("datos", context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        if(login) {
            Call<Propietario> callPropietario = ApiClient.getMyApiClient().getPropietario(token);
            callPropietario.enqueue(new Callback<Propietario>() {
                @Override
                public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                    if(response.isSuccessful()) {
                        SharedPreferences sharedPreferences = context.getSharedPreferences("datos", context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Propietario bodyPropietario = response.body();
                        editor.putInt("id", bodyPropietario.getId());
                        editor.putString("nombre", bodyPropietario.getNombre());
                        editor.putString("apellido", bodyPropietario.getApellido());
                        editor.putString("dni", bodyPropietario.getDni());
                        editor.putString("email", bodyPropietario.getEmail());
                        editor.putString("telefono", bodyPropietario.getTelefono());
                        editor.commit();
                        mutablePropietario.setValue(bodyPropietario);
                    }
                }
                @Override
                public void onFailure(Call<Propietario> call, Throwable t) {
                    Toast.makeText(context, "No fue posible cargar el propietario", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Propietario propietario = new Propietario(sharedPreferences.getInt("id", -1),
                    sharedPreferences.getString("dni", "-"),
                    sharedPreferences.getString("nombre", "-"),
                    sharedPreferences.getString("apellido", "-"),
                    sharedPreferences.getString("email", "-"),
                    sharedPreferences.getString("contraseña", "-"),
                    sharedPreferences.getString("telefono", "-"));
            mutablePropietario.setValue(propietario);
        }
    }
    public void editarPropietario(Propietario propietario) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("datos", context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        Call<Propietario> callPropietario = ApiClient.getMyApiClient().putPropietario(token, propietario);
        callPropietario.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences("datos", context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    Propietario bodyPropietario = response.body();
                    editor.putInt("id", bodyPropietario.getId());
                    editor.putString("nombre", bodyPropietario.getNombre());
                    editor.putString("apellido", bodyPropietario.getApellido());
                    editor.putString("dni", bodyPropietario.getDni());
                    editor.putString("email", bodyPropietario.getEmail());
                    editor.putString("contraseña", bodyPropietario.getContraseña());
                    editor.putString("telefono", bodyPropietario.getTelefono());
                    editor.commit();
                    mutablePropietario.setValue(bodyPropietario);
                    Toast.makeText(context, "Perfil actualizado", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Toast.makeText(context, "Error al editar perfil", Toast.LENGTH_LONG).show();
            }
        });

    }
}