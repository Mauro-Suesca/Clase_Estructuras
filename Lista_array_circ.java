import java.lang.reflect.Array;

public class Lista_array_circ<T> implements Lista_array<T>{
    protected T[] datos;
    protected int size;
    protected int front;
    protected int back;

    Lista_array_circ(){
        datos = null;
        size = front = back = 0;
    }

    Lista_array_circ(Class<T> c, int initial_size){
        this();
        @SuppressWarnings("unchecked")
        final T[] datos = (T[]) Array.newInstance(c, initial_size);
        this.datos = datos;
    }

    /**
     * Añade un elemento al final de la lista sin revisar si está llena o no.
     * @param element El elemento en cuestión.
     */
    protected void addLast_no_check(T element){
        datos[back] = element;
        size++;
        back = change_position(back, true);
    }

    public void addLast(T element) throws Invalid_size_operation{
        if(!full()){
            addLast_no_check(element);
        }else{
            throw new Invalid_size_operation("Error: Lista llena. No se puede agregar el elemento.");
        }
    }

    public void print(){
        int posicion = front;
        System.out.print("Elementos en el arreglo: [");
        if(full()){ //Si está lleno y solo se deja el while, no se imprimirá ningún elemento
            System.out.print(datos[front] + (back != change_position(front, true) ? " " : ""));
            posicion = change_position(posicion, true);
        }
        int siguiente;
        while(posicion != back){
            siguiente = change_position(posicion, true);
            System.out.print(datos[posicion] + (back != siguiente ? " " : ""));
            posicion = siguiente;
        }
        System.out.println("]");
    }

    public void removeLast() throws Invalid_size_operation{
        if(!empty()){
            back = change_position(back, false);
            size--;
        }else{
            throw new Invalid_size_operation("Error: Lista vacia. No se puede eliminar el elemento.");
        }
    }

    /**
     * Añade un elemento al inicio de la lista sin revisar si está llena o no.
     * @param element El elemento en cuestión.
     */
    protected void addFirst_no_check(T element){
        front = change_position(front, false);
        datos[front] = element;
        size++;
    }

    public void addFirst(T element) throws Invalid_size_operation{
        if(!full()){
            addFirst_no_check(element);
        }else{
            throw new Invalid_size_operation("Error: Lista llena. No se puede agregar el elemento.");
        }
    }

    public void removeFirst() throws Invalid_size_operation{
        if(!empty()){
            front = change_position(front, true);
            size--;
        }else{
            throw new Invalid_size_operation("Error: Lista vacia. No se puede eliminar el elemento.");
        }
    }

    public T topBack(){
        if(!empty()){
            return datos[change_position(back, false)];
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
            posicion = change_position(posicion, true);
        }
        while(posicion != back){
            if(datos[posicion].equals(valor)){
                respuesta = posicion;
                break;
            }
            posicion = change_position(posicion, true);
        }
        return respuesta;
    }

    public boolean erase(T valor) throws Invalid_size_operation{
        int posicion = find(valor);
        if(posicion != -1){
            if(posicion == front){
                removeFirst();
            }else if(posicion == change_position(back, false)){
                removeLast();
            }else{
                int menor_distancia = mas_cercano(posicion);
                if(menor_distancia == -1){
                    move(false, posicion, change_position(back, false),true);
                }else{
                    move(true, front, posicion, true);
                }
                size--;
            }
            return true;
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

    public int size(){
        return size;
    }

    /**
     * Añade un elemento antes de la posición indicada de la lista sin revisar si está llena o no.
     * @param posicion
     * @param element El elemento en cuestión.
     */
    protected void addBefore_no_check(int posicion, T element){
        int menor_distancia = mas_cercano(posicion);
        if(menor_distancia == 1 || menor_distancia == 0){
            int anterior = change_position(posicion, false);
            move(false, change_position(front, false), anterior, false);
            datos[anterior] = element;
        }else{
            move(true, posicion, back, false);
            datos[posicion] = element;
        }
        size++;
    }

    public void addBefore(int posicion, T element) throws Invalid_size_operation{
        if(!full()){
            addBefore_no_check(posicion, element);
        }else{
            throw new Invalid_size_operation("Error: Lista llena. No se puede agregar el elemento.");
        }
    }

    /**
     * Añade un elemento después de la posición indicada de la lista sin revisar si está llena o no.
     * @param posicion
     * @param element El elemento en cuestión.
     */
    protected void addAfter_no_check(int posicion, T element){
        int menor_distancia = mas_cercano(posicion);
        if(menor_distancia == -1 || menor_distancia == 0){
            int siguiente = change_position(posicion, true);
            move(true, siguiente, back, false);
            datos[siguiente] = element;
        }else{
            move(false, change_position(front, false), posicion, false);
            datos[posicion] = element;
        }
        size++;
    }

    public void addAfter(int posicion, T element) throws Invalid_size_operation{
        if(!full()){
            addAfter_no_check(posicion, element);
        }else{
            throw new Invalid_size_operation("Error: Lista llena. No se puede agregar el elemento.");
        }
    }

    /**
     * Da la posición anterior o siguiente del índice dado.
     * @param valor_actual El índice cuya posición anterior o siguiente se quiere saber.
     * @param next Indica si se quiere saber la posición siguiente.
     * @return El índice en el que se encuentra la posición deseada.
     */
    protected int change_position(int valor_actual, boolean next){
        if(next){
            valor_actual = ++valor_actual % datos.length;
        }else{
            valor_actual = datos.length-1 - ((datos.length - valor_actual) % datos.length);
        }
        return valor_actual;
    }

    /**
     * Este método permite determinar cuál de los extremos está más 'cerca' a la posición dada
     * @param posicion la posición que se quiere comparar
     * @return 1 si front está más cerca, 0 si están a distancias iguales, -1 si back está más cerca
     */
    protected int mas_cercano(int posicion){
        int back_aux = change_position(back, false);
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

    /**
     * Mueve los elementos del arreglo en una dirección dependiendo de la posición (para eliminar o añadir elementos en el medio). No hace nada cuando el array está lleno
     * @param forward Indica si se deben mover los elementos hacia adelante o atrás
     * @param inicio La posición donde se encuentra (o la posición anterior a, con forward == false) el primer elemento a mover
     * @param fin La posición donde se encuentra (o la posición posterior a, con forward == true) el último elemento a mover
     * @param delete Indica si se está moviendo para eliminar un elemento o no
     */
    protected void move(boolean forward, int inicio, int fin, boolean delete){
        if(!full()){
            int posicion;
            if(forward){
                int anterior;
                posicion = fin;
                while(posicion != inicio){
                    anterior = change_position(posicion, false);
                    datos[posicion] = datos[anterior];
                    posicion = anterior;
                }
            }else{
                int siguiente;
                posicion = inicio;
                while(posicion != fin){
                    siguiente = change_position(posicion, true);
                    datos[posicion] = datos[siguiente];
                    posicion = siguiente;
                }
            }

            delete = forward ? delete : !delete; //delete indica si es front o back que se modifica, si se mueve hacia el frente, se da la lógica del siguiente if, si se mueve hacia atrás, se invierta la lógica, por eso se niega delete

            if(delete){
                front = change_position(front, forward); //Ambos valores siempre se moverán en la dirección en la que se movieron los elementos, por eso se deja forward como tal
            }else{
                back = change_position(back, forward);
            }
        }
    }
}