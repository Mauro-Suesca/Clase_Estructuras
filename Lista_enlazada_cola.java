import java.util.InputMismatchException;

class Lista_enlazada_cola<T> extends Lista_enlazada<T>{
    private Node<T> tail;

    Lista_enlazada_cola(){
        super();
        tail = null;
    }

    @Override
    public void addFirst(T elemento){
        super.addFirst(elemento);
        if(tail == null){
            tail = head;
        }
    }

    @Override
    public void addLast(T elemento){
        Node<T> nuevo = new Node<T>(elemento);
        if(head != null){
            tail.set_next(nuevo);
        }else{
            head = nuevo;
        }
        tail = nuevo;
    }

    @Override
    public void removeFirst(){
        super.removeFirst();
        if(head == null){
            tail = null;
        }
    }

    @Override
    public void removeLast(){
        if(head != null){
            Node<T> puntero = null;
            if(head != tail){
                puntero = run_through(head, tail);    //Por como funciona run_through, 'puntero' es el penúltimo elemento
                puntero.set_next(null);
            }else{
                head = null;
            }
            tail = puntero;
        }else{
            throw new InputMismatchException("Error: Lista vacia. No se puede eliminar el elemento.");
        }
    }

    @Override
    public T topBack(){
        if(tail != null){
            return tail.get_valor();
        }else{
            return null;
        }
    }

    @Override
    public boolean erase(T valor){
        boolean respuesta = false;
        if(head != null){
            if(head.get_valor().equals(valor)){
                if(head == tail){
                    tail = null;
                }
                head = head.get_next();
                respuesta = true;
            }else if(tail.get_valor().equals(valor)){
                Node<T> puntero = run_through(head, tail);
                puntero.set_next(null);
                tail = puntero;
            }else{
                Node<T> current = head;
                while(current.get_next() != null){
                    if(current.get_next().get_valor().equals(valor)){
                        current.set_next(current.get_next().get_next());
                        respuesta = true;
                    }
                    current = current.get_next();
                }
            }
        }
        return respuesta;
    }

    @Override
    public void addAfter(Node<T> posicion, T valor){
        Node<T> nuevo = new Node<T>(valor);
        if(posicion != tail){
            nuevo.set_next(posicion.get_next());
            posicion.set_next(nuevo);
        }else{
            posicion.set_next(nuevo);
            tail = nuevo;
        }
    }
}