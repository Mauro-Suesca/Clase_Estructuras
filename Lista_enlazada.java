public class Lista_enlazada<T> implements Lista_nodo<T>{
    protected Node<T> head;

    Lista_enlazada(){
        head = null;
    }

    public void addFirst(T elemento){
        Node<T> nuevo = new Node<T>(elemento);
        if(!empty()){
            nuevo.set_next(head);
        }
        head = nuevo;
    }

    public void addLast(T elemento){
        Node<T> nuevo = new Node<T>(elemento);
        if(!empty()){
            run_through(head, null).set_next(nuevo);
        }else{
            head = nuevo;
        }
    }

    public void removeFirst() throws Invalid_size_operation{
        if(!empty()){
            head = head.get_next();
        }else{
            throw new Invalid_size_operation("Error: Lista vacia. No se puede eliminar el elemento.");
        }
    }

    public void removeLast() throws Invalid_size_operation{
        if(!empty()){
            Node<T> current = head;
            if(current.get_next() != null){
                while(current.get_next().get_next() != null){
                    current = current.get_next();
                }
                current.set_next(null);
            }else{
                head = null;
            }
        }else{
            throw new Invalid_size_operation("Error: Lista vacia. No se puede eliminar el elemento.");
        }
    }
        
    public void print(){
        Node<T> current = head;
        System.out.print("Elementos en la lista enlazada: [");
        if(current != null){
            System.out.print(current.get_valor());
            current = current.get_next();
            while(current != null){
                System.out.print(" " + current.get_valor());
                current = current.get_next();
            }
        }
        System.out.println("]");
    }

    public T topBack(){
        if(!empty()){
            return run_through(head, null).get_valor();
        }else{
            return null;
        }
    }

    public T topFront(){
        if(!empty()){
            return head.get_valor();
        }else{
            return null;
        }
    }

    public Node<T> find(T valor){ //Retorna el Nodo de la primera ocurrencia del elemento, retorna null si el elemento no existe en la lista
        Node<T> respuesta = null;
        Node<T> current = head;
        while(current != null){
            if(current.get_valor().equals(valor)){
                respuesta = current;
                break;
            }
            current = current.get_next();
        }
        return respuesta;
    }

    public boolean erase(T valor) throws Invalid_size_operation{
        boolean respuesta = false;
        if(!empty()){
            if(head.get_valor().equals(valor)){
                head = head.get_next();
                respuesta = true;
            }else{
                Node<T> current = head;
                while(current.get_next() != null){
                    if(current.get_next().get_valor().equals(valor)){
                        current.set_next(current.get_next().get_next());
                        respuesta = true;
                        break;
                    }
                    current = current.get_next();
                }
            }
        }else{
            throw new Invalid_size_operation("Error: Lista vacia. No se puede eliminar el elemento.");
        }
        return respuesta;
    }

    public boolean empty(){
        return head == null;
    }

    public void addBefore(Node<T> posicion, T element){
        Node<T> nuevo = new Node<T>(element);
        if(posicion != head){
            run_through(head, posicion).set_next(nuevo);
        }else{
            head = nuevo;
        }
        nuevo.set_next(posicion);
    }

    public void addAfter(Node<T> posicion, T element){
        if(posicion.get_next() != null){
            Node<T> nuevo = new Node<T>(element);
            nuevo.set_next(posicion.get_next());
            posicion.set_next(nuevo);
        }else{
            posicion.set_next(new Node<T>(element));
        }
    }

    /**
     * Recorre la lista hasta encontrar el nodo indicado.
     * @param start El nodo donde iniciar la búsqueda.
     * @param until El punto donde se debe parar la búsqueda.
     * @return El nodo en la lista cuyo 'next' es igual a 'until'.
     */
    protected Node<T> run_through(Node<T> start, Node<T> until){
        Node<T> current = start;
        while(current.get_next() != until){
            current = current.get_next();
        }
        return current;
    }
}