public class Lista_doble_enlazada<T> extends Lista_enlazada_cola<T>{
    Lista_doble_enlazada(){
        super();
    }

    @Override public void addFirst(T elemento){
        Node_mult<T> nuevo = new Node_mult<T>(elemento);
        if(!empty()){
            nuevo.set_next(head);
            as_mult(head).set_prev(nuevo);
        }
        if(tail == null){
            tail = nuevo;
        }
        head = nuevo;
    }

    @Override public void addLast(T elemento){
        Node_mult<T> nuevo = new Node_mult<T>(elemento);
        if(!empty()){
            tail.set_next(nuevo);
            nuevo.set_prev(as_mult(tail));
        }else{
            head = nuevo;
        }
        tail = nuevo;
    }

    @Override public void removeFirst() throws Invalid_size_operation{
        super.removeFirst();
        if(head != null){
            as_mult(head).set_prev(null);
        }
    }

    @Override public void removeLast() throws Invalid_size_operation{
        if(!empty()){
            if(head != tail){
                tail = as_mult(tail).get_prev();
                tail.set_next(null);
            }else{
                tail = null;
                head = null;
            }
        }else{
            throw new Invalid_size_operation("Error: Lista vacia. No se puede eliminar el elemento.");
        }
    }

    @Override public Node_mult<T> find(T valor){ //Retorna el Nodo de la primera ocurrencia del elemento, retorna null si el elemento no existe en la lista
        return as_mult(super.find(valor));
    }

    @Override public boolean erase(T valor) throws Invalid_size_operation{
        boolean respuesta = false;
        Node_mult<T> ubicacion = find(valor);

        if(ubicacion != null){
            if(ubicacion != head){
                ubicacion.get_prev().set_next(ubicacion.get_next());
            }else{
                head = head.get_next();
            }
            if(ubicacion != tail){
                ubicacion.get_next().set_prev(ubicacion.get_prev());
            }else{
                tail = as_mult(tail).get_prev();
            }
            respuesta = true;
        }else if(empty()){
            throw new Invalid_size_operation("Error: Lista vacia. No se puede eliminar el elemento.");
        }
        
        return respuesta;
    }

    @Override public void addBefore(Node<T> posicion, T element){
        Node_mult<T> nuevo = new Node_mult<T>(element);
        if(posicion != head){
            Node_mult<T> anterior = as_mult(as_mult(posicion).get_prev());
            anterior.set_next(nuevo);
            nuevo.set_prev(anterior);
        }else{
            head = nuevo;
        }
        as_mult(posicion).set_prev(nuevo);
        nuevo.set_next(posicion);
    }

    @Override public void addAfter(Node<T> posicion, T element){
        Node_mult<T> nuevo = new Node_mult<T>(element);
        if(posicion != tail){
            Node_mult<T> siguiente = as_mult(posicion.get_next());
            siguiente.set_prev(nuevo);
            nuevo.set_next(siguiente);
        }else{
            tail = nuevo;
        }
        nuevo.set_prev(as_mult(posicion));
        posicion.set_next(nuevo);
    }

    /**
     * @param nodo Un nodo de tipo 'Node_mult' enmascarado como 'Node'.
     * @return El nodo dado typecasteado a 'Node_mult'
     */
    protected Node_mult<T> as_mult(Node<T> nodo){
        return ((Node_mult<T>)nodo);
    }
}