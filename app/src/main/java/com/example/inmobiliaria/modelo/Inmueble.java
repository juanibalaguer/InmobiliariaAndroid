package com.example.inmobiliaria.modelo;

import com.example.inmobiliaria.modelo.Propietario;

import java.io.Serializable;

public class Inmueble implements Serializable {

    private int id;
    private String direccion;
    private String uso;
    private String tipo;
    private int ambientes;
    private double precio;
    private int idPropietario;
    private Propietario propietario;
    private boolean estado;
    private String foto;

    public Inmueble(int id, String direccion, String uso, String tipo, int ambientes, double precio, int idPropietario, Propietario propietario, boolean estado, String foto) {
        this.id = id;
        this.direccion = direccion;
        this.uso = uso;
        this.tipo = tipo;
        this.ambientes = ambientes;
        this.precio = precio;
        this.idPropietario = idPropietario;
        this.propietario = propietario;
        this.estado = estado;
        this.foto = foto;
    }
    public Inmueble() {

    }
    public int getId() {
        return id;
    }

    public void setId(int idInmueble) {
        this.id = idInmueble;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(int ambientes) {
        this.ambientes = ambientes;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public int getIdPropietario() {
        return idPropietario;
    }

    public void setPropietario(int idPropietario) {
        this.idPropietario = idPropietario;
    }
    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
