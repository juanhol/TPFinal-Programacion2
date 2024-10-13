import java.util.ArrayList;

public class Torneo {
    private String nombre;
    private ArrayList<Equipo> equipos;
    private ArrayList<Partido> partidos;

    public Torneo(String nombre) {
        this.nombre = nombre;
        this.partidos=new ArrayList<>();
        this.equipos=new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Equipo> getEquipos() {
        return equipos;
    }

    public ArrayList<Partido> getPartidos() {
        return partidos;
    }
    public void programarPartido(Partido partido){
        partidos.add(partido);
    }
    public void registrarResultado(Partido partido,Character resultado){
        int i=partidos.indexOf(partido);
        Partido aux=partidos.get(i);
        aux.setResultado(resultado);
        partidos.add(i,aux);
    }
}
