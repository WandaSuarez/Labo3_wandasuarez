package ar.utn.frbb.tup.Entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cuenta {
    private int numeroCuenta;
    private String tipoCuenta;
    private double saldo;
    private Date fechaApertura;
    private Cliente cliente;
    private List<Movimiento> movimientos;
    private String moneda;
    private String alias;
    private long cvu;
    

    public Cuenta(int numeroCuenta, String tipoCuenta, double saldoInicial, Date fechaApertura, Cliente cliente, String moneda, String alias, long cvu) {
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldoInicial;
        this.fechaApertura = fechaApertura;
        this.cliente = cliente;
        this.movimientos = new ArrayList<>();
        this.moneda = moneda;
        this.alias = alias;
        this.cvu = cvu;
        cliente.agregarCuenta(this); // Agregar la cuenta al cliente
    }

    public double consultarSaldo() {
        return saldo;
    }

    public void realizarDeposito(double monto) {
        if (monto > 0) {
            saldo += monto;
            Movimiento movimiento = new Movimiento(new Date(), "Depósito", monto, saldo);
            movimientos.add(movimiento);
            System.out.println("Depósito realizado. Saldo actual: " + saldo);
        } else {
            System.out.println("El monto debe ser positivo.");
        }
    }

    public void realizarRetiro(double monto) {
        if (monto > 0 && saldo >= monto) {
            saldo -= monto;
            Movimiento movimiento = new Movimiento(new Date(), "Retiro", -monto, saldo);
            movimientos.add(movimiento);
            System.out.println("Retiro realizado. Saldo actual: " + saldo);
        } else {
            System.out.println("Fondos insuficientes o monto inválido.");
        }
    }

    public void realizarTransferencia(Cuenta cuentaDestino, double monto) {
        if (monto > 0 && saldo >= monto) {
            saldo -= monto;
            Movimiento movimiento = new Movimiento(new Date(), "Transferencia", -monto, saldo);
            movimientos.add(movimiento);
            System.out.println("Transferencia realizada. Saldo actual: " + saldo);
        } else {
            System.out.println("Fondos insuficientes o monto inválido.");
            System.out.println("Su saldo actual es: " + saldo);
        }
    }

    public List<Movimiento> consultarMovimientos() {
        return movimientos;
    }

    // Getters y Setters

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public long getCvu() {
        return cvu;
    }

    public void setCvu(long cvu) {
        this.cvu = cvu;
    }
}
