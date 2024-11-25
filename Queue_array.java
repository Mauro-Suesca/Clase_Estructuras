public class Queue_array<T> extends Lista_array_circulo<T> implements Queue<T>{
    Queue_array(){
        super();
    }

    @Override public void enqueue(T elemento){
        addLast(elemento);
    }

    @Override public T dequeue(){
        T respuesta = topFront();
        removeFirst();
        return respuesta;
    }
}