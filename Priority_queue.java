import java.lang.reflect.Array;

public class Priority_queue<T> extends Lista_array_dinamico<Priority_queue<T>.Priority_node> implements Heap<T>{
    protected class Priority_node{
        T elemento;
        int prioridad;

        Priority_node(T elemento, int prioridad){
            this.elemento = elemento;
            this.prioridad = prioridad;
        }

        @Override public String toString(){
            return elemento.toString();
        }
    }

    Priority_queue(){
        super();
    }

    Priority_queue(int initial_size){
        @SuppressWarnings("unchecked")
        final Priority_node[] datos = (Priority_node[]) Array.newInstance(Priority_node.class, initial_size);
        this.datos = datos;
        this.posicion_actual = 0;
    }

    Priority_queue(T[] array, int[] prioridades){
        this(array.length);
        for(int i=0; i<array.length; i++){
            this.datos[i] = new Priority_node(array[i], prioridades[i]);
        }
        
        this.posicion_actual = array.length;
        int i = posicion_actual/2 - 1;
        while(i >= 0){
            sift_down(i--);
        }
    }

    /**
     * Cambia la prioridad del elemento en la posición indicada
     * @param i La posición del elemento cuya prioridad se quiere cambiar
     * @param nueva_prioridad La nueva prioridad a asignar al elemento
     * @throws IndexOutOfBoundsException Si i no se encuentra dentro de los límites de la queue
     */
    public void change_priority(int i, int nueva_prioridad) throws IndexOutOfBoundsException{
        if(i >= 0 && i < posicion_actual){
            boolean subir;

            subir = datos[i].prioridad <= nueva_prioridad;
            datos[i].prioridad = nueva_prioridad;
            
            if(subir){
                sift_up(i);
            }else{
                sift_down(i);
            }
        }else{
            throw new IndexOutOfBoundsException(i);
        }
    }

    public T erase(int i) throws Invalid_size_operation, IndexOutOfBoundsException{
        if(!empty()){
            if(i >= 0 && i < posicion_actual){
                Priority_node aux = topBack();
                T respuesta = datos[i].elemento;

                datos[i] = aux;
                posicion_actual--;

                if(aux.prioridad > datos[parent(i)].prioridad){
                    if(i < posicion_actual){
                        sift_up(i);
                    }
                }else{
                    sift_down(i);
                }

                return respuesta;
            }else{
                throw new IndexOutOfBoundsException(i);
            }
        }else{
            throw new Invalid_size_operation("Error: Lista vacia. No se puede eliminar el elemento.");
        }
    }

    public T extract_max() throws Invalid_size_operation{
        if(!empty()){
            T respuesta = get_max();
        
            datos[0] = datos[--posicion_actual];

            if(!empty()){
                sift_down(0);
            }

            return respuesta;
        }else{
            throw new Invalid_size_operation("Cola vacia, no se puede eliminar");
        }
    }

    public T get_max(){
        return topFront().elemento;
    }

    /**
     * No usar este método para ordenar un array, en su lugar se debe usar heap_sort(T[] array, int[] prioridades)
     */
    @Override public T[] heap_sort(T[] array){
        return array;
    }

    /**
     * Organiza en orden descendente los elementos del array dado según las prioridades dadas
     * @param elementos El array a ordenar
     * @param prioridades Un array con las prioridades de los elementos dados 
     * @return El array con sus elementos ordenados de manera descendente según sus prioridades
     */
    public T[] heap_sort(T[] elementos, int[] prioridades){
        Priority_queue<T> aux_heap = new Priority_queue<>(elementos, prioridades);
        T aux;
        for(int i = aux_heap.posicion_actual; i>0; i--){
            aux = aux_heap.extract_max();
            elementos[aux_heap.posicion_actual] = aux;
        }
        return elementos;
    }

    /**
     * Ingresa al elemento dado en la queue dándole prioridad igual al número de elementos actual
     * @param element El elemento a insertar
     */
    public void insert(T element){
        addLast(new Priority_node(element, posicion_actual));
        sift_up(posicion_actual-1);
    }

    /**
     * Ingresa al elemento dado en la queue según la prioridad dada
     * @param element El elemento a insertar
     * @param prioridad La prioridad de dicho elemento
     */
    public void insert(T element, int prioridad){
        addLast(new Priority_node(element, prioridad));
        sift_up(posicion_actual-1);
    }

    /**
     * Mueve a un elemento hacia abajo en la queue según su prioridad
     * @param i La posición del elemento que se quiere bajar
     */
    private void sift_down(int i){
        Priority_node aux;
        while(i < posicion_actual && (datos[i].prioridad < datos[left_child(i) < posicion_actual ? left_child(i) : i].prioridad || datos[i].prioridad < datos[right_child(i) < posicion_actual ? right_child(i) : i].prioridad)){
            if(right_child(i) >= posicion_actual || (datos[left_child(i)].prioridad >= datos[right_child(i)].prioridad)){
                aux = datos[left_child(i)];
                datos[left_child(i)] = datos[i];
                datos[i] = aux;
                i = left_child(i);
            }else{
                aux = datos[right_child(i)];
                datos[right_child(i)] = datos[i];
                datos[i] = aux;
                i = right_child(i);
            }
        }
    }

    /**
     * Mueve a un elemento hacia arriba en la queue según su prioridad
     * @param i La posición del elemento que se quiere subir
     */
    private void sift_up(int i){
        Priority_node aux;
        while(i > 0 && datos[i].prioridad > datos[parent(i)].prioridad){
            aux = datos[i];
            datos[i] = datos[parent(i)];
            datos[parent(i)] = aux;
            i = parent(i);
        }
    }
}