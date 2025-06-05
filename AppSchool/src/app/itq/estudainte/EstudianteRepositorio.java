package app.itq.estudainte;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EstudianteRepositorio {
    private List<Estudiante> estudiantes = new ArrayList<>();
    private int idCounter = 1;

    public void agregarEstudiante(Estudiante estudiante) {
        estudiante.setId(idCounter++);
        estudiantes.add(estudiante);
    }

    public void eliminarEstudiante(int id) {
        estudiantes.removeIf(e -> e.getId() == id);
    }

    public Optional<Estudiante> buscarEstudiante(int id) {
        return estudiantes.stream().filter(e -> e.getId() == id).findFirst();
    }

    public List<Estudiante> obtenerTodos() {
        return new ArrayList<>(estudiantes);
    }

    public void actualizarEstudiante(Estudiante estudiante) {
        eliminarEstudiante(estudiante.getId());
        estudiantes.add(estudiante);
    }
}
