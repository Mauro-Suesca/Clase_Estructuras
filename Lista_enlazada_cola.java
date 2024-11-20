import java.util.InputMismatchException;

class Lista_enlazada_cola<T> extends Lista_enlazada<T>{
    protected Node<T> tail;

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
        if(tail != null){
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
        if(tail != null){
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
                    removeLast();
                    respuesta = true;
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
        }else{
            tail = nuevo;
        }
        posicion.set_next(nuevo);
    }
}