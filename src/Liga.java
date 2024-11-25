import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class Liga extends Torneo{
    Listado<Partido> partidos;

    // Constructores
    public Liga() {
        super();
        this.partidos = new Listado<>();
    }

    public Liga(String nombre) {
        super(nombre);
        this.partidos = new Listado<>();
    }

    // Getters and Setters
    public Listado<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(Listado<Partido> partidos) {
        this.partidos = partidos;
    }

    // Sobreescrituras

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


    @Override
    public Equipo ganadorTorneo() {
        Equipo equipoGanador = null;
        int maxPuntos = Integer.MIN_VALUE;

        for (Map.Entry<Equipo, Integer> entry : resultados.entrySet()) {
            Equipo equipo = entry.getKey();
            int puntos = entry.getValue();

            if (puntos > maxPuntos) {
                maxPuntos = puntos;
                equipoGanador = equipo;
            }
        }

        return equipoGanador;
    }


    // Métodos propios

    public Boolean agregarPartido(Partido partido){
        if(partido != null){
            partidos.agregarElemento(partido);
            return true;
        }
        return false;
    }

    public String listarTablaPosiciones() {

        StringBuilder sb = new StringBuilder();
        HashMap<Equipo, Integer> resultados = getResultados();

        // Crear un TreeSet con un comparador personalizado (orden por puntos descendente)
        TreeSet<Map.Entry<Equipo, Integer>> equiposOrdenados = new TreeSet<>((entry1, entry2) -> {
            int compare = entry2.getValue().compareTo(entry1.getValue()); // Orden descendente por puntos
            if (compare == 0) {
                // Si tienen los mismos puntos, ordenarlos por nombre del equipo para evitar duplicados
                return entry1.getKey().getNombre().compareTo(entry2.getKey().getNombre());
            }
            return compare;
        });

        // Añadir las entradas del HashMap al TreeSet
        equiposOrdenados.addAll(resultados.entrySet());

        // Acumular los resultados en el StringBuilder
        for (Map.Entry<Equipo, Integer> entry : equiposOrdenados) {
            sb.append("Equipo: ").append(entry.getKey().getNombre())
                    .append(", Puntos: ").append(entry.getValue())
                    .append("\n");
        }

        // Retornar el contenido del StringBuilder como un String
        return sb.toString();
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
        }catch (JSONException ex){
            ex.printStackTrace();
        }
        return liga;
    }
}