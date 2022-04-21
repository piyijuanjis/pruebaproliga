package Control;

import Model.Arbitro;
import Model.Entrenador;
import Model.Equipo;
import Model.Jugador;

/**
 * @author Erkus00
 * @version 1.0
 *
 * Clase Control.Creacion. Clase que acoge los métodos encargados de crear las listas de los distintos objetos. No posee atributos propios ni un ToString
 */

public class Creacion {

    /**
     * Método que crea una lista de los Equipos que participaran en la Liga
     * @param numeroEquipos - Numero de Equipos que se desea crear
     * @param numJugadores - Numero de Jugadores que habrá en cada Model.Equipo
     * @return Una lista con todos los Equipos y sus propiedades
     */
    public static Equipo[] crearEquipos (int numeroEquipos, int numJugadores) {

//        Diccionarios para los nombres de los Equipos
        String[] nombres = {"Los Anfisbenas", "Las Aracnes", "Los Argos", "Los Basiliscos", "Los Centauros", "Los Ceerberos", "Los Cetus",
                "Los Cercopes", "Los Carontes", "Los Caribdis", "Los Ciclopes", "Los Demonios", "Los Eurinomos", "Las Empusas", "Las Erinias",
                "Los Gegenes", "Los Geriones", "Los Gigantes", "Las Gorgonas", "Las Grayas", "Los Hecatonquiros", "Las Arpias", "Los Hipocampos",
                "Los Ictiocentauros", "Los Ipotanes", "Los Keres", "Los Kobalos", "Los Lestrigones", "Los Licaones", "Las Lamias",
                "Las Hidras de Lerna", "Los Leones", "Las Manticoras", "Los Makhais", "Los Minotauros", "Los Mormos", "Los Onocentauros",
                "Los Ofiotauros", "Los Oriones", "Los Ortros", "Los Panes", "Los Satiros", "Las Escilas", "Las Sirenas", "Los Taraxipos",
                "Los Telequines", "Los Tifones", "Los Grifos", "Los Fenix", "Los Alkonostes", "Los Balwani", "Los Koscheii", "Los Lisovikii",
                "Los Peruni", "Las Rusalki", "Los Vodianie", "Los Vondiki", "Los Domovie", "Los Chleni", "Los Jui", "Las Siski", "Las Zhopi", "Los Mineti"};

        String[] ciudades = {"Pueblo Paleta", "Ciudad Verde", "Ciudad Plateada", "Ciudad Celeste", "Ciudad Carmin", "Ciudad Lavanda", "Ciudad Azulona",
                "Ciudad Azafran", "Ciudad Fucsia", "Isla Canela", "Pueblo Primavera", "Ciudad Cerezo", "Ciudad Malva", "Pueblo Azalea", "Ciudad Trigal",
                "Ciudad Iris", "Ciudad Olivo", "Ciudad Orquidea", "Pueblo Caoba", "Ciudad Endrino", "Ciudad Blanca", "Villa Raiz", "Pueblo Escaso", "Ciudad Petalia",
                "Ciudad Ferrica", "Pueblo Azuliza", "Ciudad Portual", "Ciudad Malvalona", "Pueblo Verdegal", "Pueblo Paradal", "Pueblo Lavacalda", "Ciudad Arborada",
                "Ciudad Calagua", "Ciudad Algaria", "Arrecipolis", "Pueblo oromar", "Ciudad Colosalia", "Isla Prima", "Isla Secunda", "Isla Tera", "Isla Quarta",
                "Isla Inta", "Isla Eta", "Isla Inta", "Ciudad Oasis", "Pueblo Pirita", "Villa Agata", "Basix", "Puerto Ancla", "Isla Tempesta", "Villavera", "Otonia",
                "Hiberna", "Villaestio", "Ciudad Canal", "Pueblo Caelestis", "Pueblo Haruba", "Pueblo Arcilla", "Pueblo Terracota", "Ciudad Gres", "Ciudad Esmalte",
                "Ciudad Porcelana", "Ciudad Mayolica", "Pueblo Biscuit", "Ciudad Fayenza", "Ciudad Caolin"};

        Equipo[] listaEquipos = new Equipo[numeroEquipos];

        for (int i = 0; i <numeroEquipos; i++) {
            //Creamos Model.Equipo
            Equipo equipo = new Equipo();

            String nombre="";
            String ciudad="";

            //Elegimos random un nombre y una ciudad de las listas respectivas.
            if(i<nombres.length && i<ciudades.length){
                nombre = nombres[i];
                ciudad = ciudades[i];
            }else{
                if(i<nombres.length && i>=ciudades.length){
                    nombre = nombres[i];
                    int selector=(int) Math.floor(Math.random()*17);
                    ciudad = ciudades[selector];

                }else if(i>=nombres.length && i<ciudades.length){
                    int selector=(int) Math.floor(Math.random()*17);
                    nombre = nombres[selector];
                    ciudad = ciudades[i];
                }
            }


            //Las pegamos con un "de" en medio
            String nombreF = nombre+" de "+ciudad ;

            equipo.setNombre(nombreF);

            //Continuamos con entrenador
            Entrenador entrenador = crearEntrenador(equipo, i);
            equipo.setEntrenador(entrenador);

            //Meter los jugadores
            Jugador[] jugadores = crearJugadores(numJugadores, equipo);
            equipo.setJugadores(jugadores);

            listaEquipos[i] = equipo;
        }
        //En caso de que el numero de Equipos sea Impar: Se crea un Model.Equipo más con el nombre "Descanso" que representa que el Model.Equipo que se enfrente
        //a este, descansa en esa Model.Jornada
        if (numeroEquipos%2!=0) {
            Equipo[] nuevoEquipos = new Equipo[numeroEquipos+1];

            System.arraycopy(listaEquipos, 0, nuevoEquipos, 0, numeroEquipos);
            nuevoEquipos[nuevoEquipos.length-1] = new Equipo();
            nuevoEquipos[nuevoEquipos.length-1].setNombre("Descanso");
            listaEquipos=nuevoEquipos;
        }
        return listaEquipos;
    }

    /**
     * Método que crea una Lista de Jugadores
     * @param numeroJugadores - Numero de Jugadores
     * @param equipo - Model.Equipo al que pertenecen los Jugadores
     * @return Lista de Jugadores del Model.Equipo
     */
    public static Jugador[] crearJugadores (int numeroJugadores, Equipo equipo) {
        //Listado de Nombres, Apellidos, Posiciones para generador random
        String[] nombres = {"Altair", "Ezio", "Sora", "Connor", "Kratos", "Link", "Zelda", "Kirby", "Megaman", "Bowser",
                "Ratchet", "Donkey Kong", "Goku", "Doraemon", "Perchita", "Suneo", "Gandalf", "Frodo", "Bilbo",
                "Dovahkiin", "Sonic", "Cloud", "Sephiroth", "Dante", "Geralt", "Trevor", "Victor", "Jinx", "VI", "Catelyn",
                "Katarina", "Treiny", "Shuna", "Talim", "Ivy", "CJ", "Spyro", "Squall", "Yuna", "Riku", "Jill", "Wesker",
                "Kassandra", "Alexios", "Luigi", "Cítrico", "Duke", "Tifa", "Alucard", "Drake", "Koopa", "Axel", "Arwen",
                "Eowyn", "Sakura", "Naruto", "Ash", "Sasuke", "Vegeta", "Sauron", "Legolas", "Leia", "Luke", "Vader"};

        String[] apellidos = {"Marui", "Shima", "Sawa", "Toyo", "Cromwell", "Keller", "Perdido", "Zimmerman", "Anderson", "Gasper",
                "Degurechaff", "Jimenez", "Gonzalez", "Lamata", "Feliz", "Bragueta", "Amiano", "Enamorado", "Nigote", "Delano", "Messina",
                "Di Montelroso", "Krauser", "Marston", "Auditore", "Bernstein", "Bogard", "Briggs", "Chaolan", "Eggman", "Dorian", "Dragunov",
                "Drake", "Winters", "Falcon", "Freeman", "Hudson", "Ibn-La' Ahad", "Yamazaki", "Wesker", "Snake", "Tsung", "Takahashi", "Strife",
                "Schugerg", "Schtauffen", "Sakazaki", "Hayabusa", "Porter", "Nikaido", "Nanakase", "Chief", "Tyrell", "Lannister", "Baratheon",
                "Martell", "Stark", "Targaryen", "Tully", "Okabe", "Krusoe", "Vergassola", "Ali Baba", "Serebriakov", "Sempai"};

        String[] roles = {"Portero","Delantero","Centrocampista","Defensa"};

        //Estructura de Array de Jugadores
        Jugador[] jugadores = new Jugador[numeroJugadores];

        for (int i = 0; i < numeroJugadores; i++) {
            //Crear un Model.Jugador
            Jugador jug = new Jugador();

            //Nombre
            int numero = (int) Math.floor(Math.random() * nombres.length);
            String nombre = nombres[numero];
            jug.setNombre(nombre);

            //Apellido
            numero = (int) Math.floor(Math.random() * nombres.length);
            String apellido = apellidos[numero];
            jug.setApellidos(apellido);

            //Dorsal
            jug.setDorsal(i + 1);

            //Model.Equipo
            jug.setEquipo(equipo);

            //Edad
            int edad = (int) Math.floor(Math.random() * 80);
            if(edad<18){
                edad+=18;
            }
            jug.setEdad(edad);

            numero = (int) Math.floor(Math.random() * roles.length);
            String posicion = roles[numero];
            jug.setPosicion(posicion);
            jugadores[i] = jug;
        }
        return jugadores;
    }

    /**
     * Método que crea una lista de Arbitros. Cada uno de los elementos será asignado a un Model.Partido
     * @param numArbitros - Numero total de Arbitros que se desean crear
     * @return Lista de Arbitros
     */
    public static Arbitro[] crearArbitros (int numArbitros) {
        //Lista de Nombres disponibles
        String[] nombres = {"Altair", "Ezio", "Sora", "Connor", "Kratos", "Link", "Zelda", "Kirby", "Megaman", "Bowser",
                "Ratchet", "Donkey Kong", "Goku", "Doraemon", "Perchita", "Suneo", "Gandalf", "Frodo", "Bilbo",
                "Dovahkiin", "Sonic", "Cloud", "Sephiroth", "Dante", "Geralt", "Trevor", "Victor", "Jinx", "VI", "Catelyn",
                "Katarina", "Treiny", "Shuna", "Talim", "Ivy", "CJ", "Spyro", "Squall", "Yuna", "Riku", "Jill", "Wesker",
                "Kassandra", "Alexios", "Luigi", "Cítrico", "Duke", "Tifa", "Alucard", "Drake", "Koopa", "Axel", "Arwen",
                "Eowyn", "Sakura", "Naruto", "Ash", "Sasuke", "Vegeta", "Sauron", "Legolas", "Leia", "Luke", "Vader"};

        String[] apellidos = {"Marui", "Shima", "Sawa", "Toyo", "Cromwell", "Keller", "Perdido", "Zimmerman", "Anderson", "Gasper",
                "Degurechaff", "Jimenez", "Gonzalez", "Lamata", "Feliz", "Bragueta", "Amiano", "Enamorado", "Nigote", "Delano", "Messina",
                "Di Montelroso", "Krauser", "Marston", "Auditore", "Bernstein", "Bogard", "Briggs", "Chaolan", "Eggman", "Dorian", "Dragunov",
                "Drake", "Winters", "Falcon", "Freeman", "Hudson", "Ibn-La' Ahad", "Yamazaki", "Wesker", "Snake", "Tsung", "Takahashi", "Strife",
                "Schugerg", "Schtauffen", "Sakazaki", "Hayabusa", "Porter", "Nikaido", "Nanakase", "Chief", "Tyrell", "Lannister", "Baratheon",
                "Martell", "Stark", "Targaryen", "Tully", "Okabe", "Krusoe", "Vergassola", "Ali Baba", "Serebriakov", "Sempai"};

        //Estructura de Array de Jugadores
        Arbitro[] arbitros = new Arbitro[numArbitros];

        for (int i = 0; i < numArbitros; i++) {
            //Crear un Model.Arbitro
            Arbitro arb = new Arbitro();

            //Nombre
            int numero = (int) Math.floor(Math.random() * nombres.length);
            String nombre = nombres[numero];
            arb.setNombre(nombre);

            //Apellido
            numero = (int) Math.floor(Math.random() * nombres.length);
            String apellido = apellidos[numero];
            arb.setApellidos(apellido);

            //Licencia
            numero = 100 + i;
            arb.setLicencia(numero);

            arbitros[i] = arb;
        }
        return arbitros;
    }

    /**
     * Método que crea un Model.Entrenador y que será asignado al Model.Equipo
     * @param equipo - Model.Equipo al que pertenece el Model.Entrenador
     * @return Un Model.Entrenador
     */
    public static Entrenador crearEntrenador (Equipo equipo, int cont) {
        //Listado de Nombres, Apellidos, Posiciones para generador random
        String[] nombres = {"Antonio", "Pepito", "Alejandra", "Ismael", "Hugo", "Oliver", "Kalesi",
                "Ingrid", "Astrid", "Indira", "Jenny", "Jessi", "Vane", "Joel", "Bruno",
                "Sasha", "Billie", "Masha", "Pingu"};

        String[] apellidos = {"Marui", "Shima", "Sawa", "Toyo", "Cromwell", "Keller", "Perdido", "Zimmerman", "Anderson", "Gasper",
                "Degurechaff", "Jimenez", "Gonzalez", "Lamata", "Feliz", "Bragueta", "Amiano", "Enamorado", "Nigote", "Delano", "Messina",
                "Di Montelroso", "Krauser", "Marston", "Auditore", "Bernstein", "Bogard", "Briggs", "Chaolan", "Eggman", "Dorian", "Dragunov",
                "Drake", "Winters", "Falcon", "Freeman", "Hudson", "Ibn-La' Ahad", "Yamazaki", "Wesker", "Snake", "Tsung", "Takahashi", "Strife",
                "Schugerg", "Schtauffen", "Sakazaki", "Hayabusa", "Porter", "Nikaido", "Nanakase", "Chief", "Tyrell", "Lannister", "Baratheon",
                "Martell", "Stark", "Targaryen", "Tully", "Okabe", "Krusoe", "Vergassola", "Ali Baba", "Serebriakov", "Sempai"};

        Entrenador entrenador = new Entrenador();

        //Nombre y apellido
        int numero = (int) Math.floor(Math.random() * nombres.length);
        String nombre = nombres[numero];
        entrenador.setNombre(nombre);
        String apellido = apellidos[numero];
        entrenador.setApellidos(apellido);

        //Model.Equipo
        equipo.setEntrenador(entrenador);

        //Licencia
        int licencia = cont + 200;
        entrenador.setLicencia(licencia);

        return entrenador;
    }
}

