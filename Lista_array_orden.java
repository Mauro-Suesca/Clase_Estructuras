public class Lista_array_orden<T extends Comparable<T>> implements Lista_array<T>{
    protected T[] datos;
    protected int posicion_actual;
    
    Lista_array_orden(){
        datos = null;
        posicion_actual = 0;
    }

    Lista_array_orden(Class<T> c, int initial_size){
        @SuppressWarnings("unchecked")
        final T[] datos = (T[]) new Comparable[initial_size];
        this.datos = datos;
        this.posicion_actual = 0;
    }

    /**
     * Añade un elemento a la lista en la posición dada
     * @param posicion La posición que va a ocupar el nuevo elemento.
     * @param element El elemento a añadir.
     */
    protected void add(int posicion, T element){
        if(!full()){
            move(posicion, true);
            datos[posicion] = element;
        }else{
            expandir_array(posicion, element);
        }
        posicion_actual++;
    }

    public void addAfter(int posicion, T element){
        add(posicion + 1, element);
    }

    public void addBefore(int posicion, T element){
        add(posicion, element);
    }

    public void addFirst(T element){
        add(0, element);
    }

    public void addLast(T element){
        add(posicion_actual, element);
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

    /**
     * Esta función crea un nuevo array con la longitud del doble de la actual, copia los elementos existentes e introduce un nuevo elemento en el array resultante
     * @param posicion La posición en la cual se va a ingresar el nuevo elemento.
     * @param element El nuevo elemento a ingresar.
     */
    private void expandir_array(int posicion, T element){
        @SuppressWarnings("unchecked")
        T[] nuevo_datos = (T[])new Comparable[datos.length*2];
        for(int i=0; i<posicion; i++){
            nuevo_datos[i] = datos[i];
        }
        nuevo_datos[posicion] = element;
        for(int i=posicion; i<posicion_actual; i++){
            nuevo_datos[i+1] = datos[i];
        }
        datos = nuevo_datos;
    }

    public int find(T valor){
        return search(valor, true);
    }

    public boolean full(){
        return posicion_actual >= datos.length;
    }

    public void insert(T element){
        addBefore(search(element, false), element);
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

    public void print(){
        System.out.print("Elementos en el arreglo: [");
        for(int i=0; i<posicion_actual-1; i++){
            System.out.print(datos[i] + " ");
        }
        System.out.println((!empty() ? datos[posicion_actual-1] : "") + "]");
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

    public void removeFirst() throws Invalid_size_operation{
        remove(0);
    }
    
    public void removeLast() throws Invalid_size_operation{
        remove(posicion_actual-1);
    }
    
    /**
     * Busca el elemento con el valor indicado.
     * @param valor El valor a buscar en la lista.
     * @param encontrar_exacto Si el método debe encontrar el valor en la lista o solo la posición donde debería insertarse.
     * @return El índice donde se encuentra el valor o -1 si no está en la lista (encontrar_exacto == true). El índice donde se encuentra el primer elemento mayor o igual al valor dado (encontrar_exacto = false).
     */
    public int search(T valor, boolean encontrar_exacto){
        int respuesta = -1, aux = 0;
        for(int i=0; i<posicion_actual; i++){
            aux = datos[i].compareTo(valor);
            if(aux >= 0){
                respuesta = aux == 0 ? i : respuesta;
                aux = i;
                break;
            }else{
                aux = i;
            }
        }
        
        if(!encontrar_exacto){
            respuesta = aux;
            if(aux == posicion_actual-1){
                if(datos[aux].compareTo(valor) < 0){
                    respuesta++;
                }
            }
        }
        return respuesta;
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
}
