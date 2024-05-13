package ar.utn.frbb.tup.Imputs;
import ar.utn.frbb.tup.Entidades.Banco;
import ar.utn.frbb.tup.Entidades.Cliente;

import java.util.List;
import java.util.Random;
public class InputCliente extends InputValidacion {
    public static void agregarCliente(Banco banco) {
        long dni;
        do {
            dni = leerLong("Ingrese el DNI del nuevo cliente: ");
            if (banco.existeClientePorDNI(dni)) {
                System.out.println("El DNI ingresado ya esta registrado. Por favor, ingrese otro DNI.");
                agregarCliente(banco);
            }
        } while (banco.existeClientePorDNI(dni)); // Se repite mientras el DNI este registrado

        String nombre = leerCadena("Ingrese el nombre del cliente: ");
        String apellido = leerCadena("Ingrese el apellido del cliente: ");
        String direccion = leerCadena("Ingrese la direccion del cliente: ");
        long telefono = leerLong("Ingrese el numero de telefono del cliente: ");
        int idCliente = generarNumeroId();
        System.out.println("El ID del cliente es: " + idCliente);
        Cliente nuevoCliente = new Cliente(idCliente,nombre, apellido, direccion, telefono, dni);
        banco.agregarCliente(nuevoCliente);
        System.out.println("Cliente agregado correctamente, Bienvenido!.");
    }

    

    public static void modificarCliente(Banco banco) {
        Cliente clienteModificar = seleccionarCliente(banco); // Se selecciona un cliente para modificar
        if (clienteModificar != null) { // Si el cliente seleccionado existe
            String nombre = leerCadena("Ingrese el nuevo nombre del cliente (Enter para no modificar): ");
            String apellido = leerCadena("Ingrese el nuevo apellido del cliente (Enter para no modificar): ");
            String direccion = leerCadena("Ingrese la nueva direccion del cliente (Enter para no modificar): ");
            long telefono = leerLong("Ingrese el nuevo numero de teleefono del cliente (Enter para no modificar): ");
            long dni = leerLong("Ingrese el nuevo DNI del cliente (Enter para no modificar): ");
            int idCliente = generarNumeroId();
            System.out.println("El ID modificado del cliente es: " + idCliente);
            if (!nombre.isEmpty()) { // Si el nombre no esta vacio
                clienteModificar.setNombre(nombre); // EStablesco el nuevo nombre del cliente
            }
            if (!apellido.isEmpty()) {
                clienteModificar.setApellido(apellido);
            }
            if (!direccion.isEmpty()) {
                clienteModificar.setDireccion(direccion);
            }
            if (telefono != 0 && telefono != clienteModificar.getTelefono()) { // Si el tel es diferente de cero y es diferente al tel actual del cliente
                clienteModificar.setTelefono(telefono); // Establesco el nuevo telefono
            }
            if (dni != 0 && dni != clienteModificar.getDni()) {
                clienteModificar.setDni(dni);   
            }
            if (idCliente != 0 && idCliente != clienteModificar.getIdCliente()) {
                clienteModificar.setIdCliente(idCliente);
            }
            

            System.out.println("Cliente modificado correctamente.");
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    public static void eliminarCliente(Banco banco) {
        Cliente clienteEliminar = seleccionarCliente(banco); // Selecciono el cliente a eliminar
        if (clienteEliminar != null) { // Si el cliente seleccionado existe
            banco.eliminarCliente(clienteEliminar); // Elimino el cliente del banco
            System.out.println("Cliente eliminado correctamente.");
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    public static Cliente seleccionarCliente(Banco banco) {
        List<Cliente> clientes = banco.getClientes(); // Obtengo la lista de clientes
        if (clientes.isEmpty()) { // Si la lista esta vacia
            System.out.println("No hay clientes registrados.");
            return null;
        }

        System.out.println("Clientes registrados:");
        for (int i = 0; i < clientes.size(); i++) { // Recorro la lista de clientes
            Cliente cliente = clientes.get(i); // Obtengo el cliente
            System.out.println((i + 1) + ". " + cliente.getNombre() + " " + cliente.getApellido()); // Imprimo a el/los cliente/s
        }

        int opcion;
        do {
            opcion = leerEntero("Ingrese el numero del cliente: ");
            if (opcion >= 1 && opcion <= clientes.size()) { // Si la opcion es valida
                return clientes.get(opcion - 1); // Retorno el cliente correspondiente
            } else {
                System.out.println("Opcion invalida. Intente nuevamente.");
                seleccionarCliente(banco);
            }
        } while (true);
    } 

    public static int generarNumeroId() {
        Random random = new Random();
        int numeroID = random.nextInt(900) + 100; // Genera un numero aleatorio entre 1000 y 9999
        return numeroID;
    }
}
