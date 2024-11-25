import netscape.javascript.JSObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

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
        }
        catch (JSONException ex){
            ex.printStackTrace();
        }
        return jsonObject;
    }


    public static Jugador deserializar(JSONObject json) {
        Jugador jugador=new Jugador();
        try {
            jugador= (Jugador) Persona.deserializar(json);
            jugador.setPosicion(json.getEnum(Posicion.class,"posicion"));

        }catch (JSONException ex){
            ex.printStackTrace();
        }
        return jugador;

    }
}
