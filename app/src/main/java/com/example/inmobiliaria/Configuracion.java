package com.example.inmobiliaria;

import android.app.Application;
import android.graphics.Bitmap;

public class Configuracion extends Application {

    private static String ruta;

    public static String getRuta() {
        return ruta;
    }

    public static void setRuta(String ruta) {
        Configuracion.ruta = ruta;
    }
}