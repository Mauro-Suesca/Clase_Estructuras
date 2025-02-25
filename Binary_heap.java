public class Binary_heap<T extends Comparable<T>> extends Lista_array_dinamico<T> implements Heap<T>{
    Binary_heap(){
        super();
    }

    Binary_heap(int initial_size){
        @SuppressWarnings("unchecked")
        final T[] datos = (T[]) new Comparable[initial_size];
        this.datos = datos;
        this.posicion_actual = 0;
    }

    Binary_heap(T[] array){
        this.datos = array;
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
     * @throws IndexOutOfBoundsException Si i no se encuentra dentro de los límites del heap
     */
    public void change_priority(int i, T nueva_prioridad) throws IndexOutOfBoundsException{
        if(i >= 0 && i < posicion_actual){
            boolean subir;

            subir = datos[i].compareTo(nueva_prioridad) <= 0;
            datos[i] = nueva_prioridad;
            
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
                T aux = topBack();
                T respuesta = datos[i];

                datos[i] = aux;
                posicion_actual--;

                if(!empty()){
                    if(aux.compareTo(datos[parent(i)]) > 0){
                        if(i < posicion_actual){
                            sift_up(i);
                        }
                    }else{
                        sift_down(i);
                    }
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
        return topFront();
    }

    public T[] heap_sort(T[] array){
        Binary_heap<T> aux_heap = new Binary_heap<>(array);
        T aux = null;
        for(int i = aux_heap.posicion_actual; i>0; i--){
            aux = aux_heap.extract_max();
            aux_heap.datos[aux_heap.posicion_actual] = aux;
        }
        return aux_heap.datos;
    }

    public void insert(T element){
        addLast(element);
        sift_up(posicion_actual-1);
    }

    /**
     * Mueve a un elemento hacia abajo en el heap
     * @param i La posición del elemento que se quiere bajar
     */
    protected void sift_down(int i){
        T aux;
        while(i < posicion_actual && (datos[i].compareTo(datos[left_child(i) < posicion_actual ? left_child(i) : i]) < 0 || datos[i].compareTo(datos[right_child(i) < posicion_actual ? right_child(i) : i]) < 0)){
            if(right_child(i) >= posicion_actual || (datos[left_child(i)].compareTo(datos[right_child(i)]) >= 0)){
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
     * Mueve a un elemento hacia arriba en el heap según su prioridad
     * @param i La posición del elemento que se quiere subir
     */
    protected void sift_up(int i){
        T aux;
        while(i > 0 && datos[i].compareTo(datos[parent(i)]) > 0){
            aux = datos[i];
            datos[i] = datos[parent(i)];
            datos[parent(i)] = aux;
            i = parent(i);
        }
    }
}