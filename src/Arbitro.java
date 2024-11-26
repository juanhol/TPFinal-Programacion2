import netscape.javascript.JSObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Objects;

public class Arbitro extends Persona{
    private int partidosArbitrados;
    private boolean tienePartidoAdirigir;


    ///CONSTRUCTORES
    public Arbitro() {
    }

    public Arbitro(int dni, String nombre, String usuario, String contrasenia, int partidosArbitrados) {
        super(dni, nombre, usuario, contrasenia);
        this.partidosArbitrados = partidosArbitrados;
        this.tienePartidoAdirigir=false;
    }
    public Arbitro(int dni, String nombre, String usuario, String contrasenia, int partidosArbitrados,boolean partidoAdirigir) {
        super(dni, nombre, usuario, contrasenia);
        this.partidosArbitrados = partidosArbitrados;
        this.tienePartidoAdirigir=partidoAdirigir;
    }

    ///GETTERS Y SETTERS
    public int getPartidosArbitrados() {
        return partidosArbitrados;
    }
    public void setPartidosArbitrados(int partidosArbitrados) {
        this.partidosArbitrados = partidosArbitrados;
    }
    public boolean isTienePartidoAdirigir() {
        return tienePartidoAdirigir;
    }
    public void setTienePartidoAdirigir(boolean tienePartidoAdirigir) {
        this.tienePartidoAdirigir = tienePartidoAdirigir;
    }

    ///TO STRING
    @Override
    public String toString() {
        return "Arbitro:" +
                super.toString()+
                "\npartidosArbitrados=" + partidosArbitrados +
                "\ntienePartidoAdirigir=" + tienePartidoAdirigir
                 ;
    }

    ///SERIALIZAR Y DESERIALIZAR
    @Override
    public JSONObject serializar() {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject=super.serializar();
            jsonObject.put("partidosArbitrados",this.partidosArbitrados);
            jsonObject.put("tienePartidoAdirigir",this.tienePartidoAdirigir);
        }
        catch (JSONException ex){
            ex.printStackTrace();
        }
        return jsonObject;    }


    public static Arbitro deserializar(JSONObject json) {
        Arbitro arbitro=new Arbitro();
        try {
            // Deserializar los datos comunes de Persona
            Persona p = Persona.deserializar(json);

            // Crear un nuevo Jugador y copiar los datos
            arbitro = new Arbitro(
                    p.getDni(),
                    p.getNombre(),
                    p.getUsuario(),
                    p.getContrasenia(),
                    json.getInt("partidosArbitrados"),
                    json.getBoolean("tienePartidoAdirigir")
            );
        }
        catch(JSONException ex){
            ex.printStackTrace();
        }
        return arbitro;
    }

}
