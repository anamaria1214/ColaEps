package Cola;

public class Nodo<E> {
    private Nodo siguienteNodo;
    private E valorNodo;

    public E getValorNodo() {
        return valorNodo;
    }

    public void setValorNodo(E valorNodo) {
        this.valorNodo = valorNodo;
    }

    public Nodo<E> getSiguienteNodo() {

        return siguienteNodo;
    }

    public void setSiguienteNodo(Nodo siguienteNodo) {

        this.siguienteNodo = siguienteNodo;
    }

    public Nodo(E valorNodo) {
        this.valorNodo = valorNodo;
        siguienteNodo = null;
    }

    @Override
    public String toString() {
        return "Nodo{" +
                "valorNodo=" + valorNodo +
                '}';
    }
}

