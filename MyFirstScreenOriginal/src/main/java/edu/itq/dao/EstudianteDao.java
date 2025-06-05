package edu.itq.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import edu.itq.dto.Estudiante;
import edu.itq.tools.RandomWordGenerator;

public class EstudianteDao {

    private List<Estudiante> estudiantes;

    public EstudianteDao() {
        this.estudiantes = new ArrayList<>();
        this.estudiantes = inicializarEstudiantes();
    }

    private List<Estudiante> inicializarEstudiantes() {
        Random random = new Random();
        int size = 10 + random.nextInt(20);
        List<Estudiante> lista = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            lista.add(new Estudiante(
                    RandomWordGenerator.generateNoControl(),
                    RandomWordGenerator.generateRandomWord(),
                    RandomWordGenerator.generateRandomWord()));
        }
        return lista;
    }

    public List<Estudiante> consultarEstudiantes() {
        return new ArrayList<>(this.estudiantes);
    }

    public void insertarEstudiante(Estudiante estudiante) {
        this.estudiantes.add(estudiante);
    }

    public void actualizarEstudiante(Estudiante estudiante) {
        Optional<Estudiante> estudianteExistente = this.estudiantes.stream()
            .filter(e -> e.getNoControl().equals(estudiante.getNoControl()))
            .findFirst();
        estudianteExistente.ifPresent(e -> {
            e.setNombre(estudiante.getNombre());
            e.setApellidos(estudiante.getApellidos());
        });
    }

    public void eliminarEstudiante(String noControl) {
        this.estudiantes = this.estudiantes.stream()
            .filter(e -> !e.getNoControl().equals(noControl))
            .collect(Collectors.toList());
    }
}
