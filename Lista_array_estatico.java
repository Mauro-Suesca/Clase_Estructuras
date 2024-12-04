import java.lang.reflect.Array;

public class Lista_array_estatico<T> implements Lista_array<T>{
    protected T[] datos;
    protected int posicion_actual;

    Lista_array_estatico(){
        datos = null;
        posicion_actual = 0;
    }

    Lista_array_estatico(Class<T> c, int initial_size){
        @SuppressWarnings("unchecked")
        final T[] datos = (T[]) Array.newInstance(c, initial_size);
        this.datos = datos;
        this.posicion_actual = 0;
    }

    public void addLast(T element) throws Invalid_size_operation{
        add(posicion_actual, element);
    }

    public void print(){
        System.out.print("Elementos en el arreglo: [");
        for(int i=0; i<posicion_actual-1; i++){
            System.out.print(datos[i] + " ");
        }
        System.out.println((!empty() ? datos[posicion_actual-1] : "") + "]");
    }

    public void removeLast() throws Invalid_size_operation{
        remove(posicion_actual-1);
    }

    public void addFirst(T element) throws Invalid_size_operation{
        add(0, element);
    }

    public void removeFirst() throws Invalid_size_operation{
        remove(0);
    }

    public T topBack(){
        if(!empty()){
            return datos[posicion_actual-1];
        }else{
            return null;
        }
    }

    public T topFront(){
        if(!empty()){
            return datos[0];
        }else{
            return null;
        }
    }

    public int find(T valor){
        int respuesta = -1;
        for(int i=0; i<posicion_actual; i++){
            if(datos[i].equals(valor)){
                respuesta = i;
                break;
            }
        }
        return respuesta;
    }

    public boolean erase(T valor) throws Invalid_size_operation{
        int posicion_eliminar = find(valor);
        if(posicion_eliminar != -1){
            remove(posicion_eliminar);
            return true;
        }else if(empty()){
            throw new Invalid_size_operation("Error: Lista vacia. No se puede eliminar el elemento.");
        }else{
            return false;
        }
    }

    public boolean empty(){
        return posicion_actual <= 0;
    }

    public boolean full(){
        return posicion_actual >= datos.length;
    }

    /**
     * Añade un elemento a la lista en la posición dada
     * @param posicion La posición que va a ocupar el nuevo elemento.
     * @param element El elemento a añadir.
     * @throws Invalid_size_operation Cuando se intenta añadir un nuevo elemento a una lista llena.
     */
    protected void add(int posicion, T element) throws Invalid_size_operation{
        if(!full()){
            move(posicion, true);
            datos[posicion] = element;
            posicion_actual++;
        }else{
            throw new Invalid_size_operation("Error: Lista llena. No se puede agregar el elemento.");
        }
    }

    /**
     * Quita el elemento de la posición especificada de la lista.
     * @param posicion La posición en la que se encuentra el elemento dentro de la lista.
     * @throws Invalid_size_operation Si se intenta eliminar un elemento de una lista vacía.
     */
    protected void remove(int posicion) throws Invalid_size_operation{
        if(!empty()){
            move(posicion, false);
            posicion_actual--;
        }else{
            throw new Invalid_size_operation("Error: Lista vacia. No se puede eliminar el elemento.");
        }
    }

    public void addBefore(int posicion, T element) throws Invalid_size_operation{
        add(posicion, element);
    }

    public void addAfter(int posicion, T element) throws Invalid_size_operation{
        add(posicion + 1, element);
    }
    
    /**
     * Esta función mueve los elementos del array desde una posición indicada (no se debe llamar si no hay espacio donde mover los elementos)
     * @param start Es la posición desde la cual se moverán los elementos. Si forward == true, se copia el valor en start y se mueve a start+1. Si forward == false, el valor en start se borra y se reemplaza con el valor en start+1
     * @param forward Indica si los elementos se tienen que mover hacia adelante.
     */
    protected void move(int start, boolean forward){
        if(forward){
            for(int i=posicion_actual; i>start; i--){
                datos[i] = datos[i-1];
            }
        }else{
            for(int i=start; i<posicion_actual-1; i++){
                datos[i] = datos[i+1];
            }
        }
    }
}