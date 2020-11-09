package com.example.inmobiliaria.logout;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobiliaria.R;

public class LogoutFragment extends Fragment {


    public static LogoutFragment newInstance() {
        return new LogoutFragment();
    }
    private LogOutViewModel logOutViewModel;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.logout_fragment, container, false);
        cerrarSesión();
        return root;
    }

    public void cerrarSesión() {
        logOutViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(LogOutViewModel.class);
        new AlertDialog.Builder(getContext(), R.style.AlertDialogTheme)
                .setTitle("Cierre de sesión")
                .setMessage("Está seguro de que desea cerrar la sesión?")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        logOutViewModel.cerrarSesion();
                        System.exit(0);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.perfilFragment);
                    }
                }).show();
    }



}