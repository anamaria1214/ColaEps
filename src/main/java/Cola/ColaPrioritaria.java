package Cola;

import lombok.Getter;

import java.util.Comparator;
import java.util.PriorityQueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ColaPrioritaria<T extends Comparable<T>> {
    @Getter
    private Nodo<T> nodoPrimero;
    @Getter
    private int tamano;
    private Nodo<T> nodoUltimo;

    public ColaPrioritaria(){
        this.nodoPrimero=null;
        this.nodoUltimo=null;
        this.tamano=0;
    }

    public void encolar(T dato) {

        Nodo<T> nodo = new Nodo<>(dato);

        if(estaVacia()) {
            nodoPrimero = nodoUltimo = nodo;
        }else {

            if( dato.compareTo(nodoPrimero.getValorNodo())>0 ) {
                nodo.setSiguienteNodo(nodoPrimero);
                nodoPrimero = nodo;
            }else if( dato.compareTo(nodoUltimo.getValorNodo()) <= 0 ) {
                nodoUltimo.setSiguienteNodo(nodo);
                nodoUltimo = nodo;
            }else {
                Nodo<T> aux = nodoPrimero.getSiguienteNodo();
                Nodo<T> ant = nodoPrimero;

                while(aux!=null) {

                    System.out.println(aux);
                    System.out.println(ant);
                    System.out.println(aux.getValorNodo().compareTo(dato)<0);

                    if(aux.getValorNodo().compareTo(dato)<0) {
                        break;
                    }
                    ant = aux;
                    aux = aux.getSiguienteNodo();
                }

                nodo.setSiguienteNodo(aux);
                ant.setSiguienteNodo(nodo);
            }

        }

        tamano++;
    }


    public boolean estaVacia(){
        return nodoPrimero==null;
    }

    public ColaPrioritaria<T> clonarCola(){
        int size=tamano;
        ColaPrioritaria<T> clonCola= new ColaPrioritaria<>();
        Nodo<T> nodoAux=nodoPrimero;
        for(int i=0;i<tamano;i++){
            nodoAux= nodoAux.getSiguienteNodo();
            clonCola.encolar(nodoAux.getValorNodo());
        }
        return clonCola;
    }

    public T desencolar() {
        if(!estaVacia()) {
            Nodo<T> n = nodoPrimero;
            T valor= n.getValorNodo();
            nodoPrimero = n.getSiguienteNodo();

            if(nodoPrimero==null) {
                nodoUltimo = null;
            }
            tamano--;
            return valor;
        }
        throw new RuntimeException("Cola vacia");
    }
    public T obtener(int index){
        Nodo<T> nodoPosicion = nodoPrimero;
        T valor=null;
        if(!estaVacia()){
            for (int i=0; i<tamano;i++){
                if(i==index){
                    valor= nodoPosicion.getValorNodo();
                }
                nodoPosicion= nodoPosicion.getSiguienteNodo();
            }
        }
        return valor;
        }


    public void imprimir(){
        Nodo<T> nodoPosicion = nodoPrimero;
        if(!estaVacia()){
            while(nodoPosicion!=null) {
                System.out.println( nodoPosicion.getValorNodo() );
                nodoPosicion = nodoPosicion.getSiguienteNodo();
            }
        }
    }

}






