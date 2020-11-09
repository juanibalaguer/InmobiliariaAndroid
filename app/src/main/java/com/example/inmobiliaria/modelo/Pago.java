package com.example.inmobiliaria.modelo;

import java.io.Serializable;
import java.util.Date;

public class Pago implements Serializable {

    private int id;
    private int numero;
    private int idContrato;
    private Contrato contrato;
    private double importe;
    private Date fechaDePago;

    public Pago() {}

    public Pago(int id, int numero, int idContrato, Contrato contrato, double importe, Date fechaDePago) {
        this.id = id;
        this.numero = numero;
        this.idContrato = idContrato;
        this.contrato = contrato;
        this.importe = importe;
        this.fechaDePago = fechaDePago;
    }

    public int getIdPago() {
        return id;
    }

    public void setIdPago(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public Date getFechaDePago() {
        return fechaDePago;
    }

    public void setFechaDePago(Date fechaDePago) {
        this.fechaDePago = fechaDePago;
    }
}
