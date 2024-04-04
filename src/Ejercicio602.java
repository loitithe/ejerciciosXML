import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio602 {
    static Scanner sc = new Scanner(System.in);

    private ArrayList<Libro> lista_libros;

    public Ejercicio602() {
        this.lista_libros = new ArrayList<>(10000);
        for (int i = 0; i < 10000; i++) {
            Libro libro = new Libro();
            System.out.println(libro);
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
        this.nombre = "Nombre"+obtenerRandomRango(0,19);
        this.apellidos = "Apellido"+obtenerRandomRango(0,19)+" Apellido"+obtenerRandomRango(0,19);
        this.editorial = "Editorial"+obtenerRandomRango(0,100);
        this.paginas = obtenerRandomRango(100,850);
        this.edicionElectronica = obtenerRandomRango(0,1)==0 ;
    }

    public static int obtenerRandomRango(int min, int max) {
        return (int) (Math.random() * (min + max));
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
        return "Libro{" +
                "publicacion=" + publicacion +
                ", edicion=" + edicion +
                ", titulo='" + titulo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", editorial='" + editorial + '\'' +
                ", paginas=" + paginas +
                ", edicionElectronica=" + edicionElectronica +
                '}';
    }
}
