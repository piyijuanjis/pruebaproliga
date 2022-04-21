package Control;

import Model.Equipo;
import Model.Partido;

public class Clasificacion {
    

    public static Partido meterResultadoPartidos(Partido partido, int golesCasa, int golesFuera){
        
        partido.setResultado1(golesCasa);
        partido.setResultado2(golesFuera);
        
        return partido;
    }

    public static void actualizarDatosEquipo(Partido partido){

        Equipo equipo1 = partido.getEquipo1();
        Equipo equipo2 = partido.getEquipo2();

        //Si gana el primer equipo
        if(partido.getResultado1()>partido.getResultado2()){
           
            //Se actualizan los puntos
            equipo1.setPuntos(equipo1.getPuntos() + equipo1.getVictoria());
            equipo2.setPuntos(equipo2.getPuntos() - equipo2.getDerrota());

            //Se actualizan el contador de victoria/perdida/empate
            equipo1.setGanados(equipo1.getGanados() + 1);
            equipo2.setPerdidos(equipo2.getPerdidos() + 1);


        //si gana el segundo equipo    
        }else if (partido.getResultado1()<partido.getResultado2()){

            //Se actualizan los puntos
            equipo1.setPuntos(equipo1.getPuntos() - equipo1.getDerrota());
            equipo2.setPuntos(equipo2.getPuntos() + equipo2.getVictoria());

            //Se actualizan el contador de victoria/perdida/empate
            equipo1.setPerdidos(equipo1.getPerdidos() + 1);
            equipo2.setGanados(equipo2.getGanados() + 1);
            

        //si empatan
        }else{

             //Se actualizan los puntos
             equipo1.setPuntos(equipo1.getPuntos() + equipo1.getEmpatados());
             equipo2.setPuntos(equipo2.getPuntos() + equipo2.getEmpatados());

             //Se actualizan el contador de victoria/perdida/empate
            equipo1.setPerdidos(equipo1.getEmpatados() + 1);
            equipo2.setGanados(equipo2.getEmpatados() + 1);
        }

        //Todas las siguientes cosas da igual se hacen siempre, independientemente de si han ganado/perdido/empatado

        //se actualizan el contador de partidos
        equipo1.setJugados(equipo1.getJugados() + 1);
        equipo2.setJugados(equipo2.getJugados() + 1);

        //Se actualizan los goles
        equipo1.setgFavor(equipo1.getgFavor() + partido.getResultado1());
        equipo1.setgContra(equipo1.getgContra() + partido.getResultado2());
        equipo2.setgFavor(equipo2.getgFavor() + partido.getResultado2());
        equipo2.setgContra(equipo2.getgContra() + partido.getResultado1());

        //Se actualizan la diferencia de goles
        equipo1.setDifGoles(equipo1.getgFavor(), equipo1.getgContra());
        equipo2.setDifGoles(equipo2.getgFavor(), equipo2.getgContra());


        

    }

    public static void imprimirClasificacion(Partido partido){
        System.out.println("Model.Partido 1");
        System.out.println(partido.getResultado1()+"---VS---"+partido.getResultado2());

        System.out.println("Model.Equipo 1");
        System.out.println("");
        System.out.println("Jugados: "+partido.getEquipo1().getJugados());
        System.out.println("ganados: "+partido.getEquipo1().getGanados());
        System.out.println("perdidos: "+partido.getEquipo1().getPerdidos());
        System.out.println("empatados: "+partido.getEquipo1().getEmpatados());
        System.out.println("Goles marcados: "+partido.getEquipo1().getgFavor());
        System.out.println("Goles que le han marcado: "+partido.getEquipo1().getgContra());
        System.out.println("diferencia de goles: "+partido.getEquipo1().getDifGoles());
        System.out.println("");
        System.out.println("");
        System.out.println("Model.Equipo 2");
        System.out.println("");
        System.out.println("Jugados: "+partido.getEquipo2().getJugados());
        System.out.println("ganados: "+partido.getEquipo2().getGanados());
        System.out.println("perdidos: "+partido.getEquipo2().getPerdidos());
        System.out.println("empatados: "+partido.getEquipo2().getEmpatados());
        System.out.println("Goles marcados: "+partido.getEquipo2().getgFavor());
        System.out.println("Goles que le han marcado: "+partido.getEquipo2().getgContra());
        System.out.println("diferencia de goles: "+partido.getEquipo2().getDifGoles());

    }
}
