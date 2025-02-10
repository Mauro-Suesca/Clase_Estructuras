public class Quaternary_heap<T extends Comparable<T>> extends Binary_heap<T>{
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

    @Override public T[] heap_sort(T[] array){
        Quaternary_heap<T> aux_heap = new Quaternary_heap<>(array);
        T aux;
        for(int i = aux_heap.posicion_actual; i>0; i--){
            aux = aux_heap.extract_max();
            aux_heap.datos[aux_heap.posicion_actual] = aux;
        }
        return aux_heap.datos;
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

    @Override protected void sift_down(int i){
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
}