import java.lang.reflect.Array;

public class Lista_array<T> implements Lista_Array_Inter<T>{
    T[] datos;
    int posicion_actual;

    Lista_array(Class<T> c, int initial_size){
        @SuppressWarnings("unchecked")
        final T[] datos = (T[]) Array.newInstance(c, initial_size);
        this.datos = datos;
    }

    public void addLast(T element){
        if(posicion_actual != datos.length){
            datos[posicion_actual] = element;
            posicion_actual++;
        }else{
            System.out.println("Error: Lista llena. No se puede agregar el elemento.");
        }
    }

    public void print(){
        System.out.print("Elementos en el arreglo: [");
        for(int i=0; i<posicion_actual-1; i++){
            System.out.print(datos[i] + " ");
        }
        System.out.println((posicion_actual > 0 ? datos[posicion_actual-1] : "") + "]");
    }

    public void removeLast(){
        if(posicion_actual > 0){
            posicion_actual--;
        }else{
            System.out.println("Error: Lista vacia. No se puede eliminar el elemento.");
        }
    }

    public void addFirst(T element){
        if(posicion_actual < datos.length){
            move(0, true);
            datos[0] = element;
            posicion_actual++;
        }else{
            System.out.println("Error: Lista llena. No se puede agregar el elemento.");
        }
    }

    public void removeFirst(){
        if(posicion_actual > 0){
            move(0, false);
            posicion_actual--;
        }else{
            System.out.println("Error: Lista vacia. No se puede eliminar el elemento.");
        }
    }

    public T topBack(){
        if(posicion_actual > 0){
            return datos[posicion_actual-1];
        }else{
            return null;
        }
    }

    public T topFront(){
        if(posicion_actual > 0){
            return datos[0];
        }else{
            return null;
        }
    }

    public int find(T valor){ //Retorna el índice de la primera ocurrencia del elemento, retorna -1 si el elemento no existe en la lista
        int respuesta = -1;
        for(int i=0; i<posicion_actual; i++){
            if(datos[i] == valor){
                respuesta = i;
                break;
            }
        }
        return respuesta;
    }

    public boolean erase(T valor){
        int posicion_eliminar = find(valor);
        if(posicion_eliminar != -1){
            move(posicion_eliminar, false);
            posicion_actual--;
            return true;
        }else{
            return false;
        }
    }

    public boolean empty(){
        return posicion_actual == 0;
    }

    public void addBefore(int posicion, T valor){
        if(posicion_actual != datos.length){
            move(posicion, true);
            datos[posicion] = valor;
            posicion_actual++;
        }else{
            System.out.println("Error: Lista llena. No se puede agregar el elemento.");
        }
    }

    public void addAfter(int posicion, T valor){
        if(posicion_actual != datos.length){
            move(posicion+1, true);
            datos[posicion+1] = valor;
            posicion_actual++;
        }else{
            System.out.println("Error: Lista llena. No se puede agregar el elemento.");
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