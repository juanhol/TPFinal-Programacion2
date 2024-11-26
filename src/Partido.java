import org.json.JSONException;
import org.json.JSONObject;

public class Partido implements Persistible{
    private Arbitro arbitro;
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private int resultadoLocal;
    private int resultadoVisitante;
    private Estado estado;

    public Partido() {

    }

    public Partido(Arbitro arbitro, Equipo equipoLocal, Equipo equipoVisitante) {
        this.arbitro = arbitro;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.resultadoLocal=0;
        this.resultadoVisitante=0;
        this.estado=Estado.NO_JUGADO;
    }

    ///GETTERS Y SETTERS

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
    }public void setResultadoLocal(int resultadoLocal) {
        this.resultadoLocal = resultadoLocal;
    }
    public int getResultadoVisitante() {return resultadoVisitante;}
    public void setResultadoVisitante(int resultadoVisitante) {
        resultadoVisitante = resultadoVisitante;
    }
    public Estado getEstado() {
        return estado;
    }
    public void setEstado(Estado estado) {
        this.estado = estado;
    }



    public Equipo getGanador(){
        Equipo equipoGanador=new Equipo();
        if(estado==Estado.JUGADO){
            if (resultadoLocal>resultadoVisitante){

                equipoGanador=equipoLocal;

            } else if (resultadoVisitante>resultadoLocal) {

                equipoGanador=equipoVisitante;

            }else{
                return new Equipo();///Retorna un equipo vacio si es empate
            }
        }else{
            equipoGanador=null;///Retorna un equipo null si no se ha jugado o si fue suspendido
        }

        return equipoGanador;
    }

    @Override
    public JSONObject serializar() {
        JSONObject jsonObject=new JSONObject();
        try {

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
