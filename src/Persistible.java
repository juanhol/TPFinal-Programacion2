import netscape.javascript.JSObject;
import org.json.JSONObject;

public interface Persistible {
    JSONObject serializar();
    //Object deserializar(JSONObject json);
}
