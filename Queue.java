public interface Queue<T>{
    public void enqueue(T elemento);
    public T dequeue();
}