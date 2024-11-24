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
            jsonObject.put("dni",this.getDni());
            jsonObject.put("nombre",this.getNombre());
            jsonObject.put("usuario",this.getUsuario());
            jsonObject.put("contrasenia",this.getContrasenia());
            jsonObject.put("estado",this.isEstado());
            jsonObject.put("email",this.getEmail());
        }
        catch (JSONException ex){
            ex.printStackTrace();
        }
        return jsonObject;
    }

    //@Override
    public static Administrador deserializar(JSONObject json) {
        Administrador admin=new Administrador();
        try {
            admin.setDni(json.getInt("dni"));
            admin.setNombre(json.getString("nombre"));
            admin.setUsuario(json.getString("usuario"));
            admin.setContrasenia(json.getString("contrasenia"));
            admin.setEstado(json.getBoolean("estado"));
            admin.setEmail(json.getString("email"));
        }
        catch(JSONException ex){
            ex.printStackTrace();
        }
        return admin;
    }
}
