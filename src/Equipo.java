import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Equipo implements Persistible{
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
        this.listadoJugadores=new Listado<>();
    }

    public Equipo(String nombre, Entrenador entrenador) {
        idh++;
        this.id=idh;
        this.nombre = nombre;
        this.entrenador = entrenador;
        this.listadoJugadores=new Listado<>();
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

    @Override
    public JSONObject serializar() {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("id",this.id);
            jsonObject.put("nombre",this.nombre);
            JSONArray listadoJugadores=new JSONArray();
            for (int i = 0; i < this.listadoJugadores.cantidadElementos(); i++) {
                JSONObject jugador=this.listadoJugadores.getElemento(i).serializar();
                listadoJugadores.put(jugador);
            }
            jsonObject.put("listadoJugadores",listadoJugadores);
            jsonObject.put("estado",this.estado);
            jsonObject.put("Entrenador",this.getEntrenador().serializar());
        }catch(JSONException ex){
            ex.printStackTrace();
        }
        return jsonObject;
    }

    //@Override
    public static Equipo deserializar(JSONObject json) {
        Equipo equipo=new Equipo();
        try {
            equipo.setId(json.getInt("id"));
            equipo.setNombre(json.getString("nombre"));
            Listado <Jugador> listadoJugadores = new Listado<>();
            JSONArray listadoJugadoresJson=json.getJSONArray("listadoJugadores");
            for (int i = 0; i < listadoJugadoresJson.length(); i++) {
                Jugador jugador=(Jugador)Jugador.deserializar(listadoJugadoresJson.getJSONObject(i));
                listadoJugadores.agregarElemento(jugador);
            }
            equipo.setListadoJugadores(listadoJugadores);
            equipo.setEstado(json.getBoolean("estado"));
            equipo.setEntrenador(Entrenador.deserializar(json.getJSONObject("Entrenador")));
        }catch (JSONException ex){
            ex.printStackTrace();
        }
        return equipo;
    }
}
