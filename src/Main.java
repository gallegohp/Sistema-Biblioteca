import ScannerUtils.ScannerUtils;
import model.Libro;
import respository.AutorRespository;
import respository.LibroRespository;


public class Main {
    public static void main(String[] args) {
        ScannerUtils ScannerUtils = new ScannerUtils();
        AutorRespository AutorRespository = new AutorRespository();
        LibroRespository LibroRespository = new LibroRespository();
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
                    System.out.println("======================================");
                    System.out.println("Has seleccionado Libros.");
                    // Aquí puedes agregar la lógica para manejar libros
                    int subOpcion = ScannerUtils.leerEntero(
                            "Seleccione una opción:\n" +
                                    "1. Listar libros\n" +
                                    "2. Agregar libro\n" +
                                    "3. Eliminar libro\n" +
                                    "4. Actualizar libro\n" +
                                    "5. Buscar libro por ISBN\n" +
                                    "6. Filtrar libros por titulo\n" +
                                    "7. Volver al menú principal\n" +
                                    "Ingrese el número de la opción deseada: "
                    );
                    switch (subOpcion) {
                        case 1:
                            System.out.println("--------------------------------------");
                            for (Libro libro : LibroRespository.listarLibros()) {
                                System.out.println("ISBN: " + libro.getIsbn() +
                                        ", Título: " + libro.getTitulo() +
                                        ", Autor ID: " + libro.getAutorId() +
                                        ", Año de Publicación: " + libro.getAñoPublicacion() +
                                        ", Cantidad Total: " + libro.getCantidadTotal() +
                                        ", Cantidad Disponible: " + libro.getCantidadDisponible());
                            }
                            System.out.println("--------------------------------------");
                            break;

                        case 2:
                            System.out.println("---------------------------------------");
                            System.out.println("has seleccionado Agregar libro.");
                            System.out.println("--------------------------------------");
                            String isbn = ScannerUtils.leerLinea("Ingrese el ISBN del libro: ");
                            String titulo = ScannerUtils.leerLinea("Ingrese el título del libro: ");

                            System.out.println("Lista de autores disponibles:");
                            System.out.println("--------------------------------------");
                            AutorRespository.listarAutores();
                            System.out.println("--------------------------------------");

                            System.out.println("Agregar nuevo autor? (s/n): ");
                            String eleccion = ScannerUtils.leerLinea("");
                            boolean Eleccion = eleccion.equalsIgnoreCase("s");
                            if (Eleccion){
                                String nombre = ScannerUtils.leerLinea("Ingrese el nombre del autor: ");
                                String nacionalidad = ScannerUtils.leerLinea("Ingrese la nacionalidad del autor: ");
                                System.out.println("--------------------------------------");
                                AutorRespository.InsertarAutor(nombre, nacionalidad);
                            }
                            AutorRespository.listarAutores();
                            System.out.println("--------------------------------------");
                            int autorId = ScannerUtils.leerEntero("Ingrese el ID del autor: ");
                            System.out.println("--------------------------------------");
                            int añoPublicacion = ScannerUtils.leerEntero("Ingrese el año de publicación: ");
                            System.out.println("--------------------------------------");
                            int cantidadTotal = ScannerUtils.leerEntero("Ingrese la cantidad total de ejemplares: ");
                            System.out.println("--------------------------------------");
                            int cantidadDisponible = ScannerUtils.leerEntero("Ingrese la cantidad disponible de ejemplares: ");
                            System.out.println("--------------------------------------");
                            Libro nuevoLibro = new Libro(isbn, titulo, autorId, añoPublicacion,cantidadTotal, cantidadDisponible);
                            LibroRespository.agregarLibro(nuevoLibro);
                            break;
                        case 3:
                            System.out.println("--------------------------------------");
                            System.out.println("has seleccionado Eliminar libro.");
                            System.out.println("--------------------------------------");
                            System.out.println("Lista de libros disponibles:");
                            for (Libro libro : LibroRespository.listarLibros()) {
                                System.out.println("ISBN: " + libro.getIsbn() +
                                        ", Título: " + libro.getTitulo() +
                                        ", Autor ID: " + libro.getAutorId() +
                                        ", Año de Publicación: " + libro.getAñoPublicacion() +
                                        ", Cantidad Disponible: " + libro.getCantidadDisponible());
                                System.out.println("--------------------------------------");
                            }
                            String isbnEliminar = ScannerUtils.leerLinea("Ingrese el ISBN del libro a eliminar: ");
                            LibroRespository.eliminarLibro(isbnEliminar);

                        case 4:
                            System.out.println("======================================");
                            System.out.println("Has seleccionado Actualizar libro.");
                            System.out.println("Libros disponibles:");

                            for (Libro libro : LibroRespository.listarLibros()) {
                                System.out.println("ISBN: " + libro.getIsbn() +
                                        ", Título: " + libro.getTitulo() +
                                        ", Autor ID: " + libro.getAutorId() +
                                        ", Año de Publicación: " + libro.getAñoPublicacion() +
                                        ", Cantidad Total: " + libro.getCantidadTotal() +
                                        ", Cantidad Disponible: " + libro.getCantidadDisponible());
                            }

                            String isbnActualizar = ScannerUtils.leerLinea("Ingrese el ISBN del libro que desea actualizar: ");

                            // 3. Buscar el libro en la base de datos
                            Libro libroAEditar = LibroRespository.buscarLibroPorIsbn(isbnActualizar);

                            if (libroAEditar != null) {

                                boolean editando = true;
                                while (editando) {
                                    System.out.println("\nLibro a editar: " + libroAEditar.getTitulo());
                                    System.out.println("Seleccione el dato a modificar:");
                                    System.out.println("1. Título (Actual: " + libroAEditar.getTitulo() + ")");
                                    System.out.println("2. ID Autor (Actual: " + libroAEditar.getAutorId() + ")");
                                    System.out.println("3. Año de Publicación (Actual: " + libroAEditar.getAñoPublicacion() + ")");
                                    System.out.println("4. Cantidad Total (Actual: " + libroAEditar.getCantidadTotal() + ")");
                                    System.out.println("5. Cantidad Disponible (Actual: " + libroAEditar.getCantidadDisponible() + ")");
                                    System.out.println("6. Guardar Cambios y Salir");
                                    System.out.println("7. Descartar Cambios y Salir");

                                    int opcionEdicion = ScannerUtils.leerEntero("Ingrese el número del campo a editar: ");

                                    switch (opcionEdicion) {
                                        case 1:
                                            String nuevoTitulo = ScannerUtils.leerLinea("Nuevo Título: ");
                                            libroAEditar.setTitulo(nuevoTitulo);
                                            System.out.println("Título modificado temporalmente.");
                                            break;
                                        case 2:
                                            System.out.println("Autores disponibles:");
                                            AutorRespository.listarAutores();
                                            int nuevoAutorId = ScannerUtils.leerEntero("Nuevo ID Autor: ");
                                            libroAEditar.setAutorId(nuevoAutorId);
                                            System.out.println("ID Autor modificado temporalmente.");
                                            break;
                                        case 3:
                                            int nuevoAnio = ScannerUtils.leerEntero("Nuevo Año de Publicación: ");
                                            libroAEditar.setAñoPublicacion(nuevoAnio);
                                            System.out.println("Año de Publicación modificado temporalmente.");
                                            break;
                                        case 4:
                                            int nuevaCantidadTotal = ScannerUtils.leerEntero("Nueva Cantidad Total: ");
                                            libroAEditar.setCantidadTotal(nuevaCantidadTotal);
                                            System.out.println("Cantidad Total modificado temporalmente.");
                                            break;
                                        case 5:
                                            int nuevaCantidadDisponible = ScannerUtils.leerEntero("Nueva Cantidad Disponible: ");
                                            libroAEditar.setCantidadDisponible(nuevaCantidadDisponible);
                                            System.out.println("Cantidad Disponible modificado temporalmente.");
                                            break;
                                        case 6:
                                            LibroRespository.actualizarLibro(libroAEditar);
                                            editando = false;
                                            break;
                                        case 7:
                                            System.out.println("Cambios descartados.");
                                            editando = false;
                                            break;
                                        default:
                                            System.out.println("Opción no válida.");
                                            break;
                                    }
                                }
                            } else {
                                System.out.println("Libro con ISBN " + isbnActualizar + " no encontrado.");
                            }
                            System.out.println("======================================");
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
                            System.out.println("--------------------------------------");
                            String nombre = ScannerUtils.leerLinea("Ingrese el nombre del autor: ");
                            String nacionalidad = ScannerUtils.leerLinea("Ingrese la nacionalidad del autor: ");
                            System.out.println("--------------------------------------");
                            AutorRespository.InsertarAutor(nombre, nacionalidad);
                            break;
                        case 3:
                            System.out.println("has seleccionado Eliminar autor.");
                            System.out.println("--------------------------------------");
                            int idEliminar = ScannerUtils.leerEntero("Ingrese el ID del autor a eliminar: ");
                            AutorRespository.EliminarAutor(idEliminar);
                            break;
                        case 4:
                            System.out.println("has seleccionado Actualizar autor.");
                            System.out.println("--------------------------------------");
                            int idActualizar = ScannerUtils.leerEntero("Ingrese el ID del autor a actualizar: ");
                            System.out.println("--------------------------------------");
                            String nuevoNombre = ScannerUtils.leerLinea("Ingrese el nuevo nombre del autor: ");
                            String nuevaNacionalidad = ScannerUtils.leerLinea("Ingrese la nueva nacionalidad del autor: ");
                            AutorRespository.ActualizarAutor(idActualizar, nuevoNombre, nuevaNacionalidad);
                            break;
                        case 5:
                            System.out.println("--------------------------------------");
                            int idBuscar = ScannerUtils.leerEntero("Ingrese el ID del autor a buscar: ");
                            AutorRespository.BuscarAutorPorId(idBuscar);
                            break;
                        case 6:
                            System.out.println("--------------------------------------");
                            String nombreBuscar = ScannerUtils.leerLinea("Ingrese el nombre del autor a buscar: ");
                            AutorRespository.BuscarAutorPorNombre(nombreBuscar);
                            break;
                        case 7:
                            System.out.println("--------------------------------------");
                            String nacionalidadBuscar = ScannerUtils.leerLinea("Ingrese la nacionalidad del autor a buscar: ");
                            AutorRespository.BuscarAutorPorNacionalidad(nacionalidadBuscar);
                            break;
                            case 8:
                            // Volver al menú principal
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