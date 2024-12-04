public interface Lista_array<T> extends Lista<T>{
    /**
     * @param valor El valor a encontrar en la lista.
     * @return El índice de la primera ocurrencia del elemento, retorna -1 si el elemento no existe en la lista.
     */
    public int find(T valor);

    /**
     * Añade un elemento a la lista antes de la posición indicada.
     * @param posicion 
     * @param element El elemento a añadir a la lista.
     * @throws Invalid_size_operation Si la implementación de lista tiene un tamaño máximo, arroja este error cuando se intenta añadir un nuevo elemento a una lista llena.
     */
    public void addBefore(int posicion, T element) throws Invalid_size_operation;

    /**
     * Añade un elemento a la lista después de la posición indicada.
     * @param posicion 
     * @param element El elemento a añadir a la lista.
     * @throws Invalid_size_operation Si la implementación de lista tiene un tamaño máximo, arroja este error cuando se intenta añadir un nuevo elemento a una lista llena.
     */
    public void addAfter(int posicion, T element) throws Invalid_size_operation;

    /**
     * @return Indica si la lista se encuentra actualmente llena.
     */
    public boolean full();
}