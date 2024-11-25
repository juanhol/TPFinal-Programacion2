import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private final String titulo;
    private static Listado<Administrador> administradores;
    private static Listado<Arbitro> arbitros;
    private static Listado<Entrenador> entrenadores;
    private static Listado<Jugador> jugadores;
    private Scanner scanner;

    public Menu(String titulo) {
        this.titulo = titulo;
        administradores = new Listado<>();
        arbitros = new Listado<>();
        entrenadores = new Listado<>();
        jugadores = new Listado<>();
        this.scanner= new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion = -1;
        ArchivoJson archivoAdministradores = new ArchivoJson("Administradores.json");
        ArchivoJson archivoArbitros = new ArchivoJson("Arbitros.json");
        ArchivoJson archivoEntrenadores = new ArchivoJson("Entrenadores.json");
        ArchivoJson archivoJugadores = new ArchivoJson("Jugadores.json");
        JSONArray array= new JSONArray();
        JSONObject object= new JSONObject();
        do {
            System.out.println("\n======================== " + titulo + " ========================");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar Sesion");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = scanner.nextInt(); // Lee la opción del usuario

                switch (opcion) {
                    case 1:
                        do {
                            System.out.println("\n======================== " + titulo + " ========================");
                            System.out.println("1. Registrarse como Administrador");
                            System.out.println("2. Registrarse como Arbitro");
                            System.out.println("3. Registrarse como Entrenador");
                            System.out.println("4. Registrarse como Jugador");
                            System.out.println("5. Volver al menu anterior");
                            System.out.println("Seleccione una opción: ");

                            try {
                                opcion = scanner.nextInt(); // Lee la opción del usuario

                                switch (opcion) {
                                    case 1:
                                        Persona admin= registrarPersona(administradores);
                                        System.out.println("Ingrese su email\n");
                                        String email=scanner.nextLine();
                                        Administrador admin1 = new Administrador(admin.getDni(), admin.getNombre(), admin.getUsuario(), admin.getContrasenia(),email);
                                        administradores.agregarElemento(admin1);
                                        array=administradores.serializar();
                                        ArchivoJson.grabarArray(array,archivoAdministradores);
                                        System.out.println("Administrador registrado");
                                        break;
                                    case 2:
                                        Persona arbitro= registrarPersona(arbitros);
                                        System.out.println("Ingrese la cantidad de partidos arbitrados:\n");
                                        int cant=scanner.nextInt();
                                        Arbitro arbitro1 = new Arbitro(arbitro.getDni(), arbitro.getNombre(), arbitro.getUsuario(), arbitro.getContrasenia(),cant);
                                        arbitros.agregarElemento(arbitro1);
                                        array=arbitros.serializar();
                                        ArchivoJson.grabarArray(array,archivoArbitros);
                                        System.out.println("Arbitro registrado");

                                        break;
                                    case 3:
                                        Persona entrenador= registrarPersona(entrenadores);
                                        System.out.println("Ingrese el equipo a dirigir\n");
                                        String equipo=scanner.nextLine();
                                        Entrenador entrenador1 = new Entrenador(entrenador.getDni(), entrenador.getNombre(), entrenador.getUsuario(), entrenador.getContrasenia(),equipo);
                                        entrenadores.agregarElemento(entrenador1);
                                        array=entrenadores.serializar();
                                        ArchivoJson.grabarArray(array,archivoEntrenadores);
                                        System.out.println("Entrenador registrado");
                                        break;
                                    case 4:
                                        Persona jugador= registrarPersona(jugadores);
                                        String entrada;
                                        Posicion posicion=null;
                                        do {
                                            System.out.println("Ingrese la posicion a jugar \n" + Arrays.toString(Posicion.values()));
                                            entrada=scanner.nextLine().toUpperCase();
                                            try {
                                                // Convertir la entrada del usuario a un valor del enum
                                                posicion = Posicion.valueOf(entrada);
                                            } catch (IllegalArgumentException e) {
                                                System.out.println("Error: '" + entrada + "' no es una posición valida.");
                                            }
                                        }while(posicion==null);

                                        Jugador jugador1 = new Jugador(jugador.getDni(), jugador.getNombre(), jugador.getUsuario(), jugador.getContrasenia(),posicion);
                                        jugadores.agregarElemento(jugador1);
                                        array=jugadores.serializar();
                                        ArchivoJson.grabarArray(array,archivoJugadores);
                                        System.out.println("Jugador registrado");
                                        break;
                                    case 5:
                                        break;
                                    default:
                                        System.out.println("Opción no válida. Intente de nuevo.");
                                }
                            } catch (Exception e) {
                                System.out.println("Error: Entrada no válida. Intente nuevamente.");
                                scanner.next();
                            }
                        } while (opcion != 5);
                    break;
                    case 0:
                        System.out.println("Saliendo del menú...");
                        scanner.close();
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Error: Entrada no válida. Intente nuevamente.");
                scanner.next();
            }
        } while (opcion != 0);







        scanner.close();
    }

    public static int CantidadDigitos(int numero) throws ExceptionCantDigitosDni {
        int contador = 0;
        do {
            numero /= 10;
            contador++;
        } while (numero > 0);
        if(contador==8){
            return 0;
        }
        throw new ExceptionCantDigitosDni("La cantidad de digitos es erronea.");
    }



    public Persona registrarPersona(ArchivoJson archivo) {
        Persona persona = new Persona();
        int flagDNI = 0;
        do {
            try {
                System.out.println("Ingrese su DNI con 8 digitos: \n");
                persona.setDni(scanner.nextInt());
                scanner.nextLine();

                flagDNI=Menu.CantidadDigitos(persona.getDni());
                flagDNI = Menu.validarSiYaExisteDni(persona.getDni(), archivo);

            } catch (InputMismatchException e) {
                flagDNI=1;
                System.out.println("Error: La entrada no es un número válido.");
                scanner.next();
            }catch (ExceptionCantDigitosDni | ExceptionDniYaIngresado e) {
                flagDNI=1;
                System.out.println(e.getMessage());;
            }

        }
            while (flagDNI == 1);

            System.out.println("Ingrese su nombre\n");
            persona.setNombre(scanner.nextLine());
            System.out.println("Ingrese su usuario\n");
            persona.setUsuario(scanner.nextLine());
            System.out.println("Ingrese su contrasenia\n");
            persona.setContrasenia(scanner.nextLine());


            return persona;
        }


        public static int validarSiYaExisteDni ( int dni, ArchivoJson archivo) throws ExceptionDniYaIngresado{

        for (int i = 0; i < lista.cantidadElementos(); i++) {
                Persona persona = (Persona) lista.getElemento(i);
                if (persona.getDni() == dni) {
                    throw new ExceptionDniYaIngresado("Dni ya ingresado");
                }
            }
            return 0;
        }


}

