import java.util.ArrayList;

public class Jugador {
    private String nombre;
    private Posicion posicion;
    private String apellido;
    private ArrayList<String> estadisticas;

    public Jugador(String nombre, String apellido, Posicion posicion) {
        this.nombre = nombre;
        this.apellido=apellido;
        this.posicion = posicion;
        this.estadisticas=new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public ArrayList<String> getEstadisticas() {
        return estadisticas;
    }

    public void setEstadisticas(ArrayList<String> estadisticas) {
        this.estadisticas = estadisticas;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + nombre + '\'' +
                ", posicion=" + posicion +
                ", apellido='" + apellido + '\'' +
                ", estadisticas=" + estadisticas +
                '}';
    }
}
