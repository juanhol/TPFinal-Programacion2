import java.awt.*;
import java.security.KeyStore;
import java.util.ArrayList;

public class Equipo {
    private String nombre;
    private ArrayList<Jugador> plantel;
    private Entrenador entrenador;
    private ArrayList<String> estadisticas;

    public Equipo(String nombre, Entrenador entrenador) {
        this.nombre = nombre;
        this.plantel = new ArrayList<>();
        this.entrenador = entrenador;
        this.estadisticas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Jugador> getPlantel() {
        return plantel;
    }

    public void setPlantel(ArrayList<Jugador> plantel) {
        this.plantel = plantel;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public ArrayList<String> getEstadisticas() {
        return estadisticas;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "nombre='" + nombre + '\'' +
                ", plantel=" + plantel +
                ", entrenador=" + entrenador +
                ", estadisticas=" + estadisticas +
                '}';
    }

    public void agregarJugador(Jugador jugador){
        plantel.add(jugador);
    }
}
