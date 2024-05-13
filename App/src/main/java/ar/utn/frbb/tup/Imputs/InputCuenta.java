package ar.utn.frbb.tup.Imputs;
import ar.utn.frbb.tup.Entidades.Banco;
import ar.utn.frbb.tup.Entidades.Cliente;
import ar.utn.frbb.tup.Entidades.Cuenta;
import ar.utn.frbb.tup.Entidades.Movimiento;

import java.util.Date;
import java.util.List;
import java.util.Random;


public class InputCuenta extends InputValidacion {

    public static void crearYAgregarCuenta(Banco banco) {
        Cliente cliente = InputCliente.seleccionarCliente(banco); // Elijo un cliente para asociar la cuenta
        if (cliente != null) { // Si el cliente elegido existe
            String tipoCuenta = leerCadena("Ingrese el tipo de cuenta (Ahorro o Corriente): ");
            double saldoInicial = leerDouble("Ingrese el saldo inicial de la cuenta: ");
            Date fechaApertura = new Date(); // Fecha actual
            String moneda = leerCadena("Ingrese la moneda de la cuenta: ");
            String alias = leerCadena("Ingrese un alias para la cuenta: ");
            long cvu = leerLong("Ingrese el CVU de la cuenta: ");
            int numeroCuenta = generarNumeroCuenta(); // Método para generar un número de cuenta único
            System.out.print("Su número de cuenta es: " + numeroCuenta);

            Cuenta nuevaCuenta = new Cuenta(numeroCuenta, tipoCuenta, saldoInicial, fechaApertura, cliente, moneda, alias, cvu);
            cliente.agregarCuenta(nuevaCuenta);
            banco.agregarCuenta(nuevaCuenta);

            System.out.println("\nCuenta agregada correctamente.");
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }


    private static int generarNumeroCuenta() {
        Random random = new Random();
        int numeroCuenta = random.nextInt(9000) + 1000; // Genera un numero aleatorio entre 1000 y 9999
        return numeroCuenta;
    }


    public static void modificarCuenta(Banco banco) {
        Cuenta cuentaModificar = seleccionarCuenta(banco); // Elijo la cuenta a modificar
        if (cuentaModificar != null) { // Si la cuenta existe
            String tipoCuenta = leerCadena("Ingrese el nuevo tipo de cuenta (Ahorro o Corriente) (Enter para no modificar): ");

            if (!tipoCuenta.isEmpty()) { // Si el tipo de cuenta no esta vacio
                cuentaModificar.setTipoCuenta(tipoCuenta); // Establesco el nuevo tipo de cuenta
            }

            System.out.println("Cuenta modificada correctamente.");
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    public static void eliminarCuenta(Banco banco) {
        Cuenta cuentaEliminar = seleccionarCuenta(banco); // Eligo la cuenta a eliminar
        if (cuentaEliminar != null) { // Si la cuenta existe
            Cliente cliente = cuentaEliminar.getCliente(); // Obtengo el cliente asociado a la cuenta
            cliente.eliminarCuenta(cuentaEliminar); // Elimino la cuenta del cliente
            banco.eliminarCuenta(cuentaEliminar); // Elimino la cuenta del banco
            System.out.println("Cuenta eliminada correctamente.");
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    public static void consultarSaldo(Banco banco) {
        Cuenta cuenta = seleccionarCuenta(banco);
        if (cuenta != null) {
            System.out.println("Saldo de la cuenta: " + cuenta.getSaldo());
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    public static void realizarDeposito(Banco banco) {
        Cuenta cuenta = seleccionarCuenta(banco);
        if (cuenta != null) {
            double monto = leerDouble("Ingrese el monto a depositar: ");
            banco.realizarDeposito(cuenta, monto);
            System.out.println("Deposito realizado. Saldo actual: " + cuenta.getSaldo());
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    public static void realizarRetiro(Banco banco) {
        Cuenta cuenta = seleccionarCuenta(banco);
        if (cuenta != null) {
            double monto = leerDouble("Ingrese el monto a retirar: ");
            if (banco.realizarRetiro(cuenta, monto)) { // Si el retiro fue exitoso
                System.out.println("Retiro realizado. Saldo actual: " + cuenta.getSaldo());
            } else {
                System.out.println("Saldo insuficiente para realizar el retiro.");
                System.out.println("Saldo actual: " + cuenta.getSaldo());
                realizarRetiro(banco);
            }
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    public static void realizarTransferencia(Banco banco) {
        List<Cuenta> cuentas = banco.getCuentas();
        System.out.println("Cuentas registradas:");
        for (int i = 0; i < cuentas.size(); i++) {
            Cuenta cuenta = cuentas.get(i);
            System.out.println((i + 1) + ". " + cuenta.getNumeroCuenta() + " - " + cuenta.getTipoCuenta());
        }
    
        int numeroCuentaOrigen;
        Cuenta cuentaOrigen;
        do {
            numeroCuentaOrigen = leerEntero("Ingrese el número de la cuenta de origen: ");
            cuentaOrigen = banco.buscarCuenta(numeroCuentaOrigen);
            if (cuentaOrigen == null) {
                System.out.println("Número de cuenta inválido. Por favor, inténtelo nuevamente.");
            }
        } while (cuentaOrigen == null);
    
        int numeroCuentaDestino;
        Cuenta cuentaDestino;
        do {
            numeroCuentaDestino = leerEntero("Ingrese el número de la cuenta de destino: ");
            cuentaDestino = banco.buscarCuenta(numeroCuentaDestino);
            if (cuentaDestino == null) {
                System.out.println("Número de cuenta inválido. Por favor, inténtelo nuevamente.");
            }
        } while (cuentaDestino == null);
    
        double monto = leerDouble("Ingrese el monto a transferir: ");
        cuentaOrigen.realizarTransferencia(cuentaDestino, monto);
    }
    

    public static void consultarMovimientos(Banco banco) {
        Cuenta cuenta = seleccionarCuenta(banco); // Eligo la cuenta
        if (cuenta != null) {
            List<Movimiento> movimientos = cuenta.getMovimientos(); // Obtengo la lista de movimientos de la cuenta
            if (movimientos.isEmpty()) { // Si la lista de movimientos esta vacia
                System.out.println("No hay movimientos registrados para esta cuenta.");
            } else {
                System.out.println("Movimientos de la cuenta:");
                for (Movimiento movimiento : movimientos) { // Recorro la lista de movimientos
                    System.out.println(movimiento);
                }
            }
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    private static Cuenta seleccionarCuenta(Banco banco) {
        List<Cuenta> cuentas = banco.getCuentas(); // Obtengo la lista de cuentas
        if (cuentas.isEmpty()) {
            System.out.println("No hay cuentas registradas.");
            return null;
        }

        System.out.println("Cuentas registradas:");
        for (int i = 0; i < cuentas.size(); i++) { // Recorro la lista de cuentas
            Cuenta cuenta = cuentas.get(i); // Obtengo la cuenta
            System.out.println((i + 1) + ". " + cuenta.getNumeroCuenta() + " - " + cuenta.getTipoCuenta());
        }

        int opcion;
        do {
            opcion = leerEntero("Ingrese el numero de la cuenta: ");
            if (opcion >= 1 && opcion <= cuentas.size()) { // Si la opcion es valida
                return cuentas.get(opcion - 1); // Retorno la cuenta correspondiente
            } else {
                System.out.println("Opcion invalida. Intente nuevamente.");
                seleccionarCuenta(banco);
            }
        } while (true); 
    }
}
