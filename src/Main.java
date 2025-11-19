import ScannerUtils.ScannerUtils;
import respository.AutorRespository;
import respository.LibroRepository;


public class Main {
    public static void main(String[] args) {
        ScannerUtils ScannerUtils = new ScannerUtils();
        LibroRepository LibroRepository = new LibroRepository();
        AutorRespository AutorRespository = new AutorRespository();
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
                    int subOpcion = ScannerUtils.leerEntero(
                            "Seleccione una opción:\n" +
                                    "1. Listar libros\n" +
                                    "2. Agregar libro\n" +
                                    "3. Eliminar libro\n" +
                                    "4. Actualizar libro\n" +
                                    "5. Buscar libro por ISBN\n" +
                                    "6. Filtrar libros por autor\n" +
                                    "7. Volver al menú principal\n" +
                                    "Ingrese el número de la opción deseada: "
                    );
                    switch (subOpcion) {
                        case 1:

                            break;
                        case 2:
                            // Llamar al método para agregar libro
                            respository.LibroRepository.listarLibros();
                            break;
                        case 3:
                            // Llamar al método para eliminar libro

                        case 7:
                            // Volver al menú principal
                            break;
                        default:
                            System.out.println("Opción no válida. Por favor, intente de nuevo.");
                            break;
                    }
                    break;
                case 2: // autores
                    System.out.println("======================================");
                    System.out.println("Has seleccionado Autores.");
                    // Aquí puedes agregar la lógica para manejar autores
                        subOpcion = ScannerUtils.leerEntero(
                                "Seleccione una opción:\n" +
                                        "1. Listar autores\n" +
                                        "2. Agregar autor\n" +
                                        "3. Eliminar autor\n" +
                                        "4. Actualizar autor\n" +
                                        "5. Filtrar autor por ID\n" +
                                        "6. Filtrar autores por NOMBRE\n" +
                                        "7. Filtrar autores por NACIONALIDAD\n" +
                                        "8. Volver al menú principal\n" +
                                        "Ingrese el número de la opción deseada: "
                        );
                    switch (subOpcion) {
                        case 1:
                            System.out.println("--------------------------------------");
                            AutorRespository.listarAutores();
                            System.out.println("--------------------------------------");
                            break;
                        case 2:
                            System.out.println("has seleccionado Agregar autor.");
                            String nombre = ScannerUtils.leerLinea("Ingrese el nombre del autor: ");
                            String nacionalidad = ScannerUtils.leerLinea("Ingrese la nacionalidad del autor: ");
                            AutorRespository.InsertarAutor(nombre, nacionalidad);
                            break;
                        case 3:
                            int idEliminar = ScannerUtils.leerEntero("Ingrese el ID del autor a eliminar: ");
                            AutorRespository.EliminarAutor(idEliminar);
                            break;
                        case 4:
                            int idActualizar = ScannerUtils.leerEntero("Ingrese el ID del autor a actualizar: ");
                            String nuevoNombre = ScannerUtils.leerLinea("Ingrese el nuevo nombre del autor: ");
                            String nuevaNacionalidad = ScannerUtils.leerLinea("Ingrese la nueva nacionalidad del autor: ");
                            AutorRespository.ActualizarAutor(idActualizar, nuevoNombre, nuevaNacionalidad);
                            break;
                        case 5:
                            int idBuscar = ScannerUtils.leerEntero("Ingrese el ID del autor a buscar: ");
                            AutorRespository.BuscarAutorPorId(idBuscar);
                            break;
                        case 6:
                            String nombreBuscar = ScannerUtils.leerLinea("Ingrese el nombre del autor a buscar: ");
                            AutorRespository.BuscarAutorPorNombre(nombreBuscar);
                            break;
                        case 7:
                            String nacionalidadBuscar = ScannerUtils.leerLinea("Ingrese la nacionalidad del autor a buscar: ");
                            AutorRespository.BuscarAutorPorNacionalidad(nacionalidadBuscar);
                            break;
                            case 8:

                            break;
                        default:
                            System.out.println("Opción no válida. Por favor, intente de nuevo.");
                            break;
                    }
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