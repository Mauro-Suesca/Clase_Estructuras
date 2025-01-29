import java.lang.reflect.Array;

public class Priority_queue<T> extends Lista_array_dinamico<Priority_queue<T>.Priority_node>{
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

    /**
     * Cambia la prioridad del elemento en la posición indicada
     * @param i La posición del elemento cuya prioridad se quiere cambiar
     * @param nueva_prioridad La nueva prioridad a asignar al elemento
     * @throws IndexOutOfBoundsException Si i no se encuentra dentro de los límites de la queue
     */
    public void change_priority(int i, int nueva_prioridad) throws IndexOutOfBoundsException{
        boolean subir;

        subir = datos[i].prioridad <= nueva_prioridad;
        datos[i].prioridad = nueva_prioridad;
        
        if(subir){
            sift_up(i);
        }else{
            sift_down(i);
        }
    }

    /**
     * Elimina el elemento en la posición dada
     * @param i La posición del elemento a eliminar
     * @return El elemento que se eliminó
     * @throws Invalid_size_operation Si se trata de eliminar de una queue vacía
     * @throws IndexOutOfBoundsException Si se trata de acceder a un elemento fuera de los límites de la queue
     */
    public T erase(int i) throws Invalid_size_operation, IndexOutOfBoundsException{
        if(!empty()){
            Priority_node aux = topBack();
            T respuesta = datos[i].elemento;

            datos[i] = aux;
            posicion_actual--;

            if(aux.prioridad > datos[parent(i)].prioridad){
                sift_up(i);
            }else{
                sift_down(i);
            }

            return respuesta;
        }else{
            throw new Invalid_size_operation("Error: Lista vacia. No se puede eliminar el elemento.");
        }
    }

    /**
     * Elimina y obtiene el elemento con la mayor prioridad de la queue
     * @return El elemento que tenía la mayor prioridad
     * @throws Invalid_size_operation Si se trata de eliminar de una queue vacía
     */
    public T extract_max() throws Invalid_size_operation{
        if(!empty()){
            T respuesta = get_max();
        
            datos[0] = datos[posicion_actual-1];
            posicion_actual--;

            if(!empty()){
                sift_down(0);
            }

            return respuesta;
        }else{
            throw new Invalid_size_operation("Cola vacia, no se puede eliminar");
        }
    }

    /**
     * @return El elemento con la mayor prioridad de la queue
     */
    public T get_max(){
        return topFront().elemento;
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
     * Obtiene la posición del hijo izquierdo del elemento que se encuentra en la posición dada
     * @param i La posición del elemento cuyo hijo se quiere obtener
     * @return La posición del hijo izquierdo del elemento en 'i'
     */
    private int left_child(int i){
        return i*2+1;
    }

    /**
     * Obtiene la posición del padre del elemento que se encuentra en la posición dada
     * @param i La posición del elemento cuyo padre se quiere obtener
     * @return La posición del padre del elemento en 'i'
     */
    private int parent(int i){
        return (i-1)/2;
    }

    /**
     * Obtiene la posición del hijo derecho del elemento que se encuentra en la posición dada
     * @param i La posición del elemento cuyo hijo se quiere obtener
     * @return La posición del hijo derecho del elemento en 'i'
     */
    private int right_child(int i){
        return left_child(i)+1;
    }

    /**
     * Mueve a un elemento hacia abajo en la queue según su prioridad
     * @param i La posición del elemento que se quiere bajar
     */
    private void sift_down(int i){
        Priority_node aux;
        while(i < posicion_actual && (datos[i].prioridad < datos[left_child(i) < posicion_actual ? left_child(i) : i].prioridad || datos[i].prioridad < datos[right_child(i) < posicion_actual ? right_child(i) : i].prioridad)){
            if(datos[left_child(i)].prioridad >= datos[right_child(i)].prioridad){
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