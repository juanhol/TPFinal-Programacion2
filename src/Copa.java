import java.util.HashMap;
import java.util.List;

public class Copa extends Torneo{
    private String faseActual;
    private HashMap<String, Listado<Partido>> partidosEliminatorios;

    public Copa() {
        super();
        this.partidosEliminatorios=new HashMap<>();
    }

    public Copa(String nombre, String faseActual) {
        super(nombre);
        this.faseActual = faseActual;
        this.partidosEliminatorios = new HashMap<>();
    }
}
