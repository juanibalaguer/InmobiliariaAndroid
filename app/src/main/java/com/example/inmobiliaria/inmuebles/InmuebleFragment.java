package com.example.inmobiliaria.inmuebles;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.inmobiliaria.Configuracion;
import com.example.inmobiliaria.R;
import com.example.inmobiliaria.modelo.Inmueble;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class InmuebleFragment extends Fragment {
    static final int REQUEST_IMAGE_GET = 1;
    private InmuebleViewModel inmuebleViewModel;
    private TextView tvId;
    private TextView tvDireccion, tvTipo, tvUso, tvAmbientes, tvPrecio;
    private CheckBox cbEstado;
    private ImageView ivImagenInmueble;
    private Button btCambiarDisponibilidad;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.inmueble_fragment, container, false);
        inicializar(root);
        return root;
    }

    private void inicializar(View view) {
        tvId = view.findViewById(R.id.tvId);
        tvDireccion = view.findViewById(R.id.tvDireccion);
        tvTipo = view.findViewById(R.id.tvTipo);
        tvUso = view.findViewById(R.id.tvUso);
        tvAmbientes = view.findViewById(R.id.tvAmbientes);
        tvPrecio = view.findViewById(R.id.etPrecio);
        cbEstado = view.findViewById(R.id.cbEstado);
        ivImagenInmueble = view.findViewById(R.id.ivImagenInmueble);
        btCambiarDisponibilidad = view.findViewById(R.id.btCambiarDisponibilidad);
       // btAgregarFoto = view.findViewById(R.id.btAgregarFoto);
        inmuebleViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InmuebleViewModel.class);
        inmuebleViewModel.getInmueble().observe(getActivity(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmueble) {
                tvId.setText(inmueble.getId() + "");
                tvDireccion.setText(inmueble.getDireccion());
                tvTipo.setText(inmueble.getTipo());
                tvUso.setText(inmueble.getUso());
                tvAmbientes.setText(inmueble.getAmbientes() + "");
                tvPrecio.setText("$" + inmueble.getPrecio());
                cbEstado.setChecked(inmueble.isEstado());
                btCambiarDisponibilidad.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        inmuebleViewModel.cambiarDisponibilidad();
                    }
                });

                Glide.with(getActivity())
                        .asBitmap()
                        .load(Configuracion.getRuta() + "Uploads/" + inmueble.getFoto() + ".jpg")
                        .into(new CustomTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(@NonNull Bitmap foto, @Nullable Transition<? super Bitmap> transition) {
                                Resources res = getResources();
                                RoundedBitmapDrawable dr = RoundedBitmapDrawableFactory.create(res, foto);
                                dr.setCornerRadius(100.0f);
                                ivImagenInmueble.setImageDrawable(dr);
                            }
                            @Override
                            public void onLoadCleared(@Nullable Drawable placeholder) {
                                ivImagenInmueble.setImageDrawable(placeholder);
                            }
                        });

            }
        });
        inmuebleViewModel.cargarInmueble(getArguments());

    }

    }

