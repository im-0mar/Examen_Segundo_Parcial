package org.omar;

import java.util.Scanner;

public class Menu {

    // Variables globales (scope de clase)
    static Scanner sc = new Scanner(System.in);
    static final int MAX = 50;
    static final double APROBATORIA = 70.0;
    static String[] nombres = new String[MAX];
    static double[] calificaciones = new double[MAX];
    static int total = 0;

    public static void main(String[] args) {
        int opcion = 0;                                   // variable local

        while (opcion != 3) {                             // while
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {                             // switch / case / break / default
                case 1:
                    registrarCalificaciones();
                    break;
                case 2:
                    mostrarEstadisticas();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
        }
        sc.close();
    }

    // Procedimiento (void)
    public static void mostrarMenu() {
        System.out.println();
        System.out.println("=====================================");
        System.out.println("       CONTROL DE CALIFICACIONES");
        System.out.println("=====================================");
        System.out.println("1. Registrar calificaciones");
        System.out.println("2. Mostrar estadísticas");
        System.out.println("3. Salir");
        System.out.println("=====================================");
    }

    // Funciones de entrada (con return y parámetro)
    public static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            if (sc.hasNextInt()) {
                int numero = sc.nextInt();                // nextInt()
                sc.nextLine();                            // limpia el salto de línea
                return numero;
            }
            System.out.println("Ingrese un número entero válido.");
            sc.nextLine();
        }
    }

    public static double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            if (sc.hasNextDouble()) {
                double numero = sc.nextDouble();          // nextDouble()
                sc.nextLine();
                return numero;
            }
            System.out.println("Ingrese un número válido.");
            sc.nextLine();
        }
    }

    // Procedimiento (void)
    public static void registrarCalificaciones() {
        int cantidad = leerEntero("¿Cuántas calificaciones desea registrar? (1-" + MAX + "): ");

        if (cantidad <= 0 || cantidad > MAX) {            // relacionales + lógicos
            System.out.println("Cantidad inválida.");
            return;
        }

        total = 0;

        for (int i = 0; i < cantidad; i++) {              // for (variable de bloque)
            System.out.print("Nombre del alumno " + (i + 1) + ": ");
            String nombre = sc.nextLine();                // nextLine()

            double nota;
            do {                                          // do-while
                nota = leerDouble("Calificación de " + nombre + " (0-100): ");
                if (nota < 0 || nota > 100) {
                    System.out.println("Debe estar entre 0 y 100.");
                }
            } while (nota < 0 || nota > 100);

            nombres[total] = nombre;
            calificaciones[total] = nota;
            total++;
        }
        System.out.println("Calificaciones registradas correctamente.");
    }

    // Procedimiento (void)
    public static void mostrarEstadisticas() {
        if (total == 0) {
            System.out.println("Primero registre calificaciones (opción 1).");
            return;
        }

        double promedio = calcularPromedio();
        int posMayor = posicionMayor();
        int posMenor = posicionMenor();
        int aprobados = contarAprobados(APROBATORIA);     // argumento
        int reprobados = total - aprobados;
        char letra = obtenerLetra(promedio);
        boolean todosAprobaron = (reprobados == 0);

        System.out.println();
        System.out.println("----------- ESTADÍSTICAS -----------");
        System.out.println("Total de alumnos: " + total);
        System.out.printf("Promedio: %.2f%n", promedio);
        System.out.println("Calificación mayor: " + calificaciones[posMayor] + " (" + nombres[posMayor] + ")");
        System.out.println("Calificación menor: " + calificaciones[posMenor] + " (" + nombres[posMenor] + ")");
        System.out.println("Aprobados: " + aprobados);
        System.out.println("Reprobados: " + reprobados);
        System.out.println("Desempeño del grupo: " + letra);

        if (todosAprobaron) {
            System.out.println("¡Todos aprobaron!");
        }
    }

    // Funciones (con return)
    public static double calcularPromedio() {
        double suma = 0;
        for (int i = 0; i < total; i++) {
            suma += calificaciones[i];                    // asignación compuesta
        }
        return suma / total;
    }

    public static int posicionMayor() {
        int pos = 0;
        for (int i = 1; i < total; i++) {
            if (calificaciones[i] > calificaciones[pos]) {
                pos = i;
            }
        }
        return pos;
    }

    public static int posicionMenor() {
        int pos = 0;
        for (int i = 1; i < total; i++) {
            if (calificaciones[i] < calificaciones[pos]) {
                pos = i;
            }
        }
        return pos;
    }

    public static int contarAprobados(double minima) {    // parámetro
        int contador = 0;
        for (int i = 0; i < total; i++) {
            if (calificaciones[i] >= minima) {
                contador++;
            }
        }
        return contador;
    }

    public static char obtenerLetra(double promedio) {
        if (promedio >= 90) {
            return 'A';
        } else if (promedio >= 80) {
            return 'B';
        } else if (promedio >= 70) {
            return 'C';
        } else {
            return 'F';
        }
    }
}