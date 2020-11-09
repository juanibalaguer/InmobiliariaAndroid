package com.example.inmobiliaria.inquilinos;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.inmobiliaria.Configuracion;
import com.example.inmobiliaria.R;
import com.example.inmobiliaria.contratos.InmuebleConContratoAdapter;
import com.example.inmobiliaria.modelo.Inmueble;
import com.example.inmobiliaria.modelo.Inquilino;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public class InmuebleConInquilinoAdapter extends RecyclerView.Adapter<InmuebleConInquilinoAdapter.ViewHolder> {

    List<Inmueble> inmuebles;
    Context context;
    LayoutInflater inflater;

    public InmuebleConInquilinoAdapter(Context context, List<Inmueble> inmuebles, LayoutInflater inflater) {
        this.context = context;
        this.inmuebles = inmuebles;
        this.inflater = inflater;
    }
    @NonNull
    @Override
    public InmuebleConInquilinoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_inquilino_fragment, parent, false);
        return new InmuebleConInquilinoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final InmuebleConInquilinoAdapter.ViewHolder holder, int position) {
        holder.tvDireccion.setText(inmuebles.get(position).getDireccion());
        Glide.with(holder.itemView)
                .asBitmap()
                .load(Configuracion.getRuta() + "Uploads/" + inmuebles.get(position).getFoto() + ".jpg")
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap foto, @Nullable Transition<? super Bitmap> transition) {
                        Resources res = context.getResources();
                        RoundedBitmapDrawable dr = RoundedBitmapDrawableFactory.create(res, foto);
                        dr.setCornerRadius(100.0f);
                        holder.ivImagenInmueble.setImageDrawable(dr);
                    }
                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                        holder.ivImagenInmueble.setImageDrawable(placeholder);
                    }
                });

    }

    @Override
    public int getItemCount() {
        return inmuebles.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDireccion;
        ImageView ivImagenInmueble;
        Button btInquilino;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagenInmueble = itemView.findViewById(R.id.ivImagenInmueble);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            btInquilino = itemView.findViewById(R.id.btInquilino);
            btInquilino.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    Inmueble inmueble = inmuebles.get(getAdapterPosition());
                    bundle.putSerializable("inmueble", inmueble);
                    Navigation.findNavController((Activity) context, R.id.nav_host_fragment).navigate(R.id.inquilinoFragment, bundle);
                }
            });
        }
    }

}
