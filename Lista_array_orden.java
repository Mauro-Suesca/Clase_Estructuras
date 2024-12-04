public class Lista_array_orden<T extends Comparable<T>> extends Lista_array_dinamico<T>{
    Lista_array_orden(){
        super();
    }

    Lista_array_orden(Class<T> c, int initial_size){
        super(c, initial_size);
    }
    
    @Override public int find(T valor){
        return search(valor, true);
    }
    
    /**
     * Busca el elemento con el valor indicado.
     * @param valor El valor a buscar en la lista.
     * @param encontrar_exacto Si el método debe encontrar el valor en la lista o solo la posición donde debería insertarse.
     * @return El índice donde se encuentra el valor o -1 si no está en la lista (encontrar_exacto == true). El índice donde se encuentra el primer elemento mayor o igual al valor dado (encontrar_exacto = false).
     */
    public int search(T valor, boolean encontrar_exacto){
        this.datos = (T[])this.datos;
        int respuesta = -1, aux = 0;
        for(int i=0; i<posicion_actual; i++){
            aux = datos[i].compareTo(valor);
            if(aux >= 0){
                respuesta = aux == 0 ? i : respuesta;
                aux = i;
                break;
            }else{
                aux++;
            }
        }
        
        if(!encontrar_exacto){
            respuesta = aux;
        }
        return respuesta;
    }

    public void insert(T element){
        try{
            addBefore(search(element, false), element);
        }catch(Invalid_size_operation e){}
    }
}
