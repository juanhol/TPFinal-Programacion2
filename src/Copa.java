import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Copa extends Torneo{
    private String faseActual;
    private HashMap<String, Listado<Partido>> partidosEliminatorios;

    public Copa() {
        super();
        this.partidosEliminatorios=new HashMap<>();
    }



    public Copa(String nombre, String faseActual) {
        super(nombre);
        this.faseActual = faseActual;
        this.partidosEliminatorios = new HashMap<>();
    }

    public String getFaseActual() {
        return faseActual;
    }

    public void setFaseActual(String faseActual) {
        this.faseActual = faseActual;
    }

    public HashMap<String, Listado<Partido>> getPartidosEliminatorios() {
        return partidosEliminatorios;
    }

    public void setPartidosEliminatorios(HashMap<String, Listado<Partido>> partidosEliminatorios) {
        this.partidosEliminatorios = partidosEliminatorios;
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
            jsonObject.put("faseActual",this.getFaseActual());
            JSONArray claves = new JSONArray();
            JSONArray valores = new JSONArray();
            for (Map.Entry<String, Listado<Partido>> entry : partidosEliminatorios.entrySet()) {
                claves.put(entry.getKey());
                valores.put(entry.getValue());
            }
        }catch (JSONException ex){
            ex.printStackTrace();
        }
        return jsonObject;
    }

    public static Copa deserializar(JSONObject json){
        Copa copa = new Copa();
        try {
            copa.setId(json.getInt("id"));
            copa.setNombre(json.getString("nombre"));
            HashSet<Equipo> equipos=new HashSet<>();
            JSONArray equiposJson=json.getJSONArray("equipos");
            for (int i = 0; i < equiposJson.length(); i++) {
                Equipo equipo=Equipo.deserializar(equiposJson.getJSONObject(i));
                equipos.add(equipo);
            }
            copa.setEquipos(equipos);

            HashMap<Equipo, Integer> resultados = new HashMap<>();
            JSONObject resultadosJson = json.getJSONObject("resultados");
            for (String key : resultadosJson.keySet()) {
                Equipo equipo = Equipo.deserializar(new JSONObject(key));
                int valor = resultadosJson.getInt(key);
                resultados.put(equipo, valor);
            }
            copa.setResultados(resultados);
            copa.setEstado(json.getEnum(Estado.class,"estado"));
        }catch (JSONException ex){
            ex.printStackTrace();
        }
        return copa;
    }



    @Override
    public Equipo ganadorTorneo() {
        return null;
    }


}
