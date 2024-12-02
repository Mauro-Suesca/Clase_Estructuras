class Lista_enlazada_cola<T> extends Lista_enlazada<T>{
    protected Node<T> tail;

    Lista_enlazada_cola(){
        super();
        tail = null;
    }

    @Override public void addFirst(T elemento){
        super.addFirst(elemento);
        if(tail == null){
            tail = head;
        }
    }

    @Override public void addLast(T elemento){
        Node<T> nuevo = new Node<T>(elemento);
        if(!empty()){
            tail.set_next(nuevo);
        }else{
            head = nuevo;
        }
        tail = nuevo;
    }

    @Override public void removeFirst() throws Invalid_size_operation{
        super.removeFirst();
        if(head == null){
            tail = null;
        }
    }

    @Override public void removeLast() throws Invalid_size_operation{
        if(!empty()){
            Node<T> puntero = null;
            if(head != tail){
                puntero = run_through(head, tail);    //Por como funciona run_through, 'puntero' es el penúltimo elemento
                puntero.set_next(null);
            }else{
                head = null;
            }
            tail = puntero;
        }else{
            throw new Invalid_size_operation("Error: Lista vacia. No se puede eliminar el elemento.");
        }
    }

    @Override public T topBack(){
        if(!empty()){
            return tail.get_valor();
        }else{
            return null;
        }
    }

    @Override public boolean erase(T valor) throws Invalid_size_operation{
        boolean respuesta = false;
        if(!empty()){
            if(head.get_valor().equals(valor)){
                removeFirst();
                respuesta = true;
            }else{
                Node<T> current = head;
                while(current.get_next() != tail){
                    if(current.get_next().get_valor().equals(valor)){
                        current.set_next(current.get_next().get_next());
                        respuesta = true;
                        break;
                    }
                    current = current.get_next();
                }
                if(!respuesta && tail.get_valor().equals(valor)){
                    current.set_next(null);
                    tail = current;
                    respuesta = true;
                }
            }
        }else{
            throw new Invalid_size_operation("Error: Lista vacia. No se puede eliminar el elemento.");
        }
        return respuesta;
    }

    @Override public void addAfter(Node<T> posicion, T element){
        Node<T> nuevo = new Node<T>(element);
        if(posicion != tail){
            nuevo.set_next(posicion.get_next());
        }else{
            tail = nuevo;
        }
        posicion.set_next(nuevo);
    }
}