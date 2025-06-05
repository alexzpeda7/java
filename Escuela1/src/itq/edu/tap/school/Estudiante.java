package itq.edu.tap.school;

/** Patron de diseño DTO (DATA TRANSFER OBJECT).
 *  PATRON: Solucion probada a un problema comun
 */

public class Estudiante {
    
    /** Numero de control del estudiante. */
    private String noControl;

    /**Nombre del estudiante. */
    private String nombre;
    
    /**
     * @param noControl2
     * @param nombre
     * @param apellidos
     */
    public Estudiante(String noControl, String nombre, String apellidos) {
        super();
        this.noControl = noControl;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    /**Apellidos del estudiante. */
    private String apellidos;
    
    
    
    /** Genera un objeto estudiante*/
    public Estudiante() {
        super();
        // TODO Auto-generated constructor stub
    }
    

    /**
     * @return the noControl
     */
    public String getnoControl() {
        return noControl;
    }

    /**
     * @param noControl the noControl to set
     */
    public void setNoControl(String noControl) {
        this.noControl = noControl;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

}
