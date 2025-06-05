package itq.edu.tap.school.dto;

import java.util.List;

import itq.edu.tap.tools.RandomWordGenerator;

public class FlexGrupo {

    public static void main(String[] args) {
        /** 
         * Lista de estudiantes
         */
        private List <Estudiante> estudiantes;
        
        /**
         * Genera un grupo flexible
         */
        
        public FlexGrupo(int size) {
            estudiantes = new ArrayList<>();
            for (int i = 0; i <size; i++) {
                estudiantes.add (new Estudainte(RandomWordGenerator.generateNoControl(), RamdomWordGenerator.generateRandomWord(), RamdomWordGenerator.generateRandomWord()));
            }

        }

    }

}
