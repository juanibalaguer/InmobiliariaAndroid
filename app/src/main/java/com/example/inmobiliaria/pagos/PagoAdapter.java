package com.example.inmobiliaria.pagos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.inmobiliaria.R;
import com.example.inmobiliaria.modelo.Pago;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PagoAdapter extends ArrayAdapter<Pago> {
    private ArrayList<Pago> pagos;
    private Context context;
    private LayoutInflater inflater;
    public PagoAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Pago> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.pagos = objects;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View viewPago = convertView;
        if (viewPago == null) {
            viewPago = inflater.inflate(R.layout.item_pago_fragment, parent, false);
        }
        TextView tvCodigo = viewPago.findViewById(R.id.tvCodigo);
        TextView tvNumero = viewPago.findViewById(R.id.tvNumero);
        TextView tvContrato = viewPago.findViewById(R.id.tvCodigoContrato);
        TextView tvImporte = viewPago.findViewById(R.id.tvImporte);
        TextView tvFecha = viewPago.findViewById(R.id.tvFecha);
        tvCodigo.setText(pagos.get(position).getIdPago() + "");
        tvNumero.setText(pagos.get(position).getNumero() + "");
        tvContrato.setText(pagos.get(position).getIdContrato() + "");
        tvImporte.setText("$" + pagos.get(position).getImporte());
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechaParseada = formato.format(pagos.get(position).getFechaDePago());
        tvFecha.setText(fechaParseada);
        return viewPago;
    }
}
