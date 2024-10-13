public class Entrenador {
    private String nombre;
    private String apellido;
    private String experiencia;

    public Entrenador(String nombre, String apellido, String experiencia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.experiencia = experiencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    @Override
    public String toString() {
        return "Entrenador{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", experiencia='" + experiencia + '\'' +
                '}';
    }
}
