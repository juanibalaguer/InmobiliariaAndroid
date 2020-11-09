package com.example.inmobiliaria.login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inmobiliaria.Configuracion;
import com.example.inmobiliaria.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsuario;
    private EditText etContraseña;
    private Button btLogin;

    private LoginActivityViewModel viewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuracion.setRuta("http://192.168.0.106:45465/");
        setContentView(R.layout.activity_login);
        inicializar();
    }

    private void inicializar() {
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginActivityViewModel.class);
        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String error) {
                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
        etUsuario = findViewById(R.id.etUsuario);
        etContraseña = findViewById(R.id.etContraseña);
        btLogin = findViewById(R.id.btLogin);
        Log.d("Salida", btLogin.toString());
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    public void login() {
        String usuario = etUsuario.getText().toString();
        String contraseña = etContraseña.getText().toString();
        viewModel.autenticar(usuario, contraseña);
    }
}
