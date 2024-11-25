import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Copa extends Torneo{
    private String faseActual;
    private HashMap<String, Listado<Partido>> partidosEliminatorios;

    //Constructores
    public Copa() {
        super();
        this.partidosEliminatorios=new HashMap<>();
    }

    public Copa(String nombre, String faseActual) {
        super(nombre);
        this.faseActual = faseActual;
        this.partidosEliminatorios = new HashMap<>();
    }

    //Getters and Setters
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

    //Sobreescrituras
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

            JSONArray fases = new JSONArray();
            JSONArray partidosPorFase = new JSONArray();

            for (Map.Entry<String, Listado<Partido>> entry : partidosEliminatorios.entrySet()) {
                String fase = entry.getKey();
                Listado<Partido> partidos = entry.getValue();


                JSONArray partidosJson = new JSONArray();
                for (int i = 0; i < partidos.cantidadElementos(); i++) {
                    Partido partido = partidos.getElemento(i);
                    partidosJson.put(partido.serializar());
                }

                fases.put(fase);
                partidosPorFase.put(partidosJson);
            }

            jsonObject.put("fases", fases);
            jsonObject.put("partidosPorFase", partidosPorFase);

        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return jsonObject;
    }

    @Override
    public Equipo ganadorTorneo() {

        Listado<Partido> partidos = partidosEliminatorios.get(faseActual);

        if (partidos == null || partidos.cantidadElementos() == 0) {
            return null;
        }


        HashMap<Equipo, Integer> puntosEquipos = new HashMap<>();

        for (int i = 0; i < partidos.cantidadElementos(); i++) {
            Partido partido = partidos.getElemento(i);
            Equipo ganador = partido.getGanador();

            if (ganador != null) {
                puntosEquipos.put(ganador, puntosEquipos.getOrDefault(ganador, 0) + 3);
            }
        }

        // Determinamos el equipo con más puntos
        Equipo ganadorFinal = null;
        int maxPuntos = 0;

        for (Map.Entry<Equipo, Integer> entry : puntosEquipos.entrySet()) {
            if (entry.getValue() > maxPuntos) {
                maxPuntos = entry.getValue();
                ganadorFinal = entry.getKey();
            }
        }

        if (ganadorFinal != null && maxPuntos == 12) {
            return ganadorFinal;
        }

        // Si no se alcanza los 12 puntos, retornar null (aún no hay ganador)
        return null;
    }

    // Métodos propios

    // Método para actualizar los puntos de los equipos
    public void actualizarPuntos(Partido partido) {

        Equipo equipoGanador = partido.getGanador();


        if (equipoGanador != null) {

            int puntos = resultados.getOrDefault(equipoGanador, 0);

            resultados.put(equipoGanador, puntos + 3);
        }
    }

    // Método para agregar un partido a la fase correspondiente
    public void agregarPartido(String fase, Partido partido) {
        Listado<Partido> partidos = partidosEliminatorios.getOrDefault(fase, new Listado<>());
        partidos.agregarElemento(partido);
        partidosEliminatorios.put(fase, partidos);
    }

    public String obtenerSiguienteFase(String faseActual) {
        switch (faseActual) {
            case "Octavos":
                return "Cuartos";
            case "Cuartos":
                return "Semis";
            case "Semis":
                return "Final";
            default:
                return "Torneo Finalizado";
        }
    }

    // Método para avanzar los equipos en cada fase
    private void avanzarEquipos(String fase) {


        Listado<Partido> partidos = partidosEliminatorios.get(fase);
        List<Equipo> equiposGanadores = new ArrayList<>();


        for (Partido partido : partidos.getElementos()) {

            if (partido.getEstado() == Estado.JUGADO) {

                Equipo equipoGanador = partido.getGanador();

                if (equipoGanador != null) {
                    equiposGanadores.add(equipoGanador);
                }
            }
        }

        String siguienteFase = obtenerSiguienteFase(fase);

        if (!equiposGanadores.isEmpty()) {

            Listado<Partido> partidosSiguienteFase = new Listado<>();


            for (int i = 0; i < equiposGanadores.size(); i += 2) {

                if (i + 1 < equiposGanadores.size()) {

                    Partido partidoSiguienteFase = new Partido();
                    partidoSiguienteFase.setEquipoLocal(equiposGanadores.get(i));
                    partidoSiguienteFase.setEquipoVisitante(equiposGanadores.get(i + 1));

                    partidosSiguienteFase.agregarElemento(partidoSiguienteFase);
                }
            }

            partidosEliminatorios.put(siguienteFase, partidosSiguienteFase);
        }
    }
}