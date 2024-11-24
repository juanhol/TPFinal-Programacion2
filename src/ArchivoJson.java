import netscape.javascript.JSObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ArchivoJson {
    private String nombre;

    public ArchivoJson() {
    }

    public ArchivoJson(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static JSONTokener leer(String nombreArchivo){
        JSONTokener jsonTokener=null;
        try {
            jsonTokener=new JSONTokener(new FileReader(nombreArchivo));
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        return jsonTokener;
    }

    public static void grabarJsonObject(JSONObject jsonObject, JSONArray jsonArray,String nombreArchivo){
        jsonArray.put(jsonObject);
        ArchivoJson.grabarArray(jsonArray,nombreArchivo);
    }

    public static void grabarArray(JSONArray jsonArray,String nombreArchivo){
        try {

            FileWriter file=new FileWriter(nombreArchivo);
            file.write(jsonArray.toString());
            file.close();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
