import org.basex.examples.api.BaseXClient;
import org.basex.examples.api.BaseXClient.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio602 {
    static Scanner sc = new Scanner(System.in);
    String ficheropeque = "ejercicio602Pequenos";
    String ficherogrande = "ejercicio602Grande";
    private static ArrayList<Libro> lista_libros;

    public Ejercicio602() {

        this.lista_libros = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            Libro libro = new Libro();
            this.lista_libros.add(libro);
            System.out.println(libro);
        }
    }

    public int recogerIntConsulta(BaseXClient session, String consulta) {
        try (Query query = session.query(consulta)) {
            return Integer.parseInt(query.next());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void crearConsulta(BaseXClient session, String consulta) {
        try (Query query = session.query(consulta)) {
            while (query.more()) {
                System.out.println(query.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void menu() {
        String query = "";
        BaseXClient session = null;
        try {
            session = new BaseXClient("localhost", 1984, "admin", "abc123");

            String mensaje = "1.Número total de documentos.\n" +
                    "2.Número de libros publicados antes de 2009\n" +
                    "3.Número de libros que escribió un determinado autor\n" +
                    "4.Número medio de palabras de todos los libros\n" +
                    "5.Número medio de palabras de los libros publicados después de 2008 y escritos por un determinado autor.\n0.Salir";
            int option = -1;
            do {
                option = introducirInt(mensaje);
                switch (option) {
                    case 1:
                        medirTiempoConsultaInt(session, "count(for $x in db:open('" + ficheropeque + "') return $x) ", "Consulta 1 - Fichero Pequeño:");

                        medirTiempoConsultaInt(session, "count(for $x in db:get('" + ficherogrande + "') return $x)", "Consulta 1 - Fichero Grande:");
                        break;
                    case 2:
                        medirTiempoConsultaInt(session, "count(for $x in db:open('" + ficheropeque + "')//libro[publicacion < 2009]return $x) ", "Consulta 2 - Fichero Pequeño:");
                        medirTiempoConsultaInt(session, "count(for $x in db:get('" + ficherogrande + "')//libro[publicacion<2009]return $x)", "Consulta 2 - Fichero Grande:");
                        break;
                    case 3:
                        //TODO DEVUELVE SIEMPRE 0 // "count(for $i in db:get('" + nombreDatasetGrande + "')/biblioteca/libro where $i/autor/nombre='Nombre" + autor +
                        //                    "' and contains($i/autor/apellidos, 'Apellido" + primerApellido + "') return $i)";
                        int autor = introducirInt("introduce el numero del autor a buscar");
                        int apellido = introducirInt("introduce el numero de primer apellido");
                        medirTiempoConsultaInt(session, "count(for $i in db:get('" + ficheropeque + "')//libro[ nombre='Nombre" + autor + "' and contains(apellidos, '" + apellido + "') ] return $i)\n", "Consulta 3 - Fichero pequeño");

                        medirTiempoConsultaInt(session, "count(for $i in db:get('" + ficherogrande + "')//libro[ nombre='Nombre" + autor + "' and contains(apellidos, '" + apellido + "') ] return $i)\n", "Consulta 3 - Fichero grande");

                        break;
                    case 4:
                        //avg(for $x in db:get('ejercicio602Pequenos')//libro/paginas  return $x )
                        medirTiempoConsultaString(session, "avg(for $x in db:get('" + ficheropeque + "')//libro/paginas  return $x)", "Consulta 4 - Fichero pequeño");
                        medirTiempoConsultaString(session, "avg(for $x in db:get('" + ficherogrande + "')//libro/paginas  return $x)", "Consulta 4 - Fichero grande");

                        break;
                    case 5:
                        autor = introducirInt("introduce el numero del autor a buscar");
                        query = "let $autor := 'Nombre" + autor + "' let $año := 2008 let $libros := db:get('"+ficheropeque+"')//libro[publicacion > $año and nombre = $autor] " +
                                "let $totalPalabras := sum(for $libro in $libros return string-length($libro/titulo)) " +
                                "return $totalPalabras div count($libros)";
                        medirTiempoConsultaString(session, query, "Consulta 5 - Fichero pequeno");
                        query = "let $autor := 'Nombre" + autor + "' let $año := 2008 let $libros := db:get('"+ficherogrande+"')//libro[publicacion > $año and nombre = $autor] " +
                                "let $totalPalabras := sum(for $libro in $libros return string-length($libro/titulo)) " +
                                "return $totalPalabras div count($libros)";
                        medirTiempoConsultaString(session, query, "Consulta 5 - Fichero grande");
                    case 0:
                        break;
                }
            } while (option != 0);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void medirTiempoConsultaInt(BaseXClient session, String consulta, String mensaje) {
        long tiempoInicio = System.nanoTime();
        int resultado = recogerIntConsulta(session, consulta);

        long tiempoFin = System.nanoTime();
        long tiempoTotal = tiempoFin - tiempoInicio;
        System.out.println(mensaje);
        System.out.println("Resultado: " + resultado + ", Tiempo: " + tiempoTotal + " nanosegundos");
    }

    public void medirTiempoConsultaDouble(BaseXClient session, String consulta, String mensaje) {
        long tiempoInicio = System.nanoTime();
        double resultado = recogerIntConsulta(session, consulta);

        long tiempoFin = System.nanoTime();
        long tiempoTotal = tiempoFin - tiempoInicio;
        System.out.println(mensaje);
        System.out.println("Resultado: " + resultado + ", Tiempo: " + tiempoTotal + " nanosegundos");
    }

    public void medirTiempoConsultaString(BaseXClient session, String consulta, String mensaje) {
        long tiempoInicio = System.nanoTime();
        long tiempoFin = System.nanoTime();
        long tiempoTotal = tiempoFin - tiempoInicio;

        System.out.println(mensaje);
        crearConsulta(session, consulta);
        System.out.println("Tiempo: " + tiempoTotal + " nanosegundos");
    }

    public void crearDatasets() {

        try (BaseXClient session = new BaseXClient("localhost", 1984, "admin", "abc123")) {

            InputStream bais = new ByteArrayInputStream(lista_libros.get(0).toString().getBytes());

            session.create("ejercicio602Pequenos", bais);

            for (int i = 1; i < lista_libros.size(); i++) {
                bais = new ByteArrayInputStream(lista_libros.get(i).toString().getBytes());
                session.add("ejercicio602Pequenos" + "/libro" + i + ".xml", bais);
            }
        } catch (Exception ignored) {
        }

        try (BaseXClient session = new BaseXClient("localhost", 1984, "admin", "abc123")) {
            // Crear un StringBuilder para construir el documento XML completo con todos los libros
            StringBuilder sb = new StringBuilder();
            sb.append("<biblioteca>");
            for (Libro libro : lista_libros) {
                sb.append(libro.toString());
            }
            sb.append("</biblioteca>");

            // Convertir el StringBuilder a InputStream
            InputStream bais = new ByteArrayInputStream(sb.toString().getBytes());

            // Crear la base de datos con el documento completo
            session.create("ejercicio602Grande", bais);
        } catch (Exception e) {
            e.printStackTrace();
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


}

class Libro {
    private int publicacion;
    private int edicion;
    private String titulo;
    private String nombre;
    private String apellidos;
    private String editorial;
    private int paginas;
    private boolean edicionElectronica;

    public Libro() {
        this.publicacion = obtenerRandomRango(2000, 2014);
        this.edicion = obtenerRandomRango(1, 20);
        this.titulo = "Titulo" + obtenerRandomRango(1, 20000);
        this.nombre = "Nombre" + obtenerRandomRango(0, 19);
        this.apellidos = "Apellido" + obtenerRandomRango(0, 19) + " Apellido" + obtenerRandomRango(0, 19);
        this.editorial = "Editorial" + obtenerRandomRango(0, 100);
        this.paginas = obtenerRandomRango(100, 850);
        this.edicionElectronica = obtenerRandomRango(0, 2) == 0;
    }

    public static int obtenerRandomRango(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }


    public int getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(int publicacion) {
        this.publicacion = publicacion;
    }

    public int getEdicion() {
        return edicion;
    }

    public void setEdicion(int edicion) {
        this.edicion = edicion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public boolean isEdicionElectronica() {
        return edicionElectronica;
    }

    public void setEdicionElectronica(boolean edicionElectronica) {
        this.edicionElectronica = edicionElectronica;
    }

    @Override
    public String toString() {
        return "<libro>\n\t" +
                "<publicacion>" + publicacion + "</publicacion>\n\t" +
                " <edicion>" + edicion + "</edicion>\n\t" +
                "<titulo>" + titulo + "</titulo>\n\t" +
                "<nombre>" + nombre + "</nombre>\n\t" +
                "<apellidos>" + apellidos + "</apellidos>\n\t" +
                "<editorial>" + editorial + "</editorial>\n\t" +
                "<paginas>" + paginas + "</paginas>\n\t" +
                "<edicionElectronica>" + edicionElectronica + "</edicionElectronica>\n" +
                "</libro>\n";
    }


}

//public void initPeque() {
//        try(BaseXClient session = new BaseXClient("localhost", 1984, "admin", "abc123")) {
//
//            InputStream bais = new ByteArrayInputStream(this.lista_libros.get(0).toString().getBytes());
//
//            session.create("ejercicio602/", bais);
//
//            for (int i = 1; i < this.lista_libros.size(); i++) {
//                bais = new ByteArrayInputStream(this.lista_libros.get(i).toString().getBytes());
//                session.add("ejercicio602FicherosPequenos/libro" + i + ".xml", bais);
//            }
//        }catch (Exception ignored){}
//
//    }
//
//    public void initGrande() {
//        try {
//            BaseXClient session = new BaseXClient("localhost", 1984, "admin", "abc123");
//            StringBuilder sb= new StringBuilder();
//            sb.append("<libros>");
//            for (Libro listaLibro : lista_libros) {
//                sb.append(listaLibro.toString());
//            }
//            sb.append("</libros>");
//            InputStream bais = new ByteArrayInputStream(sb.toString().getBytes());
//            session.create("ejercicio602FicheroGrande", bais);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

