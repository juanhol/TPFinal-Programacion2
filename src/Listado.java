import java.util.List;

public class Listado <T>{
   private List<T> elementos;

    public Listado() {
    }
    public void agregarElemento(T t){
        elementos.add(t);
    }
    public void eliminarElemento(T t){
        elementos.remove(t);
    }

    public void listarElementos(){
        for(T t:elementos){
            System.out.println(t.toString());
        }
    }
}
