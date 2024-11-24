import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

public class Liga extends Torneo{
    Listado<Partido> partidos;

    public Liga() {
        super();
    }

    public Liga(String nombre) {
        super(nombre);
    }
    public void agregarPartido(Partido partido){
        partidos.agregarElemento(partido);
    }
    public void calcularResultados(){
        // a desarrollar
    }
    public void listarTablaDePosiciones(){
        // a desarrollar
    }

    @Override
    public JSONObject serializar() {
       JSONObject jsonObject=new JSONObject();
       try {
           jsonObject.put("id",this.getId());
           jsonObject.put("nombre",this.getNombre());
           jsonObject.put("equipos",this.getEquipos());
           jsonObject.put("resultados",this.getResultados());
           jsonObject.put("estado",this.getEstado());
           JSONArray listadoPartidosJson=new JSONArray();
           for (int i = 0; i < this.partidos.cantidadElementos(); i++) {
                JSONObject partido=partidos.getElemento(i).serializar();
                listadoPartidosJson.put(partido);
           }
           jsonObject.put("listadoPartidos",listadoPartidosJson);
       }catch (JSONException ex){
           ex.printStackTrace();
       }
        return jsonObject;
    }

    public static Liga deserializar(JSONObject json){
        Liga liga=new Liga();
        try {
            liga.setId(json.getInt("id"));
            liga.setNombre(json.getString("nombre"));
            HashSet<Equipo> equipos=new HashSet<>();
            JSONArray equiposJson=json.getJSONArray("equipos");
            for (int i = 0; i < equiposJson.length(); i++) {
                Equipo equipo=Equipo.deserializar(equiposJson.getJSONObject(i));
                equipos.add(equipo);
            }
            liga.setEquipos(equipos);
            liga.setResultados();
        }catch (JSONException ex){
            ex.printStackTrace();
        }
        return liga;
    }
}
