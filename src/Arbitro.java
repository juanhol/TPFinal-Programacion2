import java.util.Objects;

public class Arbitro extends Persona{
    private int partidosArbitrados;

    public Arbitro() {
    }

    public Arbitro(int dni, String nombre, String usuario, String contrasenia, int partidosArbitrados) {
        super(dni, nombre, usuario, contrasenia);
        this.partidosArbitrados = partidosArbitrados;
    }

    public int getPartidosArbitrados() {
        return partidosArbitrados;
    }

    public void setPartidosArbitrados(int partidosArbitrados) {
        this.partidosArbitrados = partidosArbitrados;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Arbitro arbitro = (Arbitro) o;
        return partidosArbitrados == arbitro.partidosArbitrados;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), partidosArbitrados);
    }

    @Override
    public String toString() {
        return super.toString()+
                "partidosArbitrados=" + partidosArbitrados;
    }
}
