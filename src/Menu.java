
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private final String titulo;


    public Menu(String titulo) {
        this.titulo = titulo;

    }

    public void mostrarMenu(Scanner scanner) {
        int opcion = -1;
        ArchivoJson archivoAdministradores = new ArchivoJson("Administradores.json");
        ArchivoJson archivoArbitros = new ArchivoJson("Arbitros.json");
        ArchivoJson archivoEntrenadores = new ArchivoJson("Entrenadores.json");
        ArchivoJson archivoJugadores = new ArchivoJson("Jugadores.json");

        ArchivoJson archivoLigas = new ArchivoJson("Ligas.json");
        ArchivoJson archivoCopas = new ArchivoJson("Copas.json");
        ArchivoJson archivoEquipos = new ArchivoJson("Equipos.json");

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
                                        Persona admin= Menu.registrarPersona(scanner, archivoAdministradores);
                                        System.out.println("Ingrese su email\n");
                                        String email=scanner.nextLine();
                                        Administrador admin1 = new Administrador(admin.getDni(), admin.getNombre(), admin.getUsuario(), admin.getContrasenia(),email);
                                        jsonArray=ArchivoJson.leerArray(archivoAdministradores);
                                        jsonArray.put(admin1.serializar());
                                        ArchivoJson.grabarArray(jsonArray,archivoAdministradores);
                                        System.out.println("Administrador registrado");
                                        break;
                                    case 2:
                                        Persona arbitro= Menu.registrarPersona(scanner, archivoArbitros);
                                        System.out.println("Ingrese la cantidad de partidos arbitrados:\n");
                                        int cant=scanner.nextInt();
                                        Arbitro arbitro1 = new Arbitro(arbitro.getDni(), arbitro.getNombre(), arbitro.getUsuario(), arbitro.getContrasenia(),cant);
                                        jsonArray=ArchivoJson.leerArray(archivoArbitros);
                                        jsonArray.put(arbitro1.serializar());
                                        ArchivoJson.grabarArray(jsonArray,archivoArbitros);
                                        System.out.println("Arbitro registrado");

                                        break;
                                    case 3:
                                        Persona entrenador= Menu.registrarPersona(scanner, archivoEntrenadores);
                                        Entrenador entrenador1 = new Entrenador(entrenador.getDni(), entrenador.getNombre(), entrenador.getUsuario(), entrenador.getContrasenia(),"No tiene equipo");
                                        jsonArray=ArchivoJson.leerArray(archivoEntrenadores);
                                        jsonArray.put(entrenador1.serializar());
                                        ArchivoJson.grabarArray(jsonArray,archivoEntrenadores);
                                        System.out.println("Entrenador registrado");
                                        break;
                                    case 4:
                                        Persona jugador= Menu.registrarPersona(scanner, archivoJugadores);
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
                                    Menu.iniciarSesion(scanner, archivoAdministradores);
                                    Menu.menuAdministrador(scanner, archivoLigas, archivoCopas, archivoEquipos, archivoEntrenadores);
                                    break;
                                case 2:
                                    Menu.iniciarSesion(scanner, archivoArbitros);
                                    break;
                                case 3:
                                    String nombreEntrenador = Menu.iniciarSesion(scanner, archivoEntrenadores);
                                    Entrenador entrenador = Menu.traerEntrenador(archivoEntrenadores, nombreEntrenador);
                                    Menu.menuEntrenador(scanner, archivoEntrenadores, archivoJugadores, archivoEquipos, entrenador);
                                    break;
                                case 4:
                                    Menu.iniciarSesion(scanner, archivoJugadores);
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







        //scanner.close();
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



    public static Persona registrarPersona(Scanner scanner,ArchivoJson archivo) {
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

    public static void menuEntrenador(Scanner scanner, ArchivoJson archivoEntrenador, ArchivoJson archivoJugadores, ArchivoJson archivoEquipos, Entrenador entrenador){
        int opcion;
        do {
            Menu.tareasEntrenador();
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                        Menu.listarEquipos(archivoEquipos);
                    break;
                case 2:
                    Menu.agregarJugadores(scanner, archivoJugadores, archivoEquipos, entrenador.getEquipoDirigido());
                    break;
                case 3:
                    Menu.eliminarJugadores(scanner,archivoJugadores,archivoEquipos, entrenador.getEquipoDirigido());
                    break;
                case 4:
                    Menu.menuEditarPerfil(scanner, archivoEntrenador, entrenador);
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

        }while(opcion!=4);


    }

    public static void tareasEntrenador(){
        System.out.println("1. Ver todos los equipos");
        System.out.println("2. Agregar jugador al equipo");
        System.out.println("3. Eliminar jugador del equipo");
        System.out.println("4. Volver al menu anterior");
    }

    public static Equipo traerEquipodelEntrenador(ArchivoJson archivoEquipos, String equipoDirigido){
        JSONArray jsonArray = ArchivoJson.leerArray2(archivoEquipos);
        Equipo equipo = null;


        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);


            if(json.getString("nombre").equals(equipoDirigido)){

                equipo = Equipo.deserializar(json);

            }

        }
        return equipo;
    }

    public static void agregarJugadores(Scanner scanner, ArchivoJson archivoJugadores, ArchivoJson archivoEquipos, String equipoDirigido){

        Equipo equipoDelEntrenador = Menu.traerEquipodelEntrenador(archivoEquipos, equipoDirigido);

        String control = "";
        int z = 0;

        do {
            Jugador jugador = null;
            System.out.println("Listado de jugadores sin equipo: ");
            Menu.listarJugadoresSinEquipo(archivoJugadores);

            System.out.println("Indique el jugador que desea agregar: ");
            int nro = scanner.nextInt();
            scanner.nextLine();


            JSONArray jsonArray = ArchivoJson.leerArray2(archivoJugadores);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject json = jsonArray.getJSONObject(i);

                if (i + 1 == nro) {
                    jugador = Jugador.deserializar(json);

                    if (jugador.getTieneEquipo()) {
                        System.out.println("El jugador ya pertenece a un equipo. Seleccione otro.");
                        jugador = null;
                        break;
                    }

                }

            }
            boolean carga = false;
            if(jugador != null) {
                jugador.setTieneEquipo(true);
                 carga = equipoDelEntrenador.agregarJugador(jugador);
                Menu.actualizarJsondeequipos(archivoEquipos, equipoDelEntrenador);
                Menu.actualizarJsondePersona(archivoJugadores, jugador);
            }

            if (carga) {
                System.out.println("Jugador agregado con exito!");
                z++;
            } else {
                System.out.println("No se ha podido cargar al jugador en el equipo. ");
            }

            System.out.println("Desea cargar otro jugador? Ingrese 'si' o 'no': ");
            control = scanner.nextLine();

        }while(control.equalsIgnoreCase("SI"));

        if(z != 0){
            if(z == 1){
                System.out.println("Se ha cargado con exito el unico jugador agregado!");
            }else {
                System.out.println("Se han cargado con exito los " + z + " jugadores!");
            }
        }else{
            System.out.println("No se ha cargado ningun jugador!");
        }


    }

    public static void eliminarJugadores(Scanner scanner,ArchivoJson archivoJugadores,ArchivoJson archivoEquipos, String equipoDirigido){

        Equipo equipoDelEntrenador = Menu.traerEquipodelEntrenador(archivoEquipos, equipoDirigido);

        String control = "";
        boolean eliminar=false;
        Jugador jugador=null;
        int z = 0;
        do {

            System.out.println("Listado de jugadores actuales del equipo: ");
            equipoDelEntrenador.listarJugadores();

            System.out.println("Indique el jugador que desea eliminar: ");
            int nro = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < equipoDelEntrenador.getListadoJugadores().getElementos().size(); i++) {

                if (i + 1 == nro) {
                jugador=equipoDelEntrenador.getListadoJugadores().getElemento(i);
                }

            }
            if(jugador !=null) {
                jugador.setTieneEquipo(false);
                eliminar = equipoDelEntrenador.eliminarJugador(jugador);
                Menu.actualizarJsondeequipos(archivoEquipos, equipoDelEntrenador);
                Menu.actualizarJsondePersona(archivoJugadores, jugador);
            }

            if (eliminar) {
                System.out.println("Jugador eliminado con exito!");
                z++;
            } else {
                System.out.println("No se ha podido eliminar al jugador del equipo. ");
            }

            System.out.println("Desea eliminar otro jugador? Ingrese 'si' o 'no': ");
            control = scanner.nextLine();

        }while(control.equalsIgnoreCase("SI"));

        if(z != 0){
            if(z == 1){
                System.out.println("Se ha cargado con exito el unico jugador agregado!");
            }else {
                System.out.println("Se han cargado con exito los " + z + " jugadores!");
            }
        }else{
            System.out.println("No se ha cargado ningun jugador!");
        }

    }



    public static void listarEquipos(ArchivoJson archivoEquipos){

        JSONArray jsonArray = ArchivoJson.leerArray2(archivoEquipos);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);

            Equipo nuevo = Equipo.deserializar(json);
            System.out.println(i+1 + ". " + nuevo);

        }

    }

    public static void listarJugadores(ArchivoJson archivoJugadores){

        JSONArray jsonArray = ArchivoJson.leerArray2(archivoJugadores);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);

            Jugador nuevo = Jugador.deserializar(json);
            System.out.println(i+1 + ". " + nuevo.getNombre() + " - " + nuevo.getPosicion() );

        }

    }

    public static void listarJugadoresSinEquipo(ArchivoJson archivoJugadores){

        JSONArray jsonArray = ArchivoJson.leerArray2(archivoJugadores);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);
            Jugador nuevo = Jugador.deserializar(json);

            if (!nuevo.getTieneEquipo()){
                System.out.println(i+1 + ". " + nuevo.getNombre() + " - " + nuevo.getPosicion() );
            }

        }

    }


    public static void menuAdministrador(Scanner scanner, ArchivoJson archivoLiga, ArchivoJson archivoCopa, ArchivoJson archivoEquipos, ArchivoJson archivoEntrenadores){
       JSONArray jsonArray = new JSONArray();
        int opcion;
        do {
            Menu.menuTareasAdmin();
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                        int op;
                        do {
                            op = Menu.menuCrearTorneo(scanner);
                            scanner.nextLine();
                            switch (op) {
                                case 1:
                                    Liga liga = Menu.crearLiga(scanner);
                                    Menu.agregarEnJson(archivoLiga, liga);
                                    System.out.println("Liga " + liga.getNombre() + " cargada con exito!");
                                    break;
                                case 2:
                                    Copa copa = Menu.crearCopa(scanner);
                                    Menu.agregarEnJson(archivoCopa, copa);
                                    System.out.println("Copa " + copa.getNombre() + " cargada con exito!");
                                    break;
                                case 3:
                                    break;
                                case 4:
                                    break;
                                case 5:
                                    break;
                                default:
                                    System.out.println("Opción no valida. Intente nuevamente");
                            }
                        }while(op != 3);

                    break;
                case 2:
                    scanner.nextLine(); //Limpiar buffer
                    Equipo equipo = Menu.crearEquipo(scanner, archivoEntrenadores, archivoEquipos);
                    Menu.agregarEnJson(archivoEquipos, equipo);
                    System.out.println("¡El equipo " + equipo.getNombre() + " ha sido cargado con exito!");

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

        }while(opcion!=5);


    }

    public static void menuTareasAdmin(){
        System.out.println("1. Crear Torneo");
        System.out.println("2. Crear Equipo");
        System.out.println("3. Editar torneo");
        System.out.println("4. Declarar ganador del torneo");
        System.out.println("5. Volver al menu anterior");
    }

    public static Equipo crearEquipo(Scanner scanner, ArchivoJson archivoEntrenadores, ArchivoJson archivoEquipos){

        int flag = 1;
        String nombre = "";

        do{
            System.out.println("Ingrese nombre del equipo: ");
            nombre = scanner.nextLine();

            flag = Menu.validarNombreEquipo(archivoEquipos, nombre);

            if(flag == 1){
                System.out.println("El nombre " + nombre + " ya ha sido cargado.");
            }

        }while(flag == 1);

        System.out.println("Deberá elegir un entrenador para su equipo: ");
        Entrenador entrenador = Menu.ElegirEntrenador(scanner, archivoEntrenadores, nombre);


        if (entrenador == null) {
            System.out.println("No se ha seleccionado un entrenador válido.");
            return null; // Si no se selecciona un entrenador, no se crea el equipo
        }

        //Guardo en el entrenador el equipo correspondiente
        Menu.guardarEquipoAEntrenador(archivoEntrenadores, entrenador, nombre);

        return new Equipo(nombre, entrenador);
    }

    public static int validarNombreEquipo(ArchivoJson archivoEquipos, String nombre){

        JSONArray jsonArray = ArchivoJson.leerArray2(archivoEquipos);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);

            if(json.getString("nombre").equals(nombre)) {
                return 1;
            }
        }
        return 0;
    }


    public static void guardarEquipoAEntrenador(ArchivoJson archivoEntrenadores, Entrenador entrenador, String nombre){
        entrenador.setEquipoDirigido(nombre);
        Menu.actualizarJsondePersona(archivoEntrenadores, entrenador);
    }

    public static Entrenador ElegirEntrenador(Scanner scanner, ArchivoJson archivo, String nombre) {
        JSONArray jsonArray = ArchivoJson.leerArray2(archivo);

        if (jsonArray.isEmpty()) {
            System.out.println("No hay entrenadores disponibles.");
            return null; // Retornar null si no hay entrenadores
        }

        System.out.println("\n=== Lista de Entrenadores Disponibles ===");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);

            if(json.getString("equipoDirigido").equals("No tiene equipo")) {
                System.out.println((i + 1) + ". Nombre: " + json.getString("nombre") + ", DNI: " + json.getInt("dni"));
            }
        }

        int opcion = -1;
        do {
            try {
                System.out.print("\nSeleccione el número del entrenador que desea elegir (0 para cancelar): ");
                opcion = scanner.nextInt();


                if (opcion == 0) {
                    System.out.println("Operación cancelada.");
                    return null;
                }

                if (opcion > 0 && opcion <= jsonArray.length()) {

                    JSONObject json = jsonArray.getJSONObject(opcion - 1);
                    // Crear y devolver el objeto Entrenador

                    Entrenador nuevo = Entrenador.deserializar(json);
                    return nuevo;

                } else {
                    System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada no válida. Intente nuevamente.");
                scanner.next(); // Limpiar buffer
            }
        } while (true);

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

//    public static void iniciarSesion(Scanner scanner, ArchivoJson archivo) {
//        boolean usuarioValido = false;
//        String usuarioIngresado = null;
//
//        scanner.nextLine();
//
//        while (!usuarioValido) {
//            System.out.println("Ingrese su usuario: ");
//            String usuario = scanner.nextLine();
//
//            try {
//                if (Menu.validarUsuario(usuario, archivo)) { // Cambiar archivo según corresponda
//                    usuarioValido = true;
//                    usuarioIngresado = usuario; // Guardamos el usuario válido
//                    System.out.println("Usuario encontrado.");
//                } else {
//                    System.out.println("Usuario no encontrado. Por favor, intente de nuevo.");
//                }
//            } catch (Exception e) {
//                System.out.println("Ocurrió un error: " + e.getMessage());
//            }
//        }
//
//        boolean contraseniaValida = false;
//
//        while (!contraseniaValida) {
//            System.out.println("Ingrese su contraseña: ");
//            String contrasenia = scanner.next();
//
//            try {
//                if (validarContrasenia(usuarioIngresado, contrasenia, archivo)) { // Verifica la contraseña
//                    contraseniaValida = true;
//                    System.out.println("Inicio de sesión exitoso. Bienvenido.");
//                } else {
//                    System.out.println("Contraseña incorrecta. Por favor, intente de nuevo.");
//                }
//            } catch (Exception e) {
//                System.out.println("Ocurrió un error: " + e.getMessage());
//            }
//        }
//
//
//    }

    public static String iniciarSesion(Scanner scanner, ArchivoJson archivo) {
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

        return usuarioIngresado;


    }

    public static Entrenador traerEntrenador(ArchivoJson archivo, String nombreUsuario){
        JSONArray jsonArray = ArchivoJson.leerArray2(archivo);  // Lee el archivo JSON
        Entrenador p = null;

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);  // Obtiene cada objeto JSON


            if (json.getString("usuario").equals(nombreUsuario)) {

                    p = Entrenador.deserializar(json);

            }
        }

        return p;  // Devuelve el objeto encontrado o null si no se encuentra
    }

    public static int menuCrearTorneo(Scanner scanner){

        System.out.println("Elija que tipo de torneo desea crear: ");
        System.out.println("1. Liga");
        System.out.println("2. Copa");
        System.out.println("3. Volver atrás");

        int opcion = scanner.nextInt();
        return opcion;

    }

    public static Liga crearLiga(Scanner scanner){

        System.out.println("Ingrese nombre de la Liga: ");
        String nombre = scanner.nextLine();

        return new Liga(nombre);
    }

    public static Copa crearCopa(Scanner scanner){
        System.out.println("Ingrese nombre de la copa: ");
        String nombre = scanner.nextLine();

        return new Copa(nombre);
    }

    public static void agregarEnJson(ArchivoJson archivo, Torneo nuevoDato){
        JSONArray jsonArray = ArchivoJson.leerArray(archivo);
        jsonArray.put(nuevoDato.serializar());
        ArchivoJson.grabarArray(jsonArray,archivo);
    }

    public static void agregarEnJson(ArchivoJson archivo, Equipo nuevoDato){
        JSONArray jsonArray = ArchivoJson.leerArray2(archivo);
        jsonArray.put(nuevoDato.serializar());
        ArchivoJson.grabarArray(jsonArray,archivo);
    }

    public static void actualizarJsondeequipos(ArchivoJson archivo, Equipo nuevoDato){
        JSONArray jsonArray = ArchivoJson.leerArray2(archivo);

        for(int i = 0 ; i < jsonArray.length() ; i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            boolean encontrado = jsonObject.getString("nombre").equals(nuevoDato.getNombre());

            if(encontrado){
                jsonArray.put(i, nuevoDato.serializar());
                break;
            }
        }

        ArchivoJson.grabarArray(jsonArray,archivo);
    }

    public static void actualizarJsondePersona(ArchivoJson archivo, Persona nuevoDato){
        JSONArray jsonArray = ArchivoJson.leerArray2(archivo);

        for(int i = 0 ; i < jsonArray.length() ; i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            boolean dni = jsonObject.getInt("dni") == nuevoDato.getDni();

            if(dni){
                jsonArray.put(i, nuevoDato.serializar());
                break;
            }
        }

        ArchivoJson.grabarArray(jsonArray,archivo);
    }


    public static void menuEditarPerfil(Scanner scanner, ArchivoJson archivo, Persona persona){

        int opcion = 0;

        do {
            System.out.println("1. Editar nombre");
            System.out.println("2. Editar DNI");
            System.out.println("3. Editar usuario");
            System.out.println("4. Editar contraseña");
            System.out.println("5. Volver al menú anterior");
            System.out.println("Ingrese que opción desea: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion){
                case 1:
                    Menu.editarNombre(scanner, archivo, persona);
                    break;
                case 2:
                    Menu.editarDNI(scanner, archivo, persona);
                    break;
                case 3:
                    Menu.editarUsuario(scanner, archivo, persona);
                    break;
                case 4:
                    Menu.editarContrasenia(scanner, archivo, persona);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opción no valida. Intente nuevamente. \n");
            }
        } while(opcion != 5);
    }

    public static void editarNombre(Scanner scanner, ArchivoJson archivo, Persona personaAEditar){
        JSONArray jsonArray = ArchivoJson.leerArray2(archivo);

        System.out.println("Ingrese el nuevo nombre:");
        String nombre = scanner.nextLine();

        personaAEditar.setNombre(nombre);

        Menu.actualizarJsondePersona(archivo, personaAEditar);
        System.out.println("¡Cambio de nombre realizado con exito!");

    }

    public static void editarDNI(Scanner scanner, ArchivoJson archivo, Persona personaAEditar){
        JSONArray jsonArray = ArchivoJson.leerArray2(archivo);
        int flag = 0;
        int dni;

        do {
            System.out.println("Ingrese el nuevo dni:");
            dni = scanner.nextInt();

            try {
                flag = Menu.validarSiYaExisteDni(dni, archivo);
                flag = Menu.CantidadDigitos(dni);

            } catch (ExceptionDniYaIngresado | ExceptionCantDigitosDni e){
                System.out.println(e.getMessage());
                flag = 1;
            }

        } while(flag == 1);

        personaAEditar.setDni(dni);

        Menu.actualizarJsondePersona(archivo, personaAEditar);
        System.out.println("¡Cambio de dni realizado con exito!");

    }

    public static void editarUsuario(Scanner scanner, ArchivoJson archivo, Persona personaAEditar){
        JSONArray jsonArray = ArchivoJson.leerArray2(archivo);
        int flag = 0;
        String nombreNuevo = "";

        do {
            System.out.println("Ingrese el nuevo nombre de usuario:");
            nombreNuevo = scanner.nextLine();

            try {
                flag = Menu.validarSiYaExisteUsuario(nombreNuevo, archivo);

            } catch (ExceptionUsuarioRepetido e){
                System.out.println(e.getMessage());
                flag = 1;
            }

        } while(flag == 1);

        personaAEditar.setUsuario(nombreNuevo);

        Menu.actualizarJsondePersona(archivo, personaAEditar);
        System.out.println("¡Cambio de nombre de usuario realizado con exito!");

    }


    public static void editarContrasenia(Scanner scanner, ArchivoJson archivo, Persona personaAEditar){
        JSONArray jsonArray = ArchivoJson.leerArray2(archivo);

        System.out.println("Ingrese la nueva contrasena:");
        String contrasena = scanner.nextLine();

        personaAEditar.setContrasenia(contrasena);

        Menu.actualizarJsondePersona(archivo, personaAEditar);
        System.out.println("¡Cambio de contrasena realizado con exito!");

    }

    public static int validarSiYaExisteUsuario(String usuario, ArchivoJson archivo) throws ExceptionUsuarioRepetido {
        JSONTokener tokener = ArchivoJson.leer(archivo); // Lee el archivo JSON

        if (tokener != null) {

            JSONArray jsonArray = new JSONArray(tokener); // Convierto el contenido en un JSONArray

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i); // Extrae cada objeto JSON

                if (jsonObject.getString("usuario").equals(usuario)) {

                    throw new ExceptionUsuarioRepetido("El nombre de usuario ingresado ya se encuentra en uso.");
                }
            }
        }
        return 0; // Retorna 0 si no encuentra duplicados
    }



}

