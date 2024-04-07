import org.basex.examples.api.BaseXClient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Ejercicio603 {

    private static Scanner sc;

    private static void listarBd(BaseXClient session) {
        try {
            BaseXClient.Query query = session.query("db.list()");
            StringBuilder sb = new StringBuilder();
            int contador = 0;
            while (query.more()) {
                contador++;
                sb.append(contador + ". " + query.next() + "\n");
            }
            System.out.println("Bases de datos disponibles\n" + sb);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean existeBaseDatos(BaseXClient session, String nombreBd) {
        try {
            BaseXClient.Query query = session.query("db:exists('" + nombreBd + "')");
            return Boolean.parseBoolean(query.execute());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void crearDb(BaseXClient session, String nombreBd) {
        if (!existeBaseDatos(session, nombreBd)) {
            try (BaseXClient.Query query = session.query("create db '" + nombreBd + "'")) {
                session.execute("create db " + nombreBd);
                System.out.println("Base de datos creada");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else System.out.println(existeBaseDatos(session, nombreBd));
    }


    private static void seleccionarDb(BaseXClient session) {
        StringBuilder sb = new StringBuilder();
        int contador = 0;
        try (BaseXClient.Query query = session.query("db:list()")) {
            while (query.more()) {
                contador++;
                sb.append(contador + " . " + query.next() + "\n");
            }
            System.out.println("Bases de datos disponibles:\n" + sb.toString());
            int opcion = introducirInt("Seleccione una base de datos para trabajar: ", contador);
            String dbName = sb.toString().split("\n")[opcion - 1].split("\\.")[1].trim();
            System.out.println("Ha seleccionado la base de datos: " + dbName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void eliminarDb(BaseXClient session, String nombreBd) {
        if (existeBaseDatos(session, nombreBd)) {
            try (BaseXClient.Query query = session.query("db:get('" + nombreBd + "')")) {
                session.execute("drop db " + nombreBd);
                System.out.println("Base datos " + nombreBd + " eliminada .");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static void menu() {
        sc = new Scanner(System.in);
        int opcion = -1;
        try (BaseXClient session = new BaseXClient("localhost", 1984, "admin", "abc123")) {
            opcion = introducirInt("GESTION BASES DE DATOS \n1. GESTION BBDD\n2. GESTION DOCUMENTOS\n0. SALIR");
            switch (opcion) {
                case 1:
                    menuBd(session);
                    break;
                case 2:
                    menuDocumentos(session);
                    break;
                case 0 :
                    System.exit(1);
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void menuDocumentos(BaseXClient session) {
        int opcion = -1;
        opcion = introducirInt("\n1. Crear una estructura XML para los documentos de esa base de datos.\n" +
                "2. Añadir nuevos documentos siguiendo la estructura XML. \n" +
                "3. Modificar los valores de un documento.\n" +
                "4. Eliminar un documento.");


    }

    public static void menuBd(BaseXClient session) {
        int opcion = -1;
        opcion = introducirInt("GESTION BASES DE DATOS \n1. CREAR BBDD\n2. SELECCIONAR BBDD\n3. ELIMINAR BBDD\n4. SALIR");
        switch (opcion) {
            case 1:
                String nombreDb = introducirString("NOMBRE DE LA BBDD");
                crearDb(session, nombreDb);
                break;
            case 2:
                seleccionarDb(session);
                break;
            case 3:
                eliminarDb(session, introducirString("NOMBRE DE LA DB A ELIMINAR"));
                break;
            case 4:
                break;
        }
    }

    private static String introducirString(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje + ": ");
                return sc.next();
            } catch (Exception e) {
                System.out.println("Error al introducir los datos. Vuelva a introducirlos.");
            }
        }
    }

    private static int introducirInt(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje + ": ");
                return sc.nextInt();
            } catch (Exception e) {
                System.out.println("Error al introducir los datos. Vuelva a introducirlos.");
            }
        }
    }

    private static int introducirInt(String mensaje, int max) {
        int opcion;
        do {
            System.out.print(mensaje);
            while (!sc.hasNextInt()) {
                System.out.println("Por favor, ingrese un número válido.");
                System.out.print(mensaje);
                sc.next();
            }
            opcion = sc.nextInt();
            if (opcion < 1 || opcion > max) {
                System.out.println("Por favor, ingrese un número entre 1 y " + max + ".");
            }
        } while (opcion < 1 || opcion > max);
        return opcion;
    }


}
