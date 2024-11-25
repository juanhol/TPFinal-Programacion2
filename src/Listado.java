import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Listado  <T extends Persistible> {
   private List<T> elementos;

    public Listado() {
        this.elementos = new ArrayList<>();
    }

    public List<T> getElementos() {
        return elementos;
    }

    public void setElementos(List<T> elementos) {
        this.elementos = elementos;
    }

    public void agregarElemento(T t){
        elementos.add(t);
    }
    public void eliminarElemento(T t){
        elementos.remove(t);
    }

    public void listarElementos(){
        for(T t:elementos){
            System.out.println(t.toString());
        }
    }
    public int cantidadElementos()
    {
        return elementos.size();
    }

    public T getElemento(int posicion){
        return elementos.get(posicion);
    }



    public JSONArray serializar() {
        JSONArray listadoArray = new JSONArray();
        for(T elemento : elementos){
            listadoArray.put(elemento.serializar());
        }
        return listadoArray;
    }
}
