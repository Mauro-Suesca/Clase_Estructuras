public class Queue_nodo<T> extends Lista_enlazada_cola<T> implements Queue<T>{
    Queue_nodo(){
        super();
    }

    @Override public void enqueue(T elemento){
        addLast(elemento);
    }

    @Override public T dequeue() throws Invalid_size_operation{
        T respuesta = topFront();
        removeFirst();
        return respuesta;
    }
}