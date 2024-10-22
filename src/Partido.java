import java.util.Date;
import java.util.Scanner;

public class Partido {
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private Date fecha;
    private Resultado resultado;
    private int resV;

    public Partido(Equipo equipoLocal, Equipo equipoVisitante, Date fecha) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.fecha = fecha;
        this.resultado=Resultado.NO_JUGADO;
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

    public String mostrarResultado() {

        if(resultado==Resultado.NO_JUGADO)
        return "El ganador todavia no se definio.";
        else if (resultado==Resultado.VISITANTE){
            return "El ganador es: "+equipoVisitante.getNombre();
        }
        else{
            return "El ganador es: "+equipoLocal.getNombre();
        }
    }

    public boolean setResultado(Character resultado) {//devuelve true si se seteo o false si hubo un error
        try {

            if(resultado == null)
                throw new IllegalArgumentException("Ingreso no valido Ingrese L para local o V para visitante.");
            if(resultado != 'L'&& resultado!='V')
            {
                return false;
            }
            else
            {
                if(resultado=='V')
                    this.resultado=Resultado.VISITANTE;
                else
                    this.resultado=Resultado.LOCAL;
                return true;
            }
        }
        catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        return false;
    }

    public String jugarPartido(){        //hay q armar bien los pasajes de parametros y seteos de resultado y cositas
        return "Se juega el partido...";
    }
}
