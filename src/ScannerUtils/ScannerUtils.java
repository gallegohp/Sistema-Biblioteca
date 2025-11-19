package ScannerUtils;

import java.util.Scanner;

public class ScannerUtils {

    Scanner scanner = new Scanner(System.in);

    public String leerLinea(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    public int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.print("Entrada invalida. " + mensaje);
            scanner.next(); // Limpiar la entrada invalida
        }
        int valor = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        return valor;
    }
    public byte leerByte(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextByte()) {
            System.out.print("Entrada invalida. " + mensaje);
            scanner.next(); // Limpiar la entrada invalida
        }
        byte valor = scanner.nextByte();
        scanner.nextLine(); // Limpiar el buffer
        return valor;
    }


}
