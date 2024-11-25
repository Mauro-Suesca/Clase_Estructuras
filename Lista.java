public interface Lista<T>{
    public void addLast(T element) throws Invalid_size_operation;
    public T topBack();
    public void removeLast() throws Invalid_size_operation;
    public void addFirst(T element) throws Invalid_size_operation;
    public T topFront();
    public void removeFirst() throws Invalid_size_operation;
    public void print();
    public boolean erase(T valor) throws Invalid_size_operation;
    public boolean empty();
}