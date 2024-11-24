import netscape.javascript.JSObject;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Entrenador extends Persona{
    private Equipo equipoDirigido;

    public Entrenador() {
    }

    public Entrenador(int dni, String nombre, String usuario, String contrasenia, Equipo equipoDirigido) {
        super(dni, nombre, usuario, contrasenia);
        this.equipoDirigido = equipoDirigido;
    }

    public Equipo getEquipoDirigido() {
        return equipoDirigido;
    }

    public void setEquipoDirigido(Equipo equipoDirigido) {
        this.equipoDirigido = equipoDirigido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Entrenador that = (Entrenador) o;
        return Objects.equals(equipoDirigido, that.equipoDirigido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), equipoDirigido);
    }

    @Override
    public String toString() {
        return super.toString() +
                "equipoDirigido=" + equipoDirigido.getNombre();
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
            jsonObject.put("equipoDirigido",this.equipoDirigido.serializar());
        }
        catch (JSONException ex){
            ex.printStackTrace();
        }
        return jsonObject;
    }

    //@Override
    public static Entrenador deserializar(JSONObject json) {
        Entrenador entrenador=new Entrenador();
        try {
            entrenador.setDni(json.getInt("dni"));
            entrenador.setNombre(json.getString("nombre"));
            entrenador.setUsuario(json.getString("usuario"));
            entrenador.setContrasenia(json.getString("contrasenia"));
            entrenador.setEstado(json.getBoolean("estado"));
            entrenador.setEquipoDirigido(Equipo.deserializar(json.getJSONObject("equipodirigido")));
        }catch (JSONException ex){
            ex.printStackTrace();
        }
        return entrenador;
    }
}
