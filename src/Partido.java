public class Partido {
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
}
