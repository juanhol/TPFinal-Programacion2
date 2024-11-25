import netscape.javascript.JSObject;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Entrenador extends Persona{
    private String equipoDirigido;

    public Entrenador() {
    }

    public Entrenador(int dni, String nombre, String usuario, String contrasenia, String equipoDirigido) {
        super(dni, nombre, usuario, contrasenia);
        this.equipoDirigido = equipoDirigido;
    }

    public String getEquipoDirigido() {
        return equipoDirigido;
    }

    public void setEquipoDirigido(String equipoDirigido) {
        this.equipoDirigido = equipoDirigido;
    }


    @Override
    public String toString() {
        return super.toString() +
                "equipoDirigido=" + equipoDirigido;
    }

    @Override
    public JSONObject serializar() {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject=super.serializar();
            jsonObject.put("equipoDirigido",this.equipoDirigido);
        }
        catch (JSONException ex){
            ex.printStackTrace();
        }
        return jsonObject;
    }


    public static Entrenador deserializar(JSONObject json) {
        Entrenador entrenador=new Entrenador();
        try {
            entrenador= (Entrenador) Persona.deserializar(json);
            entrenador.setEquipoDirigido(json.getString("equipodirigido"));
        }catch (JSONException ex){
            ex.printStackTrace();
        }
        return entrenador;
    }
}
