package Model;

/**
 * @author Erkus00
 * @version 1.0
 *
 * Clase Model.Jornada
 * En ella, se crean todas las jornadas que tendrá la Liga. Cada una de estas, estaría formada por todos los partidos de ese dia (2 Equipos y 1 Model.Arbitro cada uno).
 * Encontramos métodos setters y getters y un ToString para mostrar la jornada
 *
 * Sus atributos son:
 * <ul>
 *     <li>partidos - Lista de enfrentamientos que habrá en una jornada {@link Partido[]}</li>
 * </ul>
 */

public class Jornada {
    //    Atributos
    private Partido[] partidos;
    private String fecha;

//    Getters y Setters
    /**
     * Método que devuelve la lista de partidos de una jornada
     * @return lista de partidos de una jornada
     */
    public Partido[] getPartidos() {
        return partidos;
    }

    /**
     * Método que establece los partidos de una jornada
     * @param partidos - Lista de Partidos
     */
    public void setPartidos(Partido[] partidos) {
        this.partidos = partidos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    //    ToString
    /**
     * Método que muestra las caracteristicas de una jornada
     * @return Un string con los partidos que se jugarán en una jornada
     */
    @Override
    public String toString() {
        String cadena="Fecha de la Model.Jornada: "+getFecha()+"\n";
        for (int i=0;i<this.partidos.length;i++) {
            cadena+=partidos[i];
        }
        return cadena;
    }

}