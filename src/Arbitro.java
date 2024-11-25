import netscape.javascript.JSObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

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
    public String toString() {
        return super.toString()+
                "partidosArbitrados=" + partidosArbitrados;
    }

    @Override
    public JSONObject serializar() {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject=super.serializar();
            jsonObject.put("partidosArbitrados",this.partidosArbitrados);
        }
        catch (JSONException ex){
            ex.printStackTrace();
        }
        return jsonObject;    }


    public static Arbitro deserializar(JSONObject json) {
        Arbitro arbitro=new Arbitro();
        try {
            arbitro= (Arbitro) Persona.deserializar(json);
            arbitro.setPartidosArbitrados(json.getInt("partidosArbitrados"));
        }
        catch(JSONException ex){
            ex.printStackTrace();
        }
        return arbitro;
    }

}
