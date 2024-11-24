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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Arbitro arbitro = (Arbitro) o;
        return partidosArbitrados == arbitro.partidosArbitrados;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), partidosArbitrados);
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
            jsonObject.put("dni",this.getDni());
            jsonObject.put("nombre",this.getNombre());
            jsonObject.put("usuario",this.getUsuario());
            jsonObject.put("contrasenia",this.getContrasenia());
            jsonObject.put("estado",this.isEstado());
            jsonObject.put("partidosArbitrados",this.partidosArbitrados);
        }
        catch (JSONException ex){
            ex.printStackTrace();
        }
        return jsonObject;    }

    //@Override
    public static Arbitro deserializar(JSONObject json) {
        Arbitro arbitro=new Arbitro();
        try {
            arbitro.setDni(json.getInt("dni"));
            arbitro.setNombre(json.getString("nombre"));
            arbitro.setUsuario(json.getString("usuario"));
            arbitro.setContrasenia(json.getString("contrasenia"));
            arbitro.setEstado(json.getBoolean("estado"));
            arbitro.setPartidosArbitrados(json.getInt("partidosArbitrados"));
        }
        catch(JSONException ex){
            ex.printStackTrace();
        }
        return arbitro;
    }
}
