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


    public static JSONTokener leer(ArchivoJson archivo){
        JSONTokener jsonTokener=null;
        try {
            jsonTokener=new JSONTokener(new FileReader(archivo.getNombre()));
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        return jsonTokener;
    }

    public static void grabarObject(JSONObject jsonObject,ArchivoJson archivo){
        try {

            FileWriter file=new FileWriter(archivo.getNombre());
            file.write(jsonObject.toString());
            file.close();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static void grabarArray(JSONArray jsonArray,ArchivoJson archivo){
        try {

            FileWriter file=new FileWriter(archivo.getNombre());
            file.write(jsonArray.toString());
            file.close();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
