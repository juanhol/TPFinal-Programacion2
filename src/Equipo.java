import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Equipo {
    private static int idh=0;
    private int id;
    private String nombre;
    private Listado<Jugador> listadoJugadores;
    private boolean estado;
    private Entrenador entrenador;

    public Equipo() {
        idh++;
        this.id=idh;
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

    public Listado<Jugador> getListadoJugadores() {
        return listadoJugadores;
    }

    public void setListadoJugadores(Listado<Jugador> listadoJugadores) {
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

    public void listarJugadores(){
        listadoJugadores.listarElementos();
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", listadoJugadores=" + listadoJugadores +
                ", entrenador=" + entrenador.getNombre();
    }
}
