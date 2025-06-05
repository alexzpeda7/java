package app.itq.estudainte;

import java.util.List;
import java.util.Optional;

public class EstudianteServicio {
    private EstudianteRepositorio repositorio = new EstudianteRepositorio();

    public void agregarEstudiante(Estudiante estudiante) {
        repositorio.agregarEstudiante(estudiante);
    }

    public void eliminarEstudiante(int id) {
        repositorio.eliminarEstudiante(id);
    }

    public Optional<Estudiante> buscarEstudiante(int id) {
        return repositorio.buscarEstudiante(id);
    }

    public List<Estudiante> obtenerTodos() {
        return repositorio.obtenerTodos();
    }

    public void actualizarEstudiante(Estudiante estudiante) {
        repositorio.actualizarEstudiante(estudiante);
    }
}
