package itq.edu.tap.school;

/**
 * Patrón de diseño DTO (Data Transfer Object).
 * Patrón: Solución probada a un problema común.
 */
public class Estudiante extends Object {

    /** Número de control del estudiante. */
    private String noControl;

    /** Nombre del estudiante. */
    private String nombre;
    /**
     * @param noControl
     * @param nombre
     * @param apellidos
     */
    public Estudiante(String noControl, String nombre, String apellidos) {
        super();
        this.noControl = noControl;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }
    /** Apellidos del estudiante. */
    private String apellidos;

    /**
     * Genera un objeto estudiante.
     */
    public Estudiante() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @return the noControl
     */
    public String getNoControl() {
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
