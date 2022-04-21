package View;

import Control.Clasificacion;
import Model.Equipo;
import Model.Partido;

import java.util.Scanner;

public class PabloMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 

        Equipo equipo1 = new Equipo();
        Equipo equipo2 = new Equipo();
        int opcion = 1;
        int contador = 1;
        while(opcion==1){
            System.out.println("Introduce los datos de el partido " + contador);
            
            System.out.println("Goles de casa: ");       
            int GolesCasa = sc.nextInt();
            System.out.println("Goles de fuera: ");
            int GolesFuera = sc.nextInt();
            
            Partido partido = new Partido();
            partido.setEquipo1(equipo1);
            partido.setEquipo2(equipo2);
            partido = Control.Clasificacion.meterResultadoPartidos(partido, GolesCasa, GolesFuera);
            Clasificacion.actualizarDatosEquipo(partido);
            Clasificacion.imprimirClasificacion(partido);

            System.out.println("¿Quieres salir del programa o hacer otro partido?");
            System.out.println(" 1 = Sí, cualquier otro numero = No");
            opcion=sc.nextInt();
            
            contador++;
        }
        System.out.println("Adios");
        
    }
}
