import org.json.JSONException;
import org.json.JSONObject;

public class Partido implements Persistible{
    private static int idh=0;
    private int id;
    private Arbitro arbitro;
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private int resultadoLocal;
    private int ResultadoVisitante;
    private Estado estado;

    public Partido() {
        idh++;
        this.id=idh;
        this.resultadoLocal=0;
        this.ResultadoVisitante=0;
        this.estado=Estado.NO_JUGADO;
    }

    public Partido(Arbitro arbitro, Equipo equipoLocal, Equipo equipoVisitante) {
        idh++;
        this.id=idh;
        this.arbitro = arbitro;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.resultadoLocal=0;
        this.ResultadoVisitante=0;
        this.estado=Estado.NO_JUGADO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Arbitro getArbitro() {
        return arbitro;
    }

    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(Equipo equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(Equipo equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public int getResultadoLocal() {
        return resultadoLocal;
    }

    public void setResultadoLocal(int resultadoLocal) {
        this.resultadoLocal = resultadoLocal;
    }

    public int getResultadoVisitante() {
        return ResultadoVisitante;
    }

    public void setResultadoVisitante(int resultadoVisitante) {
        ResultadoVisitante = resultadoVisitante;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Equipo getGanador(){
        Equipo equipoGanador=new Equipo();
        return equipoGanador;
    }

    @Override
    public JSONObject serializar() {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("id",this.getId());
            jsonObject.put("arbitro",this.getArbitro().serializar());
            jsonObject.put("equipoLocal",this.getEquipoLocal().serializar());
            jsonObject.put("equipoVisitante",this.getEquipoVisitante().serializar());
            jsonObject.put("resultadoLocal",this.getResultadoLocal());
            jsonObject.put("resultadoVisitante",this.getResultadoVisitante());
            jsonObject.put("estado",this.getEstado());

        }catch(JSONException ex){
            ex.printStackTrace();
        }
        return jsonObject;
    }

    public static Partido deserializar(JSONObject json){
        Partido partido = new Partido();
        try {
            partido.setId(json.getInt("id"));
            partido.setArbitro(Arbitro.deserializar(json.getJSONObject("arbitro")));
            partido.setEquipoLocal(Equipo.deserializar(json.getJSONObject("equipoLocal")));
            partido.setEquipoVisitante(Equipo.deserializar(json.getJSONObject("equipoVisitante")));
            partido.setResultadoLocal(json.getInt("resultadoLocal"));
            partido.setResultadoVisitante(json.getInt("resultadoVisitante"));
            partido.setEstado(json.getEnum(Estado.class,"estado"));
        }catch (JSONException ex){
            ex.printStackTrace();
        }
        return partido;
    }
}
