import org.basex.examples.api.BaseXClient;
import org.basex.examples.api.BaseXClient.Query;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio601 {

    static Scanner sc = new Scanner(System.in);

    public void init(String databaseName) {
        ArrayList<XML601> lista = crea_lista();

        try (BaseXClient session = new BaseXClient("localhost", 1984, "admin", "abc123")) {
            // definir el fichero de entrada
            InputStream bais = new ByteArrayInputStream(lista.get(0).toString().getBytes());
            // Crear una base de datos llamada "baseDatos" e inserto un documento
            session.create(databaseName, bais);
            for (int i = 1; i < lista.size(); i++) {
                bais = new ByteArrayInputStream(lista.get(i).toString().getBytes());
                session.add("ejercicio601/alumno" + i + ".xml", bais);
            }
            String opcion = "";
//            do {
//                String nombre = introducirString("Introduzca el nombre del alumno");
//                String apellidos = introducirString("Introduzca los apellidos");
//                int edad = introducirInt("Introduzca la edad");
//                String correo = introducirString("Introduzca el correo electrónico");
//
//                lista.add(new XML601(nombre, apellidos, edad, correo));
//
//                opcion = introducirString("Desea seguir introduciendo datos (si/no)");
//            } while (!opcion.equalsIgnoreCase("no"));

            System.out.println(session.info());

            String menu = "1. Número total de documentos en el sistema\n" +
                    "2. Media de edad de los alumnos.\n" +
                    "3. Alumno mayor y menor.\n" +
                    "4. Mostrar el nombre de los alumnos ordenado por edad de mayor a menor.\n" +
                    "5. Mostrar el nombre de un alumno de forma aleatoria.\n" +
                    "6. Salir";

            int opcionMenu;
            do {
                opcionMenu = introducirInt("Elige una opcion :\n" + menu);
                switch (opcionMenu) {
                    case 1:
                        String query1 = "count(for $x in db:get('ejercicio601') return $x)";
                        crearConsulta(session, query1);
                        break;
                    case 2:
                        String query2 = "avg(db:get('ejercicio601')/alumno/edad)";
                        crearConsulta(session, query2);
                        break;
                    case 3:
                        String query3 = "let $max := max(db:get('ejercicio601')/alumno/edad)\n" +
                                "let $min := min(db:get('ejercicio601')/alumno/edad)\n" +
                                "return <valor><minimo>{$min} </minimo><maximo>{$max} </maximo></valor>";
                        crearConsulta(session, query3);
                        break;
                    case 4:
                        String query4= "for $empleado in db:get('ejercicio601')/alumno\n" +
                                "order by $empleado/edad descending\n" +
                                "return $empleado/nombre";
                        crearConsulta2(session,query4);
                        break;
                    case 5:
                        String totaldocumentos = "count(for $x in /alumno return $x)";
                        int documentos = obtenerInt(session,totaldocumentos);
                        int numrandom= (int )(Math.random()*documentos+1);
                        System.out.println("numero de documentos : "+documentos+" numero random :"+numrandom);
                        String query5= String.format("let $doc:= db:get('ejercicio601')/alumno\n" +
                                "return $doc[%d]\n",numrandom);
                        crearConsulta(session,query5);
                        break;


                }
            } while (opcionMenu != 6);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int obtenerInt(BaseXClient session,String input){
        try(BaseXClient.Query query = session.query(input)){
            return Integer.parseInt(query.next());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void crearConsulta(BaseXClient session, String input) {
        try (Query query1 = session.query(input)) {
            System.out.println(query1.next());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void crearConsulta2(BaseXClient session, String input) {
        try (Query query1 = session.query(input)) {
            while(query1.more()){

            System.out.println(query1.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<XML601> crea_lista() {
        ArrayList<XML601> info = new ArrayList<XML601>();

        XML601 alumno1 = new XML601("A", "A1", 10, "alumno1@alumno");
        XML601 alumno2 = new XML601("B", "B1", 10, "alumno2@alumno");
        XML601 alumno3 = new XML601("C", "C1", 10, "alumno3@alumno");
        XML601 alumno4 = new XML601("D", "D1", 40, "alumno4@alumno");
        XML601 alumno5 = new XML601("E", "E1", 50, "alumno5@alumno");
        info.add(alumno1);
        info.add(alumno2);
        info.add(alumno3);
        info.add(alumno4);
        info.add(alumno5);

        return info;
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
}

class XML601 {
    private String nombre;
    private String apellidos;
    private int edad;
    private String correo;

    public XML601(String nombre, String apellidos, int edad, String correo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "<alumno>" + "<nombre>" + nombre + "</nombre>" + "<apellidos>" + apellidos + "</apellidos>" + "<edad>" + edad + "</edad>" + "<correo>" + correo + "</correo>" + "</alumno>";
    }


}
