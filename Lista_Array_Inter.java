public interface Lista_Array_Inter<T> extends Lista<T>{
    public int find(T elemento);
    public void addBefore(int posicion, T valor);
    public void addAfter(int posicion, T valor);
}