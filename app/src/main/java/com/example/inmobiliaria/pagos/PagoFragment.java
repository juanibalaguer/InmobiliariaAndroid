package com.example.inmobiliaria.pagos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.inmobiliaria.R;
import com.example.inmobiliaria.modelo.Pago;

import java.util.ArrayList;

public class PagoFragment extends Fragment {

    private PagoViewModel mViewModel;

    public static PagoFragment newInstance() {
        return new PagoFragment();
    }

    Context context;
    ListView lvPagos;
    PagoViewModel pagoViewModel;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.pago_fragment, container, false);
        context = root.getContext();
        inicializar(root);
        return root;
    }

    private void inicializar(View view) {
        pagoViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PagoViewModel.class);
        lvPagos = view.findViewById(R.id.lvPagos);
        pagoViewModel.getPagos().observe(getViewLifecycleOwner(), new Observer<ArrayList<Pago>>() {
            @Override
            public void onChanged(ArrayList<Pago> pagos) {
                PagoAdapter adapter = new PagoAdapter(context, R.layout.item_pago_fragment, pagos, getLayoutInflater());
                lvPagos.setAdapter(adapter);
            }
        });
        pagoViewModel.cargarPagos(getArguments());

    }

}