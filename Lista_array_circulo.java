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

    public void addLast(T element) throws Invalid_size_operation{
        if(!full()){
            datos[back] = element;
            size++;
            back = move(back, true);
        }else{
            throw new Invalid_size_operation("Error: Lista llena. No se puede agregar el elemento.");
        }
    }

    public void print(){
        int posicion = front;
        System.out.print("Elementos en el arreglo: [");
        if(full()){ //Si está lleno y solo se deja el while, no se imprimirá ningún elemento
            System.out.print(datos[front] + " ");
            posicion = move(posicion, true);
        }
        while(posicion != back){
            System.out.print(datos[posicion] + " ");
            posicion = move(posicion, true);
        }
        System.out.println("]");
    }

    public void removeLast() throws Invalid_size_operation{
        if(!empty()){
            back = move(back, false);
            size--;
        }else{
            throw new Invalid_size_operation("Error: Lista vacia. No se puede eliminar el elemento.");
        }
    }

    public void addFirst(T element) throws Invalid_size_operation{
        if(!full()){
            front = move(front, false);
            datos[front] = element;
            size++;
        }else{
            throw new Invalid_size_operation("Error: Lista llena. No se puede agregar el elemento.");
        }
    }

    public void removeFirst() throws Invalid_size_operation{
        if(!empty()){
            front = move(front, true);
            size--;
        }else{
            throw new Invalid_size_operation("Error: Lista vacia. No se puede eliminar el elemento.");
        }
    }

    public T topBack(){
        if(!empty()){
            return datos[move(back, false)];
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
        int posicion = front, respuesta = -1;
        if(full()){ //Si está lleno y solo se deja el while, no se revisará ningún elemento
            if(datos[posicion].equals(valor)){
                return posicion;
            }
            posicion = move(posicion, true);
        }
        while(posicion != back){
            if(datos[posicion].equals(valor)){
                respuesta = posicion;
                break;
            }
            posicion = move(posicion, true);
        }
        return respuesta;
    }

    public boolean erase(T valor) throws Invalid_size_operation{//TODO función erase
        int posicion = find(valor);
        if(posicion != -1){
            int menor_distancia = mas_cercano(posicion);
            if(menor_distancia == 1){

            }else if(menor_distancia == -1){

            }else{

            }
        }else if(empty()){
            throw new Invalid_size_operation("Error: Lista vacia. No se puede eliminar el elemento.");
        }else{
            return false;
        }
    }

    public boolean empty(){
        return size <= 0;
    }

    public boolean full(){
        return size == datos.length;
    }

    public void addBefore(int posicion, T valor){//TODO función addBefore

    }

    public void addAfter(int posicion, T valor){//TODO función addAfter

    }

    private int move(int valor_actual, boolean forward){
        if(forward){
            valor_actual++;
            if(valor_actual >= datos.length){
                valor_actual = 0;
            }
        }else{
            valor_actual--;
            if(valor_actual < 0){
                valor_actual = datos.length-1;
            }
        }
        return valor_actual;
    }

    /**
     * Este método permite determinar cuál de los extremos está más 'cerca' a la posición dada
     * @param posicion la posición que se quiere comparar
     * @return 1 si front está más cerca, 0 si están a distancias iguales, -1 si back está más cerca
     */
    private int mas_cercano(int posicion){
        int back_aux = move(back, false);
        if(posicion == front || posicion == back_aux){
            if(posicion == front && posicion == back_aux){
                return 0;
            }else{
                return posicion == front ? 1 : -1;
            }
        }else{
            int distancia_a_front;
            int distancia_a_back;

            if(posicion > front){
                distancia_a_front = Math.abs(posicion-front);
            }else{
                distancia_a_front = posicion;
                distancia_a_front += datos.length-1 - front;
            }
            if(posicion < back){
                distancia_a_back = Math.abs(posicion-back_aux);
            }else{
                distancia_a_back = datos.length-1 - posicion;
                distancia_a_back += back;
            }

            if(distancia_a_front < distancia_a_back){
                return 1;
            }else if(distancia_a_front == distancia_a_back){
                return 0;
            }else{
                return -1;
            }
        }  
    }
}