import netscape.javascript.JSObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;

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

    //////////////////////////
    public static JSONArray leerArray2(ArchivoJson archivo) {
        File file = new File(archivo.getNombre());

        if (!file.exists()) {
            System.out.println("El archivo no existe. Se creará uno nuevo.");
            return new JSONArray(); // Retorna un JSONArray vacío si el archivo no existe
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder contenido = new StringBuilder();
            String linea;

            while ((linea = reader.readLine()) != null) {
                contenido.append(linea);
            }

            // Verificar si el archivo está vacío
            if (contenido.toString().isEmpty()) {
                System.out.println("El archivo está vacío.");
                return new JSONArray(); // Retorna un JSONArray vacío
            }

            // Intentar parsear el contenido del archivo a un JSONArray
            return new JSONArray(contenido.toString());
        } catch (IOException e) {
            System.out.println("Error leyendo el archivo: " + archivo.getNombre());
            e.printStackTrace();
            return new JSONArray(); // Retorna un JSONArray vacío en caso de error
        } catch (JSONException e) {
            System.out.println("Error de formato en el archivo JSON.");
            e.printStackTrace();
            return new JSONArray(); // Retorna un JSONArray vacío si el JSON es inválido
        }
    }
}
