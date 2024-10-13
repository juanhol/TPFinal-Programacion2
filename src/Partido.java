import java.util.Date;

public class Partido {
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private Date fecha;
    private Character resultado;

    public Partido(Equipo equipoLocal, Equipo equipoVisitante, Date fecha) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.fecha = fecha;
        this.resultado='N';
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Character getResultado() {
        return resultado;
    }

    public boolean setResultado(Character resultado) {
        if(resultado != 'L'|| resultado!='V')
        {
            return false;
        }
        else
        {
            this.resultado = resultado;
            return true;
        }

    }
}
