public class Lista_enlazada_orden<T extends Comparable<T>> extends Lista_enlazada_cola<T>{
    @Override public boolean erase(T valor) throws Invalid_size_operation{
        if(!empty()){
            Node<T> posicion = search(valor, false);
            boolean respuesta = false;

            if(posicion != null){
                if(posicion != tail){ //Si posicion es tail, no hay ningún elemento igual al valor dado en la lista (por la definición de search)
                    if(posicion.get_next().get_valor().compareTo(valor) == 0){
                        posicion.set_next(posicion.get_next().get_next());
                        respuesta = true;
                    }
                }
            }else{ //Si posicion es null, significa que el primer elemento mayor o igual está en head 
                if(head.get_valor().compareTo(valor) == 0){
                    removeFirst();
                    respuesta = true;
                }
            }

            return respuesta;
        }else{
            throw new Invalid_size_operation("Error: Lista vacia. No se puede eliminar el elemento.");
        }
    }
    
    @Override public Node<T> find(T valor){
        return search(valor, true);
    }

    /**
     * Inserta un elemento a la lista en la posición adecuada para mantener el orden.
     * @param element El elemento a insertar.
     */
    public void insert(T element){
        Node<T> posicion = search(element, false);
        if(posicion != null){
            addAfter(posicion, element);
        }else{
            addFirst(element);
        }
    }
    
    /**
     * Busca el elemento con el valor indicado.
     * @param valor El valor a buscar en la lista.
     * @param encontrar_exacto Si el método debe encontrar el valor en la lista o solo la posición donde debería insertarse.
     * @return El nodo donde se encuentra el valor o null si no está en la lista (encontrar_exacto == true). El Nodo donde se encuentra el elemento anterior al primer elemento mayor o igual al valor dado, null si dicho primer elemento es head (encontrar_exacto = false).
     */
    public Node<T> search(T valor, boolean encontrar_exacto){
        Node<T> respuesta = null, prev = null, current = head;
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
        
                    prev = current;
                    current = current.get_next();
                }
            }else if(aux == 0){
                if(!encontrar_exacto){
                    prev = run_through(head, tail);
                }
                respuesta = tail;
            }else{
                respuesta = null;
                prev = tail;
            }
        }        

        if(!encontrar_exacto){
            respuesta = prev;
        }

        return respuesta;
    }
}