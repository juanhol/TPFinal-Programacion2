import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

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

            HashMap<Equipo, Integer> resultados = new HashMap<>();
            JSONObject resultadosJson = json.getJSONObject("resultados");
            for (String key : resultadosJson.keySet()) {
                Equipo equipo = Equipo.deserializar(new JSONObject(key));
                int valor = resultadosJson.getInt(key);
                resultados.put(equipo, valor);
            }
            liga.setResultados(resultados);
            liga.setEstado(json.getEnum(Estado.class,"estado"));
        }catch (JSONException ex){
            ex.printStackTrace();
        }
        return liga;
    }


    @Override
    public Equipo ganadorTorneo() {
        return null;
    }


}
