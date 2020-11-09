package com.example.inmobiliaria.inquilinos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.inmobiliaria.R;
import com.example.inmobiliaria.modelo.Inquilino;

public class InquilinoFragment extends Fragment {

    private InquilinoViewModel inquilinoViewModel;
    private TextView tvNombre;
    private TextView tvApellido;
    private TextView tvDNI;
    private TextView tvCodigo;
    private TextView tvEmail;
    private TextView tvTelefono;
    private TextView tvGarante;
    private TextView tvTelefonoGarante;

    public InquilinoFragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.inquilino_fragment, container, false);
        inicializar(root);
        return root;
    }


    public void inicializar(View view) {
        tvNombre = view.findViewById(R.id.tvNombre);
        tvApellido = view.findViewById(R.id.tvApellido);
        tvCodigo = view.findViewById(R.id.tvCodigo);
        tvDNI = view.findViewById(R.id.tvDNI);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvTelefono = view.findViewById(R.id.tvTelefono);
        tvGarante = view.findViewById(R.id.tvGarante);
        tvTelefonoGarante = view.findViewById(R.id.tvTelefonoGarante);

        inquilinoViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InquilinoViewModel.class);
        inquilinoViewModel.getInquilino().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino inquilino) {
                tvNombre.setText(inquilino.getNombre());
                tvApellido.setText(inquilino.getApellido());
                tvCodigo.setText(inquilino.getIdInquilino() + "");
                tvDNI.setText(inquilino.getDNI());
                tvEmail.setText(inquilino.getEmail());
                tvTelefono.setText(inquilino.getTelefono());
                tvGarante.setText(inquilino.getNombreGarante());
                tvTelefonoGarante.setText(inquilino.getTelefonoGarante());

            }
        });
        inquilinoViewModel.cargarInquilino(getArguments());
    }

}