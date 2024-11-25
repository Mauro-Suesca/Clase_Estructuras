public interface Stack<T>{   
    public T pop() throws Invalid_size_operation;
    public void push(T elemento) throws Invalid_size_operation;
}