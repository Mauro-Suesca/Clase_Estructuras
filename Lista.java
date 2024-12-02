public interface Lista<T>{
    /**
     * Añade un elemento al final de la lista.
     * @param element El elemento a añadir.
     * @throws Invalid_size_operation Si la implementación de lista tiene un tamaño máximo, arroja este error cuando se intenta añadir un nuevo elemento a una lista llena.
     */
    public void addLast(T element) throws Invalid_size_operation;

    /**
     * @return El elemento que se encuentra en la última posición de la lista.
     */
    public T topBack();

    /**
     * Quita un elemento del final de la lista.
     * @throws Invalid_size_operation Si se intenta eliminar un elemento de una lista vacía.
     */
    public void removeLast() throws Invalid_size_operation;

    /**
     * Añade un elemento al inicio de la lista.
     * @param element El elemento a añadir.
     * @throws Invalid_size_operation Si la implementación de lista tiene un tamaño máximo, arroja este error cuando se intenta añadir un nuevo elemento a una lista llena.
     */
    public void addFirst(T element) throws Invalid_size_operation;

    /**
     * @return El elemento que se encuentra en la primera posición de la lista.
     */
    public T topFront();

    /**
     * Quita un elemento del final de la lista.
     * @throws Invalid_size_operation Si se intenta eliminar un elemento de una lista vacía.
     */
    public void removeFirst() throws Invalid_size_operation;

    /**
     * Muestra la lista con sus elementos en su totalidad.
     */
    public void print();

    /**
     * Elimina el primer elemento que tiene el valor indicado.
     * @param valor El valor a buscar para eliminar.
     * @return Si el elemento se encontró en la lista y se pudo eliminar.
     * @throws Invalid_size_operation Si se intenta eliminar un elemento de una lista vacía.
     */
    public boolean erase(T valor) throws Invalid_size_operation;

    /**
     * @return Indica si la lista está vacía.
     */
    public boolean empty();
}