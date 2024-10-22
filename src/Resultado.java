public enum Resultado {

    VISITANTE("Visitante"),
    LOCAL("Local"),
    NO_JUGADO("Todavia no se jugo el partido");

    private final String equipo;
    Resultado(String equipo) {
        this.equipo= equipo;
    }
    public String getEquipo(){
        return this.equipo;
    }
}
