public class Lista_array_orden<T extends Comparable<T>> extends Lista_array_dinamico<T>{   
    Lista_array_orden(){
        super();
    }

    Lista_array_orden(int initial_size){
        @SuppressWarnings("unchecked")
        final T[] datos = (T[]) new Comparable[initial_size];
        this.datos = datos;
        this.posicion_actual = 0;
    }

    @Override protected void expandir_array(int posicion, T element){
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

    @Override public int find(T valor){
        return search(valor, true);
    }

    /**
     * Inserta un elemento a la lista en la posición adecuada para mantener el orden.
     * @param element El elemento a insertar.
     */
    public void insert(T element){
        addBefore(search(element, false), element);
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
}
