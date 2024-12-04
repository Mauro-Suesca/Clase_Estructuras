public class Lista_array_circ_orden<T extends Comparable<T>> extends Lista_array_circ_din<T>{
    Lista_array_circ_orden(){
        super();
    }

    Lista_array_circ_orden(int initial_size){
        this();
        @SuppressWarnings("unchecked")
        final T[] datos = (T[]) new Comparable[initial_size];
        this.datos = datos;
    }

    @Override protected void expandir_array(boolean es_front, int posicion, T element){
        @SuppressWarnings("unchecked")
        T[] nuevo_datos = (T[])new Comparable[datos.length*2];
        int posicion_actual_nuevo = 0;
        int posicion_actual_datos = front;

        if(es_front){
            nuevo_datos[posicion_actual_nuevo++] = element;
        }
        //Estas líneas se ponen, porque si se quiere añadir al principio, si no se tienen, no se va a añadir ningún elemento del array original al nuevo
        nuevo_datos[posicion_actual_nuevo++] = datos[posicion_actual_datos];
        posicion_actual_datos = change_position(posicion_actual_datos, true);

        while(posicion_actual_datos != posicion){
            nuevo_datos[posicion_actual_nuevo++] = datos[posicion_actual_datos];
            posicion_actual_datos = change_position(posicion_actual_datos, true);
        }

        if(!es_front){
            nuevo_datos[posicion_actual_nuevo++] = element;
        }

        while(posicion_actual_datos != back){
            nuevo_datos[posicion_actual_nuevo++] = datos[posicion_actual_datos];
            posicion_actual_datos = change_position(posicion_actual_datos, true);
        }

        datos = nuevo_datos;
        front = 0;
        size = posicion_actual_nuevo;
        back = !full() ? posicion_actual_nuevo : 0;  //Si después de añadir todo, la lista está llena, back puede estar apuntando fuera del array
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
        int posicion = front, respuesta = -1, aux = 0;

        if(full()){ //Si está lleno y solo se deja el while, no se revisará ningún elemento
            if(datos[posicion].compareTo(valor) >= 0){
                return posicion;
            }
            posicion = change_position(posicion, true);
        }
        while(posicion != back){
            aux = datos[posicion].compareTo(valor);
            if(aux >= 0){
                respuesta = aux == 0 ? posicion : respuesta;
                aux = posicion;
                break;
            }else{
                aux = posicion;
            }
            posicion = change_position(posicion, true);
        }

        if(!encontrar_exacto){
            respuesta = aux;
            if(aux == change_position(back, false)){
                if(datos[aux].compareTo(valor) < 0){
                    respuesta = change_position(respuesta, true);
                }
            }
        }

        return respuesta;
    }
}