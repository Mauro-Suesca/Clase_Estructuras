public interface Queue<T>{
    public void enqueue(T elemento) throws Invalid_size_operation;
    public T dequeue() throws Invalid_size_operation;
}