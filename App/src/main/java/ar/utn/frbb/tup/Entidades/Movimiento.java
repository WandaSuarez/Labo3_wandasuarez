package ar.utn.frbb.tup.Entidades;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Movimiento {
    private Date fechaHora;
    private String tipoOperacion;
    private double monto;
    private double saldoActual;

    public Movimiento(Date fechaHora, String tipoOperacion, double monto, double saldoActual) {
        this.fechaHora = fechaHora;
        this.tipoOperacion = tipoOperacion;
        this.monto = monto;
        this.saldoActual = saldoActual;
    }

    // Getters y Setters

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(double saldoActual) {
        this.saldoActual = saldoActual;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaHoraFormateada = formatter.format(fechaHora);
        return "Tipo de operación: " + tipoOperacion + ", Monto: " + monto + ", Saldo actual: " + saldoActual + ", Fecha y hora: " + fechaHoraFormateada;
    }
}
