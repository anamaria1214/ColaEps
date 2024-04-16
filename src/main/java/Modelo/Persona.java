package Modelo;

import lombok.ToString;

@ToString
public class Persona implements Comparable<Persona> {
    private String nombre;
    private Prioridad prioridad;

    public Persona(String nombre, Prioridad prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
    }
    public Persona() {

    }

    public String getNombre() {
        return nombre;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public int compareTo(Persona otraPersona) {
        return this.prioridad.compareTo(otraPersona.getPrioridad());
    }
}
