public class Quaternary_heap<T extends Comparable<T>> extends Lista_array_orden<T> implements Heap<T>{
    Quaternary_heap(){
        super();
    }

    Quaternary_heap(int initial_size){
        super(initial_size);
    }

    Quaternary_heap(T[] array){
        this.datos = array;
        this.posicion_actual = array.length;
        int i = parent(posicion_actual-1);
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

                if(aux.compareTo(datos[parent(i)]) > 0){
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
        return topFront();
    }

    public T[] heap_sort(T[] array){
        Quaternary_heap<T> aux_heap = new Quaternary_heap<>(array);
        T aux;
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
     * Obtiene el índice del hijo número j del elemento en la posición i
     * @param i El índice del elemento cuyo hijo se quiere conocer
     * @param j El número de hijo que se quiere obtener (1-indexed)
     * @return El índice del hijo número j de la posición i
     */
    private int jth_child(int i, int j){
        return i*4+j;
    }

    @Override public int parent(int i){
        return (i-1)/4;
    }

    /**
     * Mueve a un elemento hacia abajo en el heap
     * @param i La posición del elemento que se quiere bajar
     */
    private void sift_down(int i){
        T aux;
        int max1, max2;
        while(i < posicion_actual && (datos[i].compareTo(datos[jth_child(i, 1) < posicion_actual ? jth_child(i, 1) : i]) < 0 || datos[i].compareTo(datos[jth_child(i, 2) < posicion_actual ? jth_child(i, 2) : i]) < 0 || datos[i].compareTo(datos[jth_child(i, 3) < posicion_actual ? jth_child(i, 3) : i]) < 0 || datos[i].compareTo(datos[jth_child(i, 4) < posicion_actual ? jth_child(i, 4) : i]) < 0)){
            if(jth_child(i, 2) >= posicion_actual || (datos[jth_child(i, 1)].compareTo(datos[jth_child(i, 2)]) >= 0)){
                max1 = jth_child(i, 1);
            }else{
                max1 = jth_child(i, 2);
            }

            if(jth_child(i, 3) < posicion_actual){
                if(jth_child(i, 4) >= posicion_actual || (datos[jth_child(i, 3)].compareTo(datos[jth_child(i, 4)]) >= 0)){
                    max2 = jth_child(i, 3);
                }else{
                    max2 = jth_child(i, 4);
                }
    
                if((datos[max1].compareTo(datos[max2]) < 0)){
                    max1 = max2;
                }
            }

            aux = datos[max1];
            datos[max1] = datos[i];
            datos[i] = aux;
            i = max1;
        }
    }

    /**
     * Mueve a un elemento hacia arriba en el heap según su prioridad
     * @param i La posición del elemento que se quiere subir
     */
    private void sift_up(int i){
        T aux;
        while(i > 0 && datos[i].compareTo(datos[parent(i)]) > 0){
            aux = datos[i];
            datos[i] = datos[parent(i)];
            datos[parent(i)] = aux;
            i = parent(i);
        }
    }
}