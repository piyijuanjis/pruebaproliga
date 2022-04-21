package View;

/**
 * @author Erkus00
 * @version 1.0
 *
 * Clase en la que se encuentran todos los menús del programa, métodos varios para leer entradas por teclado y un ultimo que sirve para limpiar la pantalla
 * Clase View.View
 */

import Control.Setting;

import java.util.Scanner;
public class View {

//    Métodos
    /**
     * Método principal de la clase. Muestra el menú inicial del Programa (Estructura establecida en {@link #imprimirMenu()}) y contiene las funciones que permiten ajustar la Liga
     * a nuestras necesidades. En su interior encontramos 2 funciones:
     * <ul>
     *     <li>Menú a mostrar --> Estructura establecida en {@link #imprimirMenu()}</li>
     *     <li>Método de selección --> En este, podrás elegir si quieres continuar con los ajustes predefinidos
     *          internamente en el programa o si deseas cambiarlos {@link #elegirMenu(Control.Setting)}</li>
     * </ul>
     *
     * Una vez configurado, te muestra las preferencias elegidas y guardadas
     * Si deseara cambiar algún campo una vez haya salido de la elección inicial, deberá reiniciar el programa
     *
     * @param ajustes - Objeto de la clase Control.Setting donde se guardarán las configuraciones del programa
     */
    public void configuracion(Setting ajustes) {
        System.out.println();
        System.out.println("Bienvenido al programa de LA LIGA, indique que desea modificar. En caso de querer dejar las predeterminadas, pulse 0: ");
        System.out.println();
        imprimirMenu();
        elegirMenu(ajustes);
        System.out.println();

        System.out.println("Preferencias elegidas.");
        System.out.println();
        System.out.print(ajustes.toString());
        System.out.println();
        System.out.println();
        System.out.println("Reiniciar programa en caso de equivocacion");

        for(int i=0; i<4; i++){
            System.out.println();
        }
    }

    /**
     * Método que muestra el menú inicial del programa
     */
    public void imprimirMenu() {
        System.out.println("-------------Opciones-----------");
        System.out.println("0. Dejar ajustes Predeterminados");
        System.out.println("1. Ajustes Basicos");
        System.out.println("2. Puntuacion");
        System.out.println("3. Fechas");
    }

    /**
     * Método que permite la elección de las preferencias que se deseen
     * Posibles opciones
     * <ul>
     *     <li>Dejar los ajustes predeterminados{@link Setting#predeterminado()}. Saldría de la selección, pues ya están establecidos</li>
     *     <li>Cambiar los Ajustes Basicos. Accediendo a {@link #ajustesBasicos(Setting)}</li>
     *     <li>Cambiar criterios de Puntuación. Por medio del metodo {@link #puntuacion(Setting)}</li>
     * </ul>
     * @param ajustes - Objeto del tipo Control.Setting en el cual se sobreescribirán las preferencias que el usuario desee cambiar
     */
    public void elegirMenu(Setting ajustes) {
        Scanner sc = new Scanner(System.in);
        int set;
        boolean exit = false;

        while (!exit) {
            set = leerNumero();
            switch (set) {
//              Ajustes Predeterminados
                case 0:
                    exit = true;
                    break;
//              Ajustes Basicos
                case 1:
                    ajustesBasicos(ajustes);
                    break;
//              Ajustes en el Sistema de Puntos
                case 2:
                    puntuacion(ajustes);
                    break;
//              Ajuste de la fecha de Inicio de la Liga
                case 3:
                    fechas(ajustes);
                    break;
//              Caso de Introducir una opción que no esté Disponible
                default:
                    System.out.println("Eleccion no disponible, intentelo de nuevo");
                    break;
            }
//            Pregunta si se desea Salir. En caso de que no, se podrá cambiar algún ajuste introducido. OJO --> Habrá que cambiar/reintroducir todos los datos introducidos en esa parte
            System.out.println("¿Desea Salir de la configuración de los ajustes? y/n");
            char salir = sc.next().charAt(0);
            if (salir == 'y' || salir == 'Y') {
                exit = true;
                for(int i=0; i<4; i++){
                    System.out.println();
                }
            } else {
                exit = false;
                imprimirMenu();
            }
        }
    }

    /**
     * Método que te muestra uno a uno los ajustes básicos para que los establezcas. En este método puedes cambiar:
     * <ul>
     *     <li>Nombre de la Liga</li>
     *     <li>Numero de Equipos que formaran la Liga</li>
     *     <li>Numero de Jugadores que hay en cada Model.Equipo</li>
     * </ul>
     * @param ajustes - Objeto del tipo Control.Setting en el cual se sobreescribirán las preferencias que el usuario desee cambiar
     */
    public void ajustesBasicos(Setting ajustes) {
        System.out.println("Elija el nombre de la Liga: ");
        String nombre = leerEntrada();
        ajustes.setNombre(nombre);

        System.out.println("Elija el numero de Equipos: ");
        int nEquipos = leerNumero();
        ajustes.setnEquipos(nEquipos);

        System.out.println("Elija el numero de Jugadores: ");
        int nJugadores = leerNumero();
        ajustes.setnJugadores(nJugadores);
    }

    /**
     * Método que te muestra cada una de las posibles opciones de resultado de un partido para que establezcas la puntuación de cada:
     * <ul>
     *     <li>Puntos Obtenidos por una Derrota</li>
     *     <li>Puntos Obtenidos por un Empate</li>
     *     <li>Puntos Obtenidos por una Victoria</li>
     * </ul>
     * @param ajustes - Objeto del tipo Control.Setting en el cual se sobreescribirán las preferencias que el usuario desee cambiar
     */
    public void puntuacion(Setting ajustes) {

        System.out.println("------------------------------------------------------------");
        System.out.println("Derrota: ");
        ajustes.setDerrota(leerNumero());
        System.out.println("Empate: ");
        ajustes.setEmpate(leerNumero());
        System.out.println("Victoria: ");
        ajustes.setVictoria(leerNumero());
    }

    public void fechas(Setting ajustes){

        System.out.println("------------------------------------------------------------");
        System.out.println("Indique el dia del mes en el que desea empezar la Liga: ");
        int dia= leerNumero();
        System.out.println("Indiqueme el mes deseado: ");
        String mes = leerEntrada();
        boolean comprobacionMeses = comprobar(dia,mes);
        if(comprobacionMeses){
            ajustes.setDiaInicio(dia);
            ajustes.setMesInicio(mes);
        }else{
            System.out.println("El dia no cuadra con el Mes introducido. Vuelva al Menú y añada la fecha de forma correcta ");
        }
    }

    public boolean comprobar(int dia, String mes){
        mes = mes.toUpperCase();

        if (mes.equals("ENERO") && 1<=dia && dia<=31){
            return true;
        }else if (mes.equals("FEBRERO") && 1<=dia && dia<=28){
            return true;
        }else if(mes.equals("MARZO") && 1<=dia && dia<=31){
            return true;
        } else if(mes.equals("ABRIL") && 1<=dia && dia<=30){
            return true;
        } else if(mes.equals("MAYO") && 1<=dia && dia<=31){
            return true;
        } else if(mes.equals("JUNIO") && 1<=dia && dia<=30){
            return true;
        } else if(mes.equals("JULIO") && 1<=dia && dia<=31){
            return true;
        } else if(mes.equals("AGOSTO") && 1<=dia && dia<=31){
            return true;
        } else if(mes.equals("SEPTIEMBRE") && 1<=dia && dia<=30){
            return true;
        } else if(mes.equals("OCTUBRE") && 1<=dia && dia<=31){
            return true;
        } else if(mes.equals("NOVIEMBRE") && 1<=dia && dia<=30){
            return true;
        } else if(mes.equals("DICIEMBRE") && 1<=dia && dia<=31){
            return true;
        }
        return false;
    }

    /**
     * Método que limpia la pantalla (Por medio de 150 saltos de linea)
     */
    public void limpiarPantalla() {
        for (int i = 0; i < 150; i++) {
            System.out.println();
        }
        System.out.println("Gracias por usarme");
    }

    /**
     * Método que lee un numero introducido por teclado y lo devuelve
     * @return Valor del numero introducido por teclado
     */
    public int leerNumero() {
        Scanner sc = new Scanner(System.in);
        int num;
        num = sc.nextInt();
        return num;
    }

    /**
     * Método que lee una serie de caracteres introducidos por teclado hasta que se pulse enter y lo devuelve
     * @return Valor de la cadena introducida por teclado
     */
    public String leerEntrada() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Método que muestra la leyenda (qué significa cada Abreviatura) de la Control.Clasificacion para que el usuario pueda leerlay entenderla
     */
    public void mostrarLeyenda(){
        String leyenda= "\n\n\nLEYENDA: \n"+"J: Jugados\n"+
                "G: Ganados \n"+
                "P: Perdidos \n"+
                "GF: Goles a favor \n"+
                "GC: Goles Contra \n"+
                "DG: Diferencia de Goles \n"+
                "Ptos: Puntos"+"\n"+"\n";
        ;
        System.out.println(leyenda);
    }
}
