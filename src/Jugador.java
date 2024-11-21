import java.util.Objects;

public class Jugador extends Persona{
    private Posicion posicion;

    public Jugador() {
    }

    public Jugador(int dni, String nombre, String usuario, String contrasenia, Posicion posicion) {
        super(dni, nombre, usuario, contrasenia);
        this.posicion = posicion;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Jugador jugador = (Jugador) o;
        return posicion == jugador.posicion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), posicion);
    }

    @Override
    public String toString() {
        return super.toString() +
                "posicion=" + posicion +
                '}';
    }
}
