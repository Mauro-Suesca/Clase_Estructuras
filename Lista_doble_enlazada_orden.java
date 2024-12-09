public class Lista_doble_enlazada_orden<T extends Comparable<T>> extends Lista_doble_enlazada<T>{
    @Override public Node_mult<T> find(T valor){
        return search(valor, true);
    }

    /**
     * Inserta un elemento a la lista en la posición adecuada para mantener el orden.
     * @param element El elemento a insertar.
     */
    public void insert(T element){
        Node_mult<T> posicion = search(element, false);
        if(posicion != null){   //Si search con 'encontrar_exacto' da null, significa que el elemento se debe añadir al final de la lista
            addBefore(posicion, element);
        }else{
            addLast(element);
        }
    }
    
    /**
     * Busca el elemento con el valor indicado.
     * @param valor El valor a buscar en la lista.
     * @param encontrar_exacto Si el método debe encontrar el valor en la lista o solo la posición donde debería insertarse.
     * @return El nodo donde se encuentra el valor o null si no está en la lista (encontrar_exacto == true). El Nodo donde se encuentra el primer elemento mayor o igual al valor dado, o null si no hay elementos mayores o iguales en la lista (encontrar_exacto = false).
     */
    public Node_mult<T> search(T valor, boolean encontrar_exacto){
        Node_mult<T> respuesta = null, current = as_mult(head);
        int aux;

        if(tail != null){
            aux = tail.get_valor().compareTo(valor);
            if(aux > 0){
                while(current != null){
                    aux = current.get_valor().compareTo(valor);
                    if(aux >= 0){
                        respuesta = aux == 0 ? current : null;
                        break;
                    }
        
                    current = current.get_next();
                }
            }else if(aux == 0){
                respuesta = as_mult(tail);
            }else{
                current = null;
            }
        }        

        if(!encontrar_exacto){
            respuesta = current;
        }

        return respuesta;
    }
}