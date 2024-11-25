import netscape.javascript.JSObject;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Administrador extends Persona {
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
    public String toString() {
        return super.toString() +
                "email='" + email;
    }

    @Override
    public JSONObject serializar() {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject=super.serializar();
            jsonObject.put("email",this.getEmail());
        }
        catch (JSONException ex){
            ex.printStackTrace();
        }
        return jsonObject;
    }


    public static Administrador deserializar(JSONObject json) {
        Administrador admin=new Administrador();
        try {
            admin= (Administrador) Persona.deserializar(json);
            admin.setEmail(json.getString("email"));
        }
        catch(JSONException ex){
            ex.printStackTrace();
        }
        return admin;
    }

}
