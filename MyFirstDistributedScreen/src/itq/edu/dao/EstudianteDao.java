package itq.edu.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import itq.edu.dto.Estudiante;
import itq.edu.tools.RandomWordGenerator;

/**
 * Clase que implementa el patrón DAO
 * (Data Access Object)
 * CRUD (Create Read Update Delete)
 */
public class EstudianteDao {

    /**
     * Consulta todos los estudiantes.
     * @return Lista de estudiantes.
     */
    public List<Estudiante> consultarEstudiantes() {
        Random random = new Random();
        int size = 10 + random.nextInt(20);
        List<Estudiante> estudiantes = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            estudiantes.add(new Estudiante(
                    RandomWordGenerator.generateNoControl(),
                    RandomWordGenerator.generateRandomWord(),
                    RandomWordGenerator.generateRandomWord()));
        }
        return estudiantes;
    }
}