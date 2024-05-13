package ar.utn.frbb.tup.Imputs;

import ar.utn.frbb.tup.Entidades.Banco;


public class InputMenu extends InputValidacion {
    public static Banco banco;

    public static void mostrarMenuInicioSesion() {
        int op;
        do {
            System.out.println("\nBienvenido a la applicacion del Banco");
            System.out.println("1. Iniciar sesion");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");
            op = leerEntero("Ingrese una opcion: ");
            System.out.println();

            switch (op) {
                case 1:
                    InputSesion.iniciarSesion(banco);
                    break;
                case 2:
                    InputSesion.registrarse(banco);
                    break;
                case 3:
                    System.out.println("Saliendo del Banco.");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (op < 1 || op > 3); // Se repite mientras la opcion no este entre 1 y 3
    }

   

    public static void mostrarMenuPrincipal(Banco banco) {
        int opcion;
        do {
            System.out.println("\nMenu Principal Cliente");
            System.out.println("1. Ver datos del cliente");
            System.out.println("2. Ver cuentas del cliente");
            System.out.println("3. Realizar operaciones");
            System.out.println("4. Salir");
            opcion = leerEntero("Ingrese una opcion: ");
            System.out.println();

            switch (opcion) {
                case 1:
                    gestionarClientes(banco);
                    break;
                case 2:
                    gestionarCuentas(banco);
                    break;
                case 3:
                    realizarOperaciones(banco);
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opcion invalida. Intente nuevamente.");
                    mostrarMenuPrincipal(banco);
                    break;
            }
        } while (opcion != 4);// Se repite mientras la opcion sea diferente de 4
    }

    public static int mostrarMenuClientes() {
        System.out.println("\nDatos del Clientes");
        System.out.println("1. Agregar cliente");
        System.out.println("2. Modificar cliente");
        System.out.println("3. Eliminar cliente");
        System.out.println("4. Volver al menu principal");
        return leerEntero("Ingrese una opcion: ");
    }

    public static void gestionarClientes(Banco banco) {
        int opcion;
        do {
            opcion = mostrarMenuClientes();
            switch (opcion) {
                case 1:
                    InputCliente.agregarCliente(banco);
                    break;
                case 2:
                    InputCliente.modificarCliente(banco);
                    break;
                case 3:
                    InputCliente.eliminarCliente(banco);
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opcion invalida. Intente nuevamente.");
                    mostrarMenuClientes();
                    break;
            }
        } while (opcion != 4); // Se repite mientras la opcion sea diferente de 4
    }

   
    

    public static int mostrarMenuCuentas() {
        System.out.println("\nDatos de Cuentas");
        System.out.println("1. Agregar cuenta");
        System.out.println("2. Modificar cuenta");
        System.out.println("3. Eliminar cuenta");
        System.out.println("4. Volver al menu principal");
        return leerEntero("Ingrese una opcion: ");
    }

    public static void gestionarCuentas(Banco banco) {
        int opcion;
        do {
            opcion = mostrarMenuCuentas();
            switch (opcion) {
                case 1:
                    InputCuenta.crearYAgregarCuenta(banco);
                    break;
                case 2:
                    InputCuenta.modificarCuenta(banco);
                    break;
                case 3:
                    InputCuenta.eliminarCuenta(banco);
                    break;
                case 4:
                    // Vuelvo al menu principal
                    break;
                default:
                    System.out.println("Opcion invalida. Intente nuevamente.");
                    mostrarMenuCuentas();
                    break;
            }
        } while (opcion != 4);
    }

 
    public static int mostrarMenuOperaciones() {
        System.out.println("\nOperaciones Bancarias");
        System.out.println("1. Consultar saldo");
        System.out.println("2. Realizar deposito");
        System.out.println("3. Realizar retiro");
        System.out.println("4. Realizar transferencia");
        System.out.println("5. Consultar movimientos");
        System.out.println("6. Volver al menu principal");
        return leerEntero("Ingrese una opcion: ");
    }

    public static void realizarOperaciones(Banco banco) {
        int opcion;
        do {
            opcion = mostrarMenuOperaciones();
            switch (opcion) {
                case 1:
                    InputCuenta.consultarSaldo(banco);
                    break;
                case 2:
                    InputCuenta.realizarDeposito(banco);
                    break;
                case 3:
                    InputCuenta.realizarRetiro(banco);
                    break;
                case 4:
                    InputCuenta.realizarTransferencia(banco);
                    break;
                case 5:
                    InputCuenta.consultarMovimientos(banco);
                    break;
                case 6:
                    // Vuelvo al menu principal
                    break;
                default:
                    System.out.println("Opcion invalida. Intente nuevamente.");
                    realizarOperaciones(banco);
                    break;
            }
        } while (opcion != 6);
    }
    
    
}