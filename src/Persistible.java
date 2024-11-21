import netscape.javascript.JSObject;

public interface Persistible {
    JSObject serializar();
    void deserializar(JSObject json);
}
