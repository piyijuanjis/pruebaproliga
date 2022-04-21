package View;

import Control.Calendario;
import Control.Creacion;
import Control.InsertarBDD;
import Control.Setting;
import Model.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class ToUpload {

    public static void main(String[] args) throws SQLException {

        Control.Setting ajustes = new Setting();
        ajustes.predeterminado();

        int nEquipos = ajustes.getnEquipos();
        int nJugadores = ajustes.getnJugadores();

//        Creacion Equipos y guardado en Arraylist
        Equipo[] equipos = Creacion.crearEquipos(nEquipos, nJugadores);
        Entrenador[] entrenadores = new Entrenador[nEquipos];

        ArrayList<Jugador[]> listadoJugadores = new ArrayList<>();
        ArrayList<Partido[]> listadoPartidos = new ArrayList<>();
        int cont = 0;

        for (Equipo eq : equipos) {
            if (eq.getNombre() != "Descanso") {
                Jugador[] jugadores = eq.getJugadores();
                entrenadores[cont] = eq.getEntrenador();
                listadoJugadores.add(cont, jugadores);
                cont++;
            }
        }
        cont = 0;

//        Creacion de Arbitros para usar en el Calendario
        Arbitro[] arbitros = Creacion.crearArbitros((nEquipos + 2) / 2);
//        Creamos el Calendario; a partir del cual obtenemos las jornadas
        Calendario calendario = new Calendario(equipos, arbitros, ajustes);
        Jornada[] jornadas = calendario.getJornadas();

//        Obtenemos los Partidos
        for (Jornada jorn : jornadas) {
            Partido[] partidos = jorn.getPartidos();
            listadoPartidos.add(cont, partidos);
            cont++;
        }

//        Implementacion de los datos en la base de Datos

        InsertarBDD.insertarEntrenadores(entrenadores);
        InsertarBDD.insertarEquipos(equipos);
        InsertarBDD.insertarJugadores(listadoJugadores);
        InsertarBDD.insertarArbitros(arbitros);
        InsertarBDD.insertarPartidos(listadoPartidos, calendario);

//        //Mostrar Entrenadores
//        System.out.println("----------------------------------------------------------ENTRENADORES");
//
//        for(Entrenador e: entrenadores){
//            System.out.println("Nombre: "+e.getNombre());
//            System.out.println("Apellidos: "+e.getApellidos());
//            System.out.println("Licencia (IdArbitro): "+e.getLicencia());
//        }
//        //Mostrar Equipos
//        System.out.println("----------------------------------------------------------EQUIPOS");
//
//        for (Equipo eq: equipos){
//            if(!eq.getNombre().equals("Descanso")){
//                System.out.println(eq.infoEnClasitoString());
//                System.out.println(eq);
//            }
//        }
//        //Mostrar Jugadores
//        System.out.println("----------------------------------------------------------JUGADORES");
//
//        for(int i=0; i<listadoJugadores.size(); i++){
//            Jugador[] jugadorEquipo = listadoJugadores.get(i);
//            for(Jugador jug: jugadorEquipo){
//                System.out.println("Nombre: "+jug.getNombre());
//                System.out.println("Apellidos: "+jug.getApellidos());
//                System.out.println("Edad: "+jug.getEdad());
//                System.out.println("Dorsal: "+jug.getDorsal());
//                System.out.println("Posicion: "+jug.getPosicion());
//
//            }
//        }
//
//        //Mostrar Arbitros
//        System.out.println("----------------------------------------------------------ARBITROS");
//
//        for ( Arbitro arb : arbitros) {
//            System.out.println("Nombre: "+arb.getNombre());
//            System.out.println("Apellidos: "+arb.getApellidos());
//            System.out.println("Licencia (IdArbitro): "+arb.getLicencia());
//        }
//        //Mostrar Partidos
//        System.out.println("----------------------------------------------------------PARTIDOS");
//
//        for(int i=0; i<listadoPartidos.size(); i++){
//            Partido[] partidoJornada = listadoPartidos.get(i);
//            for(Partido par: partidoJornada){
//                System.out.println("--------");
//                System.out.println("Model.Jornada: "+(i+1));
//                System.out.println("ENFRENTAMIENTO: ");
//                System.out.println("Equipo1: "+par.getEquipo1().getNombre());
//                System.out.println("Resultado: "+par.getResultado1());
//                System.out.println("Equipo2: "+par.getEquipo2().getNombre());
//                System.out.println("Resultado: "+par.getResultado2());
//                System.out.println("Licencia del Model.Arbitro: "+par.getArbitro().getLicencia());
//                System.out.println("Fecha: "+jornadas[i].getFecha());
//                System.out.println("Hora: "+par.getHoraInicio());
//
//            }
//        }
//    }
    }
}
