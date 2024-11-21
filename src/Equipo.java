import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Equipo {
    private static int idh=0;
    private int id;
    private String nombre;
    private List<Jugador> listadoJugadores;
    private boolean estado;
    private Entrenador entrenador;

    public Equipo() {
        idh++;
        this.id=idh;
        this.listadoJugadores = new ArrayList<>();
        this.estado=true;
    }

    public Equipo(String nombre, Entrenador entrenador) {
        idh++;
        this.id=idh;
        this.nombre = nombre;
        this.entrenador = entrenador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Jugador> getListadoJugadores() {
        return listadoJugadores;
    }

    public void setListadoJugadores(List<Jugador> listadoJugadores) {
        this.listadoJugadores = listadoJugadores;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipo equipo = (Equipo) o;
        return id == equipo.id && estado == equipo.estado && Objects.equals(nombre, equipo.nombre) && Objects.equals(listadoJugadores, equipo.listadoJugadores) && Objects.equals(entrenador, equipo.entrenador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, listadoJugadores, estado, entrenador);
    }

    public String listarJugadores(){
        StringBuilder listado=new StringBuilder();

        for (Jugador jugador: this.listadoJugadores){
            listado.append(jugador.getNombre()).append(", ").append(jugador.getPosicion());;
        }
        if (!listado.isEmpty()) {
            listado.setLength(listado.length() - 2);
        }
        return listado.toString();
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", listadoJugadores=" + this.listarJugadores() +
                ", estado=" + estado +
                ", entrenador=" + entrenador +
                '}';
    }
}
