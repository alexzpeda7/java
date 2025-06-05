package itq.edu.tap.school.dto;

import itq.edu.tap.tools.RandomWordGenerator;
import itq.edu.tap.tools.WordGenerator;

public class Grupo {
    private Estudiante[] estudiantes;
    
    /**Constructor para inicializar un grupo*/
    public Grupo () {
        estudiantes = new Estudiante[22];
        estudiantes[0] = new Estudiante();
        estudiantes[0].setApellidos("ACEVEDO PÉREZ");
        estudiantes[0].setNombre("FERNAN EMILIO");
        estudiantes[0].setNoControl("00000");
        
        Estudiante estudiante = new Estudiante();
        estudiante.setApellidos("ZEPEDA QUINTANAR");
        estudiante.setNombre("BRIAN ALEJANDRO");
        estudiante.setNoControl("12345");
        estudiantes[1] = estudiante;
        
        for (int i = 2; i < estudiantes.length; i++) {
            estudiante = new Estudiante();
            estudiante.setApellidos(RandomWordGenerator.generateRandomWord());
            estudiante.setNombre(RandomWordGenerator.generateRandomWord());
            estudiante.setNoControl(RandomWordGenerator.generateNoControl());
        }
        
    }

}