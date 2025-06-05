package itq.edu.tap.school;

import java.util.Random;
import java.util.Scanner;

public class Grupo {
    private Estudiante[] estudiantes;
    private static final String[] NOMBRES = {
        "Juan", "María", "Pedro", "Ana", "Luis", "Lucía", "Carlos", "Sofía", "Jorge", "Marta"
    };
    private static final String[] APELLIDOS = {
        "García", "Martínez", "López", "Hernández", "González", "Pérez", "Sánchez", "Ramírez", "Torres", "Flores"
    };
    private static final Random RANDOM = new Random();

    public Grupo(int cantidadEstudiantes) {
        estudiantes = new Estudiante[cantidadEstudiantes];
        for (int i = 0; i < cantidadEstudiantes; i++) {
            estudiantes[i] = new Estudiante();
            estudiantes[i].setNombre(generarNombreAleatorio());
            estudiantes[i].setApellidos(generarApellidosAleatorios());
            estudiantes[i].setNoControl(generarNoControlAleatorio());
        }
    }

    private String generarNombreAleatorio() {
        return NOMBRES[RANDOM.nextInt(NOMBRES.length)];
    }

    private String generarApellidosAleatorios() {
        return APELLIDOS[RANDOM.nextInt(APELLIDOS.length)] + " " + APELLIDOS[RANDOM.nextInt(APELLIDOS.length)];
    }

    private String generarNoControlAleatorio() {
        return String.format("%05d", RANDOM.nextInt(100000));
    }

    public void mostrarEstudiantes() {
        for (Estudiante estudiante : estudiantes) {
            System.out.println("No. Control: " + estudiante.getnoControl() + ", Nombre: " + estudiante.getNombre() + ", Apellidos: " + estudiante.getApellidos());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la cantidad de estudiantes en el grupo: ");
        int cantidadEstudiantes = scanner.nextInt();

        Grupo grupo = new Grupo(cantidadEstudiantes);
        grupo.mostrarEstudiantes();
    }
}