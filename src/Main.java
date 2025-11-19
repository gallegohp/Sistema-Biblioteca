import ScannerUtils.ScannerUtils;
import respository.AutorRespository;


public class Main {
    public static void main(String[] args) {
        ScannerUtils scannerUtils = new ScannerUtils();
        AutorRespository AutorRespository = new AutorRespository();
        boolean MenuActivo = true;

        while (MenuActivo){
            byte eleccion = scannerUtils.leerByte(
                    "\nSeleccione una opcion:\n" +
                            "1. Listar Autores\n" +
                            "2. Insertar Autor\n" +
                            "3. Eliminar Autor\n" +
                            "4. Salir\n" +
                            "Opcion: "
            );
            switch (eleccion){
                case 1:
                    AutorRespository.ListarAutores();
                    break;
                case 2:
                    String nombre = scannerUtils.leerLinea("Ingrese el nombre del autor: ");
                    String nacionalidad = scannerUtils.leerLinea("Ingrese la nacionalidad del autor: ");
                    AutorRespository.InsertarAutor(nombre, nacionalidad);
                    break;
                case 3:
                    int idEliminar = scannerUtils.leerEntero("Ingrese el ID del autor a eliminar: ");
                    AutorRespository.EliminarAutor(idEliminar);
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    MenuActivo = false;
                    break;
                default:
                    System.out.println("Opcion invalida. Por favor, intente de nuevo.");
            }
        }

    }
}