package Modelo;

import Cola.ColaPrioritaria;
import lombok.Getter;

import java.util.Comparator;

public class CentroAsistenciaMedica {

    @Getter
    public ColaPrioritaria<Persona> colaNormalPrioritaria;
    @Getter
    public ColaPrioritaria<Persona> colaPremium;
    private static CentroAsistenciaMedica eps;

    public static CentroAsistenciaMedica getInstance(){
        if(eps == null){
            eps = new CentroAsistenciaMedica();
        }

        return eps;
    }
    public CentroAsistenciaMedica() {
        colaNormalPrioritaria = new ColaPrioritaria<>();
        colaPremium = new ColaPrioritaria<>();
    }

    public void encolarPersona(Persona persona) {
        if (persona.getPrioridad() == Prioridad.PREMIUM) {
            colaPremium.encolar(persona);
            System.out.println(persona.getNombre() + " ha sido encolado en la cola premium.");
        } else {
            colaNormalPrioritaria.encolar(persona);
            System.out.println(persona.getNombre() + " ha sido encolado en la cola normal.");
        }
    }

    public void desencolarPersona(ColaPrioritaria<Persona> cola) throws InterruptedException {
        Thread.sleep(5000);
        Persona persona;

        if (!cola.estaVacia()) {
            persona = cola.desencolar();
            System.out.println("Atendiendo a " + persona.getNombre() + " de cola.");
            Thread.sleep(2000);
            System.out.println("Atención completada para " + persona.getNombre());
        } else {
            System.out.println("No hay personas en espera en la cola.");
        }
    }
}