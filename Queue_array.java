public class Queue_array<T> extends Lista_array_circ<T> implements Queue<T>{
    Queue_array(){
        super();
    }

    Queue_array(Class<T> c, int initial_size){
        super(c, initial_size);
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