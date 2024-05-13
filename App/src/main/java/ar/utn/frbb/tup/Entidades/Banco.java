package ar.utn.frbb.tup.Entidades;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private String nombre;
    private List<Cliente> clientes;
    private List<Cuenta> cuentas;

    public Banco(String nombre) {
        this.nombre = nombre;
        clientes = new ArrayList<>();
        cuentas = new ArrayList<>();
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    public void eliminarCuenta(Cuenta cuenta) {
        cuentas.remove(cuenta);
    }

    public void eliminarCliente(Cliente cliente) {
        clientes.remove(cliente);
        List<Cuenta> cuentasCliente = buscarCuentasCliente(cliente.getIdCliente());
        cuentas.removeAll(cuentasCliente);
    }

    public Cliente buscarClientePorID(int idCliente) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdCliente() == idCliente) {
                return cliente;
            }
        }
        return null;
    }

    public Cliente buscarClientePorDNI(long dni) {
        for (Cliente cliente : clientes) {
            if (cliente.getDni() == dni) {
                return cliente;
            }
        }
        return null;
    }
    
    public boolean existeClientePorDNI(long dni) {
        for (Cliente cliente : clientes) {
            if (cliente.getDni() == dni) {
                return true;
            }
        }
        return false;
    }
    

    public Cuenta buscarCuenta(int numeroCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta() == numeroCuenta) {
                return cuenta;
            }
        }
        return null;
    }

    public List<Cuenta> buscarCuentasCliente(int idCliente) {
        List<Cuenta> cuentasCliente = new ArrayList<>();
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getCliente().getIdCliente() == idCliente) {
                cuentasCliente.add(cuenta);
            }
        }
        return cuentasCliente;
    }

    public void realizarDeposito(Cuenta cuenta, double monto) {
        if (monto > 0) {
            cuenta.realizarDeposito(monto);
        } else {
            System.out.println("El monto de depósito debe ser mayor a cero.");
        }
    }

    public boolean realizarRetiro(Cuenta cuenta, double monto) {
        if (cuenta.getSaldo() >= monto) {
            cuenta.setSaldo(cuenta.getSaldo() - monto);
            // Realizar otras operaciones necesarias
            return true; // Retiro exitoso
        } else {
            return false; // Saldo insuficiente
        }
    }

    public boolean realizarTransferencia(Cuenta cuentaOrigen, Cuenta cuentaDestino, double monto) {
        if (cuentaOrigen.getSaldo() >= monto) {
            cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);
            cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);
            return true; // Transferencia exitosa
        } else {
            return false; // Saldo insuficiente
        }
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }
}