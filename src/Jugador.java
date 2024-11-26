import netscape.javascript.JSObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Objects;

public class Jugador extends Persona{
    private Posicion posicion;
    private boolean tieneEquipo;

    // Constructores
    public Jugador() {
    }

    public Jugador(int dni, String nombre, String usuario, String contrasenia, Posicion posicion) {
        super(dni, nombre, usuario, contrasenia);
        this.posicion = posicion;
        this.tieneEquipo = false;
    }

    public Jugador(int dni, String nombre, String usuario, String contrasenia, Posicion posicion, Boolean tieneEquipo) {
        super(dni, nombre, usuario, contrasenia);
        this.posicion = posicion;
        this.tieneEquipo = tieneEquipo;
    }


    // Getters and Setters
    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public boolean getTieneEquipo() {
        return tieneEquipo;
    }
    public void setTieneEquipo(boolean tieneEquipo) {
        this.tieneEquipo = tieneEquipo;
    }

    // Sobreescrituras
    @Override
    public String toString() {
        return super.toString() +
                "posicion=" + posicion +
                '}';
    }

    @Override
    public JSONObject serializar() {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject=super.serializar();
            jsonObject.put("posicion",this.getPosicion());
            jsonObject.put("tieneEquipo",this.getTieneEquipo());

        }
        catch (JSONException ex){
            ex.printStackTrace();
        }
        return jsonObject;
    }


    public static Jugador deserializar(JSONObject json) {
        Jugador jugador = null;

        try {

            // Deserializar los datos comunes de Persona
            Persona p = Persona.deserializar(json);

            // Crear un nuevo Jugador y copiar los datos
            jugador = new Jugador(
                    p.getDni(),
                    p.getNombre(),
                    p.getUsuario(),
                    p.getContrasenia(),
                    json.getEnum(Posicion.class,"posicion"),
                    json.getBoolean("tieneEquipo")
            );


        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return jugador;
    }

}