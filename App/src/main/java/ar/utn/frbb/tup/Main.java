package ar.utn.frbb.tup;
import java.util.Date;

import ar.utn.frbb.tup.Entidades.Banco;
import ar.utn.frbb.tup.Entidades.Cliente;
import ar.utn.frbb.tup.Entidades.Cuenta;
import ar.utn.frbb.tup.Imputs.InputMenu;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco("Banco Nacional");

        // Agrego algunos clientes y cuentas de prueba
        Cliente cliente1 = new Cliente(222, "Juan", "Pérez", "Calle Falsa 123", 1234567890L, 12345678);
        Cliente cliente2 = new Cliente(255, "María", "Gómez", "Avenida Siempreviva 456", 4557744L, 87654321);

        Cuenta cuenta1 = new Cuenta(1234, "Ahorro", 5000.0, new Date(), cliente1, "USD", "Cuenta Personal", 1234567890);
        Cuenta cuenta2 = new Cuenta(5678, "Corriente", 10000.0, new Date(), cliente2, "EUR", "Cuenta Empresa", 588665);

        // Agregar cuentas al banco
        banco.agregarCliente(cliente1);
        banco.agregarCliente(cliente2);
        banco.agregarCuenta(cuenta1);
        banco.agregarCuenta(cuenta2);

        InputMenu.banco = banco;        
        InputMenu.mostrarMenuInicioSesion();
    }
}
