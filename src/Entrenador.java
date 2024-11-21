import java.util.Objects;

public class Entrenador extends Persona{
    private Equipo equipoDirigido;

    public Entrenador() {
    }

    public Entrenador(int dni, String nombre, String usuario, String contrasenia, Equipo equipoDirigido) {
        super(dni, nombre, usuario, contrasenia);
        this.equipoDirigido = equipoDirigido;
    }

    public Equipo getEquipoDirigido() {
        return equipoDirigido;
    }

    public void setEquipoDirigido(Equipo equipoDirigido) {
        this.equipoDirigido = equipoDirigido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Entrenador that = (Entrenador) o;
        return Objects.equals(equipoDirigido, that.equipoDirigido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), equipoDirigido);
    }

    @Override
    public String toString() {
        return super.toString() +
                "equipoDirigido=" + equipoDirigido.getNombre();
    }
}
