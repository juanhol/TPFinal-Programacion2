import java.util.Objects;

public class Administrador extends Persona{
    private String email;

    public Administrador() {
    }

    public Administrador(int dni, String nombre, String usuario, String contrasenia, String email) {
        super(dni, nombre, usuario, contrasenia);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Administrador that = (Administrador) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email);
    }

    @Override
    public String toString() {
        return super.toString() +
                "email='" + email;
    }
}
