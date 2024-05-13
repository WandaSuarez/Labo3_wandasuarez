package ar.utn.frbb.tup.Entidades;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona {
    private int idCliente;
    private long dni; 
    private List<Cuenta> cuentas;

    public Cliente(int idCliente, String nombre, String apellido, String direccion, long telefono, long dni) { // Cambio de int a long
        super(nombre, apellido, direccion, telefono);
        cuentas = new ArrayList<>();
        this.idCliente = idCliente;
        this.dni = dni;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public long getDni() { // Cambio de int a long
        return dni;
    }

    public void setDni(long dni) { // Cambio de int a long
        this.dni = dni;
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    public void eliminarCuenta(Cuenta cuenta) {
        cuentas.remove(cuenta);
    }

    public Cuenta buscarCuenta(int numeroCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta() == numeroCuenta) {
                return cuenta;
            }
        }
        return null;
    }

    public double consultarSaldoTotal() {
        double saldoTotal = 0;
        for (Cuenta cuenta : cuentas) {
            saldoTotal += cuenta.getSaldo();
        }
        return saldoTotal;
    }
}
