import ScannerUtils.ScannerUtils;
import respository.AutorRespository;


public class Main {
    public static void main(String[] args) {
        ScannerUtils ScannerUtils = new ScannerUtils();
        boolean EstaEjecutando = true;
        while (EstaEjecutando) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Libros");
            System.out.println("2. Autores");
            System.out.println("3. Préstamos");
            System.out.println("4. Salir");

            int opcion = ScannerUtils.leerEntero("Ingrese el número de la opción deseada: ");

            switch (opcion) {
                case 1:
                    System.out.println("Has seleccionado Libros.");
                    // Aquí puedes agregar la lógica para manejar libros
                    break;
                case 2:
                    System.out.println("Has seleccionado Autores.");
                    // Aquí puedes agregar la lógica para manejar autores
                    break;
                case 3:
                    System.out.println("Has seleccionado Préstamos.");
                    // Aquí puedes agregar la lógica para manejar préstamos
                    break;
                case 4:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    EstaEjecutando = false;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }

        }
    }
}