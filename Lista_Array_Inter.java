public interface Lista_Array_Inter<T> extends Lista<T>{
    /**
     * @param valor el valor a encontrar en la lista
     * @return El índice de la primera ocurrencia del elemento, retorna -1 si el elemento no existe en la lista
     */
    public int find(T valor);
    public void addBefore(int posicion, T valor) throws Invalid_size_operation;
    public void addAfter(int posicion, T valor) throws Invalid_size_operation;
}