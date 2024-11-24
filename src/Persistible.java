import netscape.javascript.JSObject;
import org.json.JSONObject;

public interface Persistible {
    JSONObject serializar();
    /// void deserializar(JSONObject json);
}
