import java.util.ArrayList;
import java.util.List;

public class Liga extends Torneo{
    Listado<Partido> partidos;

    public Liga() {
        super();
    }

    public Liga(String nombre) {
        super(nombre);
    }
    public void agregarPartido(Partido partido){
        partidos.agregarElemento(partido);
    }
    public void calcularResultados(){
        // a desarrollar
    }
    public void listarTablaDePosiciones(){
        // a desarrollar
    }
}
