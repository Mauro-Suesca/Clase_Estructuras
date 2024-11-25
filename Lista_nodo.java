public interface Lista_nodo<T> extends Lista<T>{
    /**
     * @param valor el valor a encontrar en la lista
     * @return El nodo de la primera ocurrencia del elemento, retorna null si el elemento no existe en la lista
     */
    public Node<T> find(T valor);
    public void addBefore(Node<T> posicion, T valor);
    public void addAfter(Node<T> posicion, T valor);
}