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

    public static JSONArray leerArray(ArchivoJson archivo) {
        JSONArray jsonArray = new JSONArray();
        try (FileReader reader = new FileReader(archivo.getNombre())) {
            JSONTokener tokener = new JSONTokener(reader);
            jsonArray = new JSONArray(tokener);
        } catch (IOException e) {
            System.out.println("Error leyendo el archivo: " + archivo.getNombre());
        }
        return jsonArray;
    }

    public static void grabarObject(JSONObject jsonObject,ArchivoJson archivo){
        try {

            FileWriter file=new FileWriter(archivo.getNombre());
            file.write(jsonObject.toString(3));
            file.close();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static void grabarArray(JSONArray jsonArray,ArchivoJson archivo){
        try {

            FileWriter file=new FileWriter(archivo.getNombre());
            file.write(jsonArray.toString(3));
            file.close();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
