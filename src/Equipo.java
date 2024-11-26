import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Equipo implements Persistible{
    private String nombre;
    private Listado<Jugador> listadoJugadores;
    private boolean estado;
    private Entrenador entrenador;

    public Equipo() {

    }

    public Equipo(String nombre, Entrenador entrenador) {
        this.nombre = nombre;
        this.entrenador = entrenador;
        this.estado=true;
        this.listadoJugadores=new Listado<>();
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
        return Objects.equals(nombre, equipo.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    public void listarJugadores(){
        listadoJugadores.listarElementos();
    }

    public boolean agregarJugador(Jugador jugador){
        if(jugador != null){
            listadoJugadores.agregarElemento(jugador);
            return true;
        }
        return false;
    }

    public boolean eliminarJugador(Jugador jugador){
        if(jugador != null){
            listadoJugadores.eliminarElemento(jugador);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return " nombre='" + nombre + '\'' +
                ", listadoJugadores=" + listadoJugadores +
                ", entrenador=" + entrenador.getNombre();
    }

    @Override
    public JSONObject serializar() {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("nombre", this.nombre);
            jsonObject.put("estado", this.estado);
            jsonObject.put("Entrenador", this.getEntrenador().serializar());

            // Serializar listado de jugadores
            JSONArray listadoJugadores = new JSONArray();
            for (int i = 0; i < this.listadoJugadores.cantidadElementos(); i++) {
                JSONObject jugador = this.listadoJugadores.getElemento(i).serializar();
                listadoJugadores.put(jugador);
            }
            jsonObject.put("listadoJugadores", listadoJugadores);

        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return jsonObject;
    }

    //@Override
    public static Equipo deserializar(JSONObject json) {
        Equipo equipo = new Equipo();

        try {
            equipo.setNombre(json.getString("nombre"));

            // Deserializar listado de jugadores
            Listado<Jugador> listadoJugadores = new Listado<>();
            JSONArray listadoJugadoresJson = json.getJSONArray("listadoJugadores");

            for (int i = 0; i < listadoJugadoresJson.length(); i++) {
                Jugador jugador = Jugador.deserializar(listadoJugadoresJson.getJSONObject(i));
                listadoJugadores.agregarElemento(jugador);
            }
            equipo.setListadoJugadores(listadoJugadores);

            equipo.setEstado(json.getBoolean("estado"));
            equipo.setEntrenador(Entrenador.deserializar(json.getJSONObject("Entrenador")));

        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return equipo;
    }
}
