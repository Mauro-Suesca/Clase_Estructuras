public interface Lista_nodo<T> extends Lista<T>{
    /**
     * @param valor El valor a encontrar en la lista.
     * @return El nodo de la primera ocurrencia del elemento, retorna null si el elemento no existe en la lista.
     */
    public Node<T> find(T element);

    /**
     * Añade un elemento a la lista antes de la posición indicada.
     * @param posicion El nodo que se encuentra en la posición referenciada.
     * @param element El elemento a añadir a la lista.
     */
    public void addBefore(Node<T> posicion, T element);

    /**
     * Añade un elemento a la lista después de la posición indicada.
     * @param posicion El nodo que se encuentra en la posición referenciada.
     * @param element El elemento a añadir a la lista.
     */
    public void addAfter(Node<T> posicion, T element);
}