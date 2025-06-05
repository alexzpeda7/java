package itq.edu.tap.school;

import itq.edu.tap.tools.WordGenerator;

public class Grupo {
    private Estudiante[] estudiantes;

    public Grupo() {
        estudiantes = new Estudiante[22];
        estudiantes[0] = new Estudiante();
        estudiantes[0].setApellidos("ACEVEDO PEREZ");
        estudiantes[0].setNombre("FERNAN EMILIO");
        estudiantes[0].setNoControl("00000000");
        
        Estudiante estudiante = new Estudiante();
        estudiante.setApellidos("AGUILAR RODRIGUEZ");
        estudiante.setNombre("ALAN EDUARDO");
        estudiante.setNoControl("11111111");
        estudiantes[1] = estudiante;
        
        for (int i = 2; i < estudiantes.length; i++) {
            estudiante = new Estudiante();
            estudiante.setApellidos(WordGenerator.generateWord());
            estudiante.setNombre(WordGenerator.generateWord());
            estudiante.setNoControl("11111111");            
        }
    }
}
