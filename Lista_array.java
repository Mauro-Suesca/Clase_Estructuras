import java.lang.reflect.Array;

public class Lista_array<T> implements Lista_Array_Inter<T>{
    protected T[] datos;
    protected int posicion_actual;

    Lista_array(){
        datos = null;
    }

    Lista_array(Class<T> c, int initial_size){
        @SuppressWarnings("unchecked")
        final T[] datos = (T[]) Array.newInstance(c, initial_size);
        this.datos = datos;
    }

    public void addLast(T element) throws Invalid_size_operation{
        if(!full()){
            datos[posicion_actual] = element;
            posicion_actual++;
        }else{
            throw new Invalid_size_operation("Error: Lista llena. No se puede agregar el elemento.");
        }
    }

    public void print(){
        System.out.print("Elementos en el arreglo: [");
        for(int i=0; i<posicion_actual-1; i++){
            System.out.print(datos[i] + " ");
        }
        System.out.println((!empty() ? datos[posicion_actual-1] : "") + "]");
    }

    public void removeLast() throws Invalid_size_operation{
        if(!empty()){
            posicion_actual--;
        }else{
            throw new Invalid_size_operation("Error: Lista vacia. No se puede eliminar el elemento.");
        }
    }

    public void addFirst(T element) throws Invalid_size_operation{
        if(!full()){
            move(0, true);
            datos[0] = element;
            posicion_actual++;
        }else{
            throw new Invalid_size_operation("Error: Lista llena. No se puede agregar el elemento.");
        }
    }

    public void removeFirst() throws Invalid_size_operation{
        if(!empty()){
            move(0, false);
            posicion_actual--;
        }else{
            throw new Invalid_size_operation("Error: Lista vacia. No se puede eliminar el elemento.");
        }
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
            if(datos[i] == valor){
                respuesta = i;
                break;
            }
        }
        return respuesta;
    }

    public boolean erase(T valor) throws Invalid_size_operation{
        int posicion_eliminar = find(valor);
        if(posicion_eliminar != -1){
            move(posicion_eliminar, false);
            posicion_actual--;
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

    public void addBefore(int posicion, T valor) throws Invalid_size_operation{
        if(!full()){
            move(posicion, true);
            datos[posicion] = valor;
            posicion_actual++;
        }else{
            throw new Invalid_size_operation("Error: Lista llena. No se puede agregar el elemento.");
        }
    }

    public void addAfter(int posicion, T valor) throws Invalid_size_operation{
        if(!full()){
            move(posicion+1, true);
            datos[posicion+1] = valor;
            posicion_actual++;
        }else{
            throw new Invalid_size_operation("Error: Lista llena. No se puede agregar el elemento.");
        }
    }
    
    private void move(int start, boolean forward){
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