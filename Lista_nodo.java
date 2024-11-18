public interface Lista_nodo<T> extends Lista<T>{
    public Node<T> find(T elemento);
    public void addBefore(Node<T> posicion, T valor);
    public void addAfter(Node<T> posicion, T valor);
}