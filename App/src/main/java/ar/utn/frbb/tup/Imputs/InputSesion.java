package ar.utn.frbb.tup.Imputs;
import ar.utn.frbb.tup.Entidades.Banco;
import ar.utn.frbb.tup.Entidades.Cliente;


public class InputSesion extends InputValidacion {
    
    private static Cliente clienteActual;

    public static void iniciarSesion(Banco banco) {
        boolean idCorrecto = false;
        boolean dniCorrecto = false;
        Cliente clienteid = null;
    
        while (!idCorrecto) { // Se repite mientras el ID  sea correcto falso
            int idCliente = leerEntero("Ingrese su ID: ");
            clienteid = banco.buscarClientePorID(idCliente); // Busca el cliente en el banco
            if (clienteid != null) { // Si encontro al cliente con ese ID
                idCorrecto = true; //Establecemos el IdCorrecto como verdadero
            } else {
                System.out.println("ID incorrecto. Por favor, intentelo nuevamente.");
            }
        }
    
        while (!dniCorrecto) { // Se repite mientras el DNI sea correcto falso
            long dni = leerLong("Ingrese su DNI: ");
            Cliente cliente = banco.buscarClientePorDNI(dni); // Busca el cliente en el banco
            if (cliente != null && cliente == clienteid) { // Si encontro al cliente con ese DNI y es el mismo que el ID
                dniCorrecto = true; //Establecemos el DniCorrecto como verdadero
                clienteActual = clienteid; // Establecemos el cliente actual
                System.out.println("Inicio de sesion exitoso, bienvenido!");
                InputMenu.mostrarMenuPrincipal(banco);
            } else {
                System.out.println("DNI incorrecto. Por favor, intentelo nuevamente.");
            }
        }
    }
    
    
    public static void registrarse(Banco banco) {
        long dni;
        do {
            dni = leerLong("Ingrese su DNI: ");
            if (banco.existeClientePorDNI(dni)) {
                System.out.println("El DNI ingresado ya esta registrado. Por favor, ingrese otro DNI.");
            }
        } while (banco.existeClientePorDNI(dni)); // Se repite mientras el DNI este registrado

        String nombre = leerCadena("Ingrese su nombre: ");
        String apellido = leerCadena("Ingrese su apellido: ");
        String direccion = leerCadena("Ingrese su direccion: ");
        long telefono = leerLong("Ingrese su numero de telefono: ");
        int idCliente = InputCliente.generarNumeroId();
        System.out.println("El ID del cliente es: " + idCliente);

        Cliente nuevoCliente = new Cliente(idCliente, nombre, apellido, direccion, telefono, dni);
        banco.agregarCliente(nuevoCliente);
        clienteActual = nuevoCliente;
        System.out.println("Registro exitoso, Bienvenido!");
        InputMenu.mostrarMenuPrincipal(banco);
    }
}
