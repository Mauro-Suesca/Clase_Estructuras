public interface Lista<T>{
    public void addLast(T element);
    public T topBack();
    public void removeLast();
    public void addFirst(T element);
    public T topFront();
    public void removeFirst();
    public void print();
    public boolean erase(T valor);
    public boolean empty();
}