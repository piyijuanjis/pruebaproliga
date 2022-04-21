package Control;

import Model.Arbitro;
import Model.Equipo;
import Model.Jornada;
import Model.Partido;

/**
 * @author Erkus00
 * @version 1.0
 *
 * Clase Control.Calendario. Crea un Control.Calendario compuesto por las Jornadas que tendrá la Liga
 * Formada por constructor, getters y setters; y un ToString que permitirá mostrar los atributos
 *
 * Sus Atributos son:
 * <ul>
 *     <li>jornadas - Listado de las Jornadas que tendrá la Liga, con los enfrentamientos pertinentes y los arbitros asignados (Model.Jornada[])</li>
 * </ul>
 */
public class Calendario{
    //    Atributos
    protected Jornada[] jornadas;
    protected String[] fechasJornada;

    /**
     * Constructor de un Control.Calendario
     * @param equipos Array de los Equipos de Liga
     * @param arbitros Array con los Arbitros que hay disponibles en la Liga
     */
    public Calendario(Equipo[] equipos, Arbitro[] arbitros, Setting ajustes) {

        int numeroEquipos = equipos.length;
        int numeroPartidos = numeroEquipos / 2;
        int numeroJornadas = (numeroEquipos - 1) * 2;
        String[] listaHorarios = {
                "9:00", "10:30", "12:00", "16:00", "17:30", "19:00"
        };

        Equipo[][] emparejamientos = new Equipo[2][numeroPartidos];

//        Primera columna del array emparejamientos
        for (int i = 0; i< numeroPartidos; i++ ) {
            emparejamientos[0][i]=equipos[i];
        }
//        Segunda columna del array emparejamientos
        for (int j = numeroPartidos -1; j>=0; j--) {
            emparejamientos[1][j]=equipos[numeroEquipos-1-j];
        }

//        Se establecen el numero de jornadas
        this.jornadas = new Jornada[numeroJornadas];

//        Algoritmo para emparejar cada equipo con todos los equipos menos consigo mismo
        for(int i = 0; i<numeroJornadas/2; i++) {

            Partido[] partidosIda = new Partido[numeroPartidos];
            Partido[] partidosVuelta = new Partido[numeroPartidos];

            for (int j = 0; j < numeroPartidos; j++) {

                int indiceHora = (int)Math.floor(Math.random()*6);
                partidosIda[j] = new Partido();
                partidosIda[j].setEquipo1(emparejamientos[0][j]);
                partidosIda[j].setEquipo2(emparejamientos[1][j]);
                partidosIda[j].setArbitro(arbitros[j]);
                partidosIda[j].setHoraInicio(listaHorarios[indiceHora]);

                indiceHora = (int)Math.floor(Math.random()*6);
                partidosVuelta[j] = new Partido();
                partidosVuelta[j].setEquipo1(emparejamientos[1][j]);
                partidosVuelta[j].setEquipo2(emparejamientos[0][j]);
                partidosVuelta[j].setArbitro(arbitros[j]);
                partidosVuelta[j].setHoraInicio(listaHorarios[indiceHora]);
            }
            jornadas[i] = new Jornada();
            jornadas[i].setPartidos(partidosIda);
            jornadas[jornadas.length - i - 1] = new Jornada();
            jornadas[jornadas.length - i - 1].setPartidos(partidosVuelta);

//          Mover la tabla de emparejamientos para cambiar los enfrentamientos
            Equipo[][] emparejamientosAux = new Equipo[2][numeroPartidos];

            for (int k = 0; k < numeroPartidos; k++) {
                if (k == 0) {
                    emparejamientosAux[0][0] = emparejamientos[0][0];
                }else if (k<numeroPartidos-1) {
                    emparejamientosAux[0][k+1]=emparejamientos[0][k];
                }else {
                    emparejamientosAux[1][k]=emparejamientos[0][k];
                }
            }
            for (int k = 0; k < numeroPartidos; k++) {
                if (k==0) {
                    emparejamientosAux[0][1]=emparejamientos[1][0];
                }else {
                    emparejamientosAux[1][k-1]=emparejamientos[1][k];
                }
            }

            emparejamientos=emparejamientosAux;
        }
        int diaAnno=calculoDia(ajustes.getMesInicio(), ajustes.getDiaInicio());
        for(Jornada jorn: jornadas){
            String fechaJornada = calculoFecha(diaAnno);
            jorn.setFecha(fechaJornada);
            diaAnno+=7;
        }
        setJornadas(jornadas);
    }

    public static int calculoDia(String mesIn, int diaIn){
        int dia;
        String mes=mesIn.toUpperCase();

        if (mes.equals("ENERO")){
            dia=diaIn;
            return dia;
        }
        if (mes.equals("FEBRERO")){
            dia=diaIn+31;
            return dia;
        }
        if(mes.equals("MARZO")){
            dia=diaIn+59;
            return dia;
        }
        if(mes.equals("ABRIL")){
            dia=diaIn+90;
            return dia;
        }
        if(mes.equals("MAYO")){
            dia=diaIn+120;
            return dia;
        }
        if(mes.equals("JUNIO")){
            dia=diaIn+151;
            return dia;
        }
        if(mes.equals("JULIO")){
            dia=diaIn+181;
            return dia;
        }
        if(mes.equals("AGOSTO")){
            dia=diaIn+211;
            return dia;
        }
        if(mes.equals("SEPTIEMBRE")){
            dia=diaIn+242;
            return dia;
        }
        if(mes.equals("OCTUBRE")){
            dia=diaIn+272;
            return dia;
        }
        if(mes.equals("NOVIEMBRE")){
            dia=diaIn+303;
            return dia;
        }
        if(mes.equals("DICIEMBRE")){
            dia=diaIn+333;
            return dia;
        }
        return 10000001;        //ERROR al inctroducir la fecha
    }

    public static String calculoFecha(int dia) {

        String fechaFinal;

        /*ENERO*/
        if (1 <= dia && dia <= 31) {
            fechaFinal = (dia + "/01/2022");
            return fechaFinal;
        }
        /*FEBRERO*/
        if (32 <= dia && dia <= 59) {
            fechaFinal = ((dia - 31) + "/02/2022");
            return fechaFinal;
        }
        /*MARZO*/
        if (60 <= dia && dia <= 90) {
            fechaFinal = ((dia - 59) + "/03/2022");
            return fechaFinal;
        }
        /*ABRIL*/
        if (91 <= dia && dia <= 120) {
            fechaFinal = ((dia - 90) + "/04/2022");
            return fechaFinal;
        }
        /*MAYO*/
        if (121 <= dia && dia <= 151) {
            fechaFinal = ((dia - 120) + "/05/2022");
            return fechaFinal;
        }
        /*JUNIO*/
        if (152 <= dia && dia <= 181) {
            fechaFinal = ((dia - 151) + "/06/2022");
            return fechaFinal;
        }
        /*JULIO*/
        if (182 <= dia && dia <= 211) {
            fechaFinal = ((dia - 181) + "/07/2022");
            return fechaFinal;
        }
        /*AGOSTO*/
        if (212 <= dia && dia <= 242) {
            fechaFinal = ((dia - 211) + "/08/2022");
            return fechaFinal;
        }
        /*SEPTIEMBRE*/
        if (243 <= dia && dia <= 272) {
            fechaFinal = ((dia - 242) + "/09/2022");
            return fechaFinal;
        }
        /*OCTUBRE*/
        if (273 <= dia && dia <= 303) {
            fechaFinal = ((dia - 272) + "/10/2022");
            return fechaFinal;
        }
        /*NOVIEMBRE*/
        if (304 <= dia && dia <= 333) {
            fechaFinal = ((dia - 303) + "/11/2022");
            return fechaFinal;
        }
        /*DICIEMBRE*/
        if (334 <= dia && dia <= 365) {
            fechaFinal = ((dia - 333) + "/12/2022");
            return fechaFinal;
        }

        return fechaFinal = ("ERROR Fecha no disponible");
    }

    /**
     * Método que devuelve la lista de todas las jornadas que hay en la Liga
     * @return Un array de las jornadas de la Liga
     */
    public Jornada[] getJornadas() {
        return jornadas;
    }

    /**
     * Método que establece todas las jornadas que hay en la Liga
     * @param jornadas Listado de Jornadas
     */
    public void setJornadas(Jornada[] jornadas) {
        this.jornadas = jornadas;
    }

    public String[] getFechasJornada() {
        return fechasJornada;
    }

    public void setFechasJornada(String[] fechasJornada) {
        this.fechasJornada = fechasJornada;
    }

    /**
     * Función que permite mostrar el Control.Calendario establecido en la Liga
     * @return String con todas las jornadas que hay en la Liga y los enfrentamientos
     */
    @Override
    public String toString() {
        String cadena="Control.Calendario Oficial: \n";
        for (int i=0;i<this.jornadas.length;i++) {
            cadena+=(i+1)+"ª "+jornadas[i]+"\n";
        }
        return cadena;
    }
}
