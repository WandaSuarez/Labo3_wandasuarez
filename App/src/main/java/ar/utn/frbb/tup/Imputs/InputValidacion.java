package ar.utn.frbb.tup.Imputs;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class InputValidacion {
    protected static Scanner scanner = new Scanner(System.in);

    protected static String leerCadena(String mensaje) {
        String valor = "";
        boolean valorValido = false;

        while (!valorValido) { // Bucle que se ejecuta hasta que se ingrese una cadena valida
            System.out.print(mensaje); // Muestra el mensaje
            try {
                valor = scanner.nextLine(); // Lee la cadena
                if (!valor.matches("\\d+")) { // es verdadero si la cadena ingresada no contiene solo digitos.
                    //matches es un metodo de la clase String para verificar si la cadena ingresada coincide con una exprecion regular(cadena de texto)
                    // \\d+ verifica si la cadena ingresada solo contiene dígitos
                    valorValido = true;
                } else {
                    System.out.println("Debe ingresar una cadena de texto. Intente nuevamente.");
                }
            } catch (InputMismatchException e) { //Captura la execpcion si ingresa cualquier otro tipo de dato, q no sea un String o un numero entero
                System.out.println("Entrada invalida. Intente nuevamente.");
                scanner.nextLine(); // Limpia el buffer del scanner
            }
        }

        return valor;
    }

    protected static int leerEntero(String mensaje) {
        int valor = 0;
        boolean valorValido = false;

        while (!valorValido) { // Bucle que se ejecuta hasta que se ingrese una cadena valida
            System.out.print(mensaje);
            try {
                valor = Integer.parseInt(scanner.nextLine()); // Lee el entero e intenta convertir la cadena de texto ingresada por el usuario a un numero entero
                valorValido = true;
            } catch (NumberFormatException e) { // Captura la excepcion si el valor ingresado no es un numero entero
                System.out.println("Debe ingresar un numero entero. Intente nuevamente.");
            }
        }

        return valor;
    }

    protected static double leerDouble(String mensaje) {
        double valor = 0; // Declaro la variable valor como un double para dps cuando intente convertir la entrada del usuario, guardo la varible como un double
        boolean valorValido = false;

        while (!valorValido) { // Bucle que se ejecuta hasta que se ingrese una cadena valida
            System.out.print(mensaje);
            try {
                valor = Double.parseDouble(scanner.nextLine()); // Lee el num double e intenta convertir la cadena de texto ingresada por el usuario a un numero double
                valorValido = true;
            } catch (NumberFormatException e) { // Captura la excepcion si el valor ingresado no es un numero entero
                System.out.println("Debe ingresar un numero decimal. Intente nuevamente.");
            }
        }

        return valor;
    }

    protected static long leerLong(String mensaje) {
        long valor = 0;
        boolean valorValido = false;

        while (!valorValido) {
            System.out.print(mensaje);
            try {
                valor = Long.parseLong(scanner.nextLine()); // Lee el entero e intenta convertir la cadena de texto ingresada por el usuario a un numero long
                valorValido = true;
            } catch (NumberFormatException e) { // Captura la excepcion si el valor ingresado no es un numero entero
                System.out.println("Debe ingresar un numero entero. Intente nuevamente.");
            }
        }

        return valor;
    }
}
