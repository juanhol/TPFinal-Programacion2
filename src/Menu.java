import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private final String titulo;
    private static Scanner scanner;

    public Menu(String titulo) {
        this.titulo = titulo;
        this.scanner= new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion = -1;
        ArchivoJson archivoAdministradores = new ArchivoJson("Administradores.json");
        ArchivoJson archivoArbitros = new ArchivoJson("Arbitros.json");
        ArchivoJson archivoEntrenadores = new ArchivoJson("Entrenadores.json");
        ArchivoJson archivoJugadores = new ArchivoJson("Jugadores.json");
        JSONArray jsonArray= new JSONArray();
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
                                        Persona admin= registrarPersona(archivoAdministradores);
                                        System.out.println("Ingrese su email\n");
                                        String email=scanner.nextLine();
                                        Administrador admin1 = new Administrador(admin.getDni(), admin.getNombre(), admin.getUsuario(), admin.getContrasenia(),email);
                                        jsonArray=ArchivoJson.leerArray(archivoAdministradores);
                                        jsonArray.put(admin1.serializar());
                                        ArchivoJson.grabarArray(jsonArray,archivoAdministradores);
                                        System.out.println("Administrador registrado");
                                        break;
                                    case 2:
                                        Persona arbitro= registrarPersona(archivoArbitros);
                                        System.out.println("Ingrese la cantidad de partidos arbitrados:\n");
                                        int cant=scanner.nextInt();
                                        Arbitro arbitro1 = new Arbitro(arbitro.getDni(), arbitro.getNombre(), arbitro.getUsuario(), arbitro.getContrasenia(),cant);
                                        jsonArray=ArchivoJson.leerArray(archivoArbitros);
                                        jsonArray.put(arbitro1.serializar());
                                        ArchivoJson.grabarArray(jsonArray,archivoArbitros);
                                        System.out.println("Arbitro registrado");

                                        break;
                                    case 3:
                                        Persona entrenador= registrarPersona(archivoEntrenadores);
                                        Entrenador entrenador1 = new Entrenador(entrenador.getDni(), entrenador.getNombre(), entrenador.getUsuario(), entrenador.getContrasenia(),"");
                                        jsonArray=ArchivoJson.leerArray(archivoEntrenadores);
                                        jsonArray.put(entrenador1.serializar());
                                        ArchivoJson.grabarArray(jsonArray,archivoEntrenadores);
                                        System.out.println("Entrenador registrado");
                                        break;
                                    case 4:
                                        Persona jugador= registrarPersona(archivoJugadores);
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
                                        jsonArray=ArchivoJson.leerArray(archivoJugadores);
                                        jsonArray.put(jugador1.serializar());
                                        ArchivoJson.grabarArray(jsonArray,archivoJugadores);
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
                    case 2:
                        int opcion1 = 0;

                        do {
                            System.out.println("\n======================== " + titulo + " ========================");
                            System.out.println("1. Iniciar sesión como Administrador");
                            System.out.println("2. Iniciar sesión como Arbitro");
                            System.out.println("3. Iniciar sesión como Entrenador");
                            System.out.println("4. Iniciar sesión como Jugador");
                            System.out.println("5. Volver al menu anterior");
                            System.out.println("Seleccione una opción: ");
                            opcion1 = scanner.nextInt();

                            switch (opcion1){
                                case 1:
                                    Menu.iniciarSesion(archivoAdministradores);
                                    break;
                                case 2:
                                    Menu.iniciarSesion(archivoArbitros);
                                    break;
                                case 3:
                                    Menu.iniciarSesion(archivoEntrenadores);
                                    break;
                                case 4:
                                    Menu.iniciarSesion(archivoJugadores);
                                    break;
                                case 5:
                                    break;
                                default:
                                    System.out.println("Opción no válida. Intente de nuevo.");
                            }

                        } while (opcion1 != 5);

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

                flagDNI = Menu.CantidadDigitos(persona.getDni());
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


    public static int validarSiYaExisteDni(int dni, ArchivoJson archivo) throws ExceptionDniYaIngresado {
        JSONTokener tokener = ArchivoJson.leer(archivo); // Lee el archivo JSON

        if (tokener != null) {

            JSONArray jsonArray = new JSONArray(tokener); // Convierto el contenido en un JSONArray

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i); // Extrae cada objeto JSON

                if (jsonObject.getInt("dni") == dni) {

                    throw new ExceptionDniYaIngresado("DNI ya ingresado.");
                }
            }
        }
        return 0; // Retorna 0 si no encuentra duplicados
    }

    public void menuEntrenador(Entrenador entrenador){

    }
    public void menuAdministrador(Administrador administrador){

    }

    public void menuArbitro(Arbitro arbitro){

    }

    public void menuJugador(Jugador jugador){

    }



    public static boolean validarUsuario(String usuario, ArchivoJson archivo) {
        JSONTokener tokener = ArchivoJson.leer(archivo); // Lee el archivo JSON

        if (tokener != null) {
            JSONArray jsonArray = new JSONArray(tokener); // Convierto el contenido en un JSONArray

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                if (jsonObject.getString("usuario").equals(usuario)) {
                    return true; // Usuario encontrado
                }
            }
        }

        return false; // Usuario no encontrado
    }

    public static boolean validarContrasenia(String usuario, String contrasenia, ArchivoJson archivo) {
        JSONTokener tokener = ArchivoJson.leer(archivo);

        if (tokener != null) {
            JSONArray jsonArray = new JSONArray(tokener);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                // Verifica si el usuario coincide y luego valida la contraseña
                if (jsonObject.getString("usuario").equals(usuario)) {
                    return jsonObject.getString("contrasenia").equals(contrasenia); // Contraseña correcta o incorrecta
                }
            }
        }

        return false; // Esto no debería ocurrir si se valida el usuario previamente
    }

    public static void iniciarSesion(ArchivoJson archivo) {
        boolean usuarioValido = false;
        String usuarioIngresado = null;

        scanner.nextLine();

        while (!usuarioValido) {
            System.out.println("Ingrese su usuario: ");
            String usuario = scanner.nextLine();

            try {
                if (Menu.validarUsuario(usuario, archivo)) { // Cambiar archivo según corresponda
                    usuarioValido = true;
                    usuarioIngresado = usuario; // Guardamos el usuario válido
                    System.out.println("Usuario encontrado.");
                } else {
                    System.out.println("Usuario no encontrado. Por favor, intente de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            }
        }

        boolean contraseniaValida = false;

        while (!contraseniaValida) {
            System.out.println("Ingrese su contraseña: ");
            String contrasenia = scanner.next();

            try {
                if (validarContrasenia(usuarioIngresado, contrasenia, archivo)) { // Verifica la contraseña
                    contraseniaValida = true;
                    System.out.println("Inicio de sesión exitoso. Bienvenido.");
                } else {
                    System.out.println("Contraseña incorrecta. Por favor, intente de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            }
        }


    }





}

