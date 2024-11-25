public class Queue_array<T> extends Lista_array_circulo<T> implements Queue<T>{
    Queue_array(){
        super();
    }

    @Override public void enqueue(T elemento) throws Invalid_size_operation{
        addLast(elemento);
    }

    @Override public T dequeue() throws Invalid_size_operation{
        T respuesta = topFront();
        removeFirst();
        return respuesta;
    }
}