package Control;

import Model.*;

import java.sql.*;
import java.util.ArrayList;

public class InsertarBDD {

    private static final String URL = "jdbc:mysql://localhost:3306/liga_futbol";
    private static final String USR = "root";
    private static final String PWD = "toorDam2";
    private static Connection con = null;

    static {
        try {
            con = DriverManager.getConnection(URL, USR, PWD);

            System.out.println("Se ha establecido la conexión con la Base de Datos");

        } catch (Exception e) {
            System.out.println("No se ha podido establecer conexión con la Base de Datos. Revise configuracion de la URL, USER y PASSWORD");
        }

    }

    public static boolean insertarEntrenadores (Entrenador[] listaEntrenadores) throws SQLException {
        boolean situacion;

        String sentenciaSQL = "INSERT INTO entrenador(Licencia, Nombre, Apellidos) VALUES (?,?,?)";

        PreparedStatement ps = con.prepareStatement(sentenciaSQL);

        try{
            for(Entrenador ent: listaEntrenadores){
                String nombre = ent.getNombre();
                String apellidos = ent.getApellidos();
                int licencia = ent.getLicencia();
                ps.setInt(1, licencia);
                ps.setString(2, nombre);
                ps.setString(3, apellidos);
                ps.executeUpdate();

            }
            ps.close();
            System.out.println("Se han insertado correctamente los Entrenadores");
            situacion=true;
        }catch (Exception e) {
            System.out.println("Fallo en el vertido de los Entrenadores");
            situacion=false;
        }

        return situacion;
    }

    public static boolean insertarEquipos(Equipo[] listaEquipos) throws SQLException {
        boolean resultado;
        ArrayList<Integer> listaLicenciasEntrenador = new ArrayList<>();

        for (int i=0; i<listaEquipos.length-1; i++){
            listaLicenciasEntrenador.add(i, listaEquipos[i].getEntrenador().getLicencia());
        }

        String sentenciaSQL = "INSERT INTO equipo(Nombre, Licencia_Entrenador) VALUES (?,?)";

        PreparedStatement ps = con.prepareStatement(sentenciaSQL);

        try {
            int licenciaEntrenador = -1;

            for (int i = 0; i < listaEquipos.length-1; i++) {
                String nombreEquipo = listaEquipos[i].getNombre();
                for (Integer k : listaLicenciasEntrenador){
                    if(k == listaEquipos[i].getEntrenador().getLicencia()){
                        licenciaEntrenador = listaLicenciasEntrenador.get(i);
                    }
                }

                ps.setString(1, nombreEquipo);
                ps.setInt(2, licenciaEntrenador);

                ps.executeUpdate();
            }

            ps.close();

            System.out.println("Se han insertado correctamente los Equipos");
            resultado = true;

        } catch (Exception e) {
            System.out.println("Problema al insertar los Equipos");
            resultado = false;
        }
        return resultado;
    }

    /***
     * Método que inserta jugadores en la Base de Datos a partir de los ID de equipos que están en la Base de Datos.
     * @param listaJugadores: Lista de equipos
     * @return boolean: Comprobación de que se han insertado los datos correctamente
     * @throws SQLException En caso de no poder realizar la sentencia
     */
    public static boolean insertarJugadores(ArrayList<Jugador[]> listaJugadores) throws SQLException {
        boolean resultado = false;

        ArrayList<Integer> idEquipos = recuperarIdEquipos();

        try {
            String sentenciaSQL = "INSERT INTO jugador(Nombre,Apellidos,Edad,Dorsal,Posicion,Id_Equipo) VALUES(?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sentenciaSQL);
            for (int i=0; i<idEquipos.size(); i++){
                for (int j = 0; j < listaJugadores.size(); j++) {
                    // Creamos un jugador temporal y lo obtenemos del equipo deseado.
                    Jugador jugadorTemporal = new Jugador();
                    jugadorTemporal = listaJugadores.get(i)[j];

                    // Le añadimos el nombre
                    String nombre = jugadorTemporal.getNombre();
                    String apellidos = jugadorTemporal.getApellidos();
                    int edad = jugadorTemporal.getEdad();
                    int dorsal = jugadorTemporal.getDorsal();
                    String posicion = jugadorTemporal.getPosicion();

                    ps.setString(1, nombre);
                    ps.setString(2, apellidos);
                    ps.setInt(3, edad);
                    ps.setInt(4, dorsal);
                    ps.setString(5, posicion);
                    ps.setInt(6, idEquipos.get(i));

                    ps.executeUpdate();
                }
            }

            ps.close();
            System.out.println("Los jugadores se han insertado correctamente");
            return resultado = true;

        } catch (Exception e) {

            System.out.println("No se han podido insertar los jugadores");

        }
        return resultado;
    }

    public static boolean insertarArbitros(Arbitro[] listaArbitros) throws SQLException{
        boolean situacion;

        String sentenciaSQL = "INSERT INTO arbitro(Licencia, Nombre, Apellidos) VALUES (?,?,?)";

        PreparedStatement ps = con.prepareStatement(sentenciaSQL);

        try{
            for(Arbitro ent: listaArbitros){
                String nombre = ent.getNombre();
                String apellidos = ent.getApellidos();
                int licencia = ent.getLicencia();
                ps.setInt(1, licencia);
                ps.setString(2, nombre);
                ps.setString(3, apellidos);
                ps.executeUpdate();

            }
            ps.close();
            System.out.println("Se han insertado correctamente los Arbitros");
            situacion=true;
        }catch (Exception e) {
            System.out.println("Fallo en el vertido de los Arbitros");
            situacion=false;
        }

        return situacion;
    }

    public static boolean insertarPartidos (ArrayList<Partido[]> agrupacionPartidos, Calendario calendario) throws SQLException{
        boolean resultado = false;

        ArrayList<Integer> idEquipos= recuperarIdEquipos();
        Jornada[] jornadas = calendario.getJornadas();

        try {
            String sentenciaSQL = "INSERT INTO partido(Jornada,Id_Equipo_Local,Id_Equipo_Visitante,Fecha,Hora,Goles_Local,Goles_Visitante,Licencia_Arbitro) VALUES(?,?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sentenciaSQL);

            for (int j = 0; j < agrupacionPartidos.size(); j++) {
                Partido[] partidoJornada = agrupacionPartidos.get(j);
                for(Partido par: partidoJornada){
                    if(par.getEquipo1().getNombre()!="Descanso" && par.getEquipo2().getNombre()!="Descanso"){
                        int jornada = (j+1);
                        String fecha = jornadas[j].getFecha();
                        String hora = par.getHoraInicio();
                        int idLocal = recuperarIdEquipo(par.getEquipo1());
                        int idVisitante = recuperarIdEquipo(par.getEquipo2());
                        int golesLocal = par.getResultado1();
                        int golesVisitante = par.getResultado2();
                        int licenciaArbi = par.getArbitro().getLicencia();

                        ps.setInt(1, jornada);
                        ps.setInt(2,idLocal);
                        ps.setInt(3, idVisitante);
                        ps.setString(4, fecha);
                        ps.setString(5, hora);
                        ps.setInt(6, golesLocal);
                        ps.setInt(7, golesVisitante);
                        ps.setInt(8, licenciaArbi);
                        ps.executeUpdate();
                    }

                }
            }

            ps.close();
            System.out.println("Los partidos se han insertado correctamente");
            return resultado = true;

        } catch (Exception e) {

            System.out.println("No se han podido insertar los partidos");

        }
        return resultado;
    }

    /***
     * Método que recupera los ID de los equipos que se encuentran en la Base de Datos.
     * @return ArrayList<Integer>: Lista de ID de los equipos
     * @throws SQLException En caso de no poder realizar la sentencia
     */
    public static ArrayList<Integer> recuperarIdEquipos() throws SQLException {
        ArrayList<Integer> listaIdEquipos = new ArrayList<>();

        // Query que se realizará a la Base de Datos
        String sentenciaSelect = "SELECT Id_Equipo FROM equipo";

        // Objetos encargados de realizar la consulta y guardar los resultados.
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sentenciaSelect);

        // Recorremos el ResultSet para poder obtener los ID de los equipos.
        while (rs.next()) {

            listaIdEquipos.add(rs.getInt("Id_Equipo"));

        }

        rs.close();

        return listaIdEquipos;
    }

    public static int recuperarIdEquipo(Equipo equipo) throws SQLException {

        String nombre = equipo.getNombre();
        if(nombre!="Descanso"){
            String sentenciaSelect = "SELECT Id_Equipo FROM equipo WHERE Nombre=?";
            PreparedStatement ps = con.prepareStatement(sentenciaSelect);
            ps.setString(1, nombre);
            int idVuelta = ps.executeQuery().getInt("Id_Equipo");
            ps.close();
            return idVuelta;
        }
        return -1;
    }

}
