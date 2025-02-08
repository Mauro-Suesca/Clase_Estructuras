public interface Heap<T>{
    /**
     * Elimina el elemento en la posición dada
     * @param i La posición del elemento a eliminar
     * @return El elemento que se eliminó
     * @throws Invalid_size_operation Si se trata de eliminar de un heap vacío
     * @throws IndexOutOfBoundsException Si se trata de acceder a un elemento fuera de los límites del heap
     */
    public T erase(int i) throws Invalid_size_operation, IndexOutOfBoundsException;

    /**
     * Elimina y obtiene el mayor elemento del heap
     * @return El mayor elemento del heap
     * @throws Invalid_size_operation Si se trata de eliminar de un heap vacío
     */
    public T extract_max() throws Invalid_size_operation;

    /**
     * @return El mayor elemento del heap
     */
    public T get_max();

    /**
     * Organiza en orden descendente los elementos del array dado
     * @param array El array a ordenar
     * @return El array con sus elementos ordenados de manera descendente
     */
    public T[] heap_sort(T[] array);

    /**
     * Ingresa al elemento dado en el heap
     * @param element El elemento a insertar
     */
    public void insert(T element);

    /**
     * Obtiene la posición del hijo izquierdo del elemento que se encuentra en la posición dada
     * @param i La posición del elemento cuyo hijo se quiere obtener
     * @return La posición del hijo izquierdo del elemento en 'i'
     */
    default int left_child(int i){
        return i*2+1;
    }

    /**
     * Obtiene la posición del padre del elemento que se encuentra en la posición dada
     * @param i La posición del elemento cuyo padre se quiere obtener
     * @return La posición del padre del elemento en 'i'
     */
    default int parent(int i){
        return (i-1)/2;
    }

    /**
     * Obtiene la posición del hijo derecho del elemento que se encuentra en la posición dada
     * @param i La posición del elemento cuyo hijo se quiere obtener
     * @return La posición del hijo derecho del elemento en 'i'
     */
    default int right_child(int i){
        return left_child(i)+1;
    }
}