import java.util.Scanner;

public class Main {
    private static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        int opcion = -1;

        opcion = introducirInt("Menu de ejercicios\n1. Ejercicio601\n2. Ejercicio602\n3. Ejercicio603\n4. Ejercicio604");
        switch (opcion) {
            case 1:
                //  Ejercicio601 ejercicio601 = new Ejercicio601();
                //  ejercicio601.init("ejercicio601");

                break;
            case 2:
                Ejercicio602 ejercicio602 = new Ejercicio602();
                // ejercicio602.crearDatasets();
                // ejercicio602.initPeque();
                // ejercicio602.initGrande();
                ejercicio602.menu();
                break;
            case 3:
                Ejercicio603 ejercicio603 = new Ejercicio603();
                ejercicio603.menu();
                break;
            case 4:
                break;
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