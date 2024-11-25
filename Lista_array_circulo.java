import java.lang.reflect.Array;

public class Lista_array_circulo<T> implements Lista_Array_Inter<T>{
    protected T[] datos;
    protected int size;
    protected int front;
    protected int back;

    Lista_array_circulo(){
        datos = null;
        size = front = back = 0;
    }

    Lista_array_circulo(Class<T> c, int initial_size){
        this();
        @SuppressWarnings("unchecked")
        final T[] datos = (T[]) Array.newInstance(c, initial_size);
        this.datos = datos;
    }

    public void addLast(T element){
        if(!full()){
            if(back >= datos.length){
                back = 0;
            }
            datos[back++] = element;
            size++;
        }else{
            //TODO Excepción
        }
    }

    public void print(){
    
    }

    public void removeLast(){
        
    }

    public void addFirst(T element){

    }

    public void removeFirst(){
    
    }

    public T topBack(){
        if(!empty()){
            return datos[back-1];
        }else{
            return null;
        }
    }

    public T topFront(){
        if(!empty()){
            return datos[front];
        }else{
            return null;
        }
    }

    public int find(T valor){

    }

    public boolean erase(T valor){
    
    }

    public boolean empty(){
        return size <= 0;
    }

    public boolean full(){
        return size == datos.length;
    }

    public void addBefore(int posicion, T valor){

    }

    public void addAfter(int posicion, T valor){

    }
}