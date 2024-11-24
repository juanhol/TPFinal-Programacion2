import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public abstract class Torneo implements Persistible {
    private static int idh=0;
    private int id;
    private String nombre;
    private Set<Equipo> equipos;
    private HashMap<Equipo,Integer> resultados;
    private Estado estado;

    public Torneo() {
        idh++;
        this.id=idh;
        this.equipos=new HashSet<>();
        this.resultados=new HashMap<>();
        this.estado=Estado.NO_JUGADO;
    }

    public Torneo(String nombre) {
        this.nombre = nombre;
        idh++;
        this.id=idh;
        this.equipos=new HashSet<>();
        this.resultados=new HashMap<>();
        this.estado=Estado.NO_JUGADO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(Set<Equipo> equipos) {
        this.equipos = equipos;
    }

    public HashMap<Equipo, Integer> getResultados() {
        return resultados;
    }

    public void setResultados(HashMap<Equipo, Integer> resultados) {
        this.resultados = resultados;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    // exepcion personalizada supera limite de equipos
    public void agregarEquipo(Equipo equipo){
        equipos.add(equipo);
    }
    public void eliminarEquipo(Equipo equipo){
        equipos.remove(equipo);
    }
    public void actualizarResultados(Equipo equipo,Integer puntos){
        resultados.replace(equipo,puntos);
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", equipos=" + equipos +
                ", resultados=" + resultados;
    }
    public Equipo ganadorTorneo(){
        Equipo equipoGanador=new Equipo();
        return equipoGanador;
    }
}
