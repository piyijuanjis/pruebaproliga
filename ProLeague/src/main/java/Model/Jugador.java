package Model;

import Model.Equipo;

public class Jugador {
    String nombre;
    String apellidos;
    String posicion;
    Equipo equipo;
    int dorsal;
    int edad;

    
    public Jugador() {
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public int getDorsal() {
        return dorsal;
    }
    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }
    public String getPosicion() {
        return posicion;
    }
    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
    public Equipo getEquipo() {
        return equipo;
    }
    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Método que muestra las características del jugador
     * @return Un string con
     * <ul>
     *     <li>Nombre del Model.Equipo al que pertenece un Model.Jugador</li>
     *     <li>Numero de Dorsal asociado a un Model.Jugador</li>
     *     <li>Rol de un Model.Jugador</li>
     * </ul>
     */
    @Override
    public String toString() {
        return "Model.Equipo: "+getEquipo().getNombre()+"\n"+
                "Nombre Model.Jugador: "+getNombre()+" "+getApellidos()+"\n"+
                "Dorsal: "+getDorsal()+" Posicion: "+getPosicion()+"\n";

    }
}
