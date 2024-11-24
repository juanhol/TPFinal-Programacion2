import netscape.javascript.JSObject;

import java.util.Objects;

public abstract class Persona implements Persistible{
    private int dni;
    private String nombre;
    private String usuario;
    private String contrasenia;
    private boolean estado;

    public Persona() {
    }

    public Persona(int dni, String nombre, String usuario, String contrasenia) {
        this.dni = dni;
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.estado = true;
    }

    ///GETTERS Y SETTERS

    public int getDni() {
        return dni;
    }
    public void setDni(int dni) {
        this.dni = dni;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getContrasenia() {
        return contrasenia;
    }
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return dni == persona.dni;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }

    public boolean iniciarSesion(String usuario, String contrasenia){
        if ((usuario.equals(this.usuario))&&(contrasenia.equals(this.contrasenia)))
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "nombre= " + nombre + '\'' +
                ", usuario= " + usuario + '\'' +
                "dni=" + dni    ;
    }

}
