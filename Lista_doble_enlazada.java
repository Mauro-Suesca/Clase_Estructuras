import java.util.InputMismatchException;

public class Lista_doble_enlazada<T> extends Lista_enlazada_cola<T>{
    Lista_doble_enlazada(){
        super();
    }

    @Override
    public void addFirst(T elemento){
        Node_mult<T> nuevo = new Node_mult<T>(elemento);
        if(head != null){
            nuevo.set_next(head);
            as_mult(head).set_prev(nuevo);
        }
        if(tail == null){
            tail = nuevo;
        }
        head = nuevo;
    }

    @Override
    public void addLast(T elemento){
        Node_mult<T> nuevo = new Node_mult<T>(elemento);
        if(tail != null){
            tail.set_next(nuevo);
            nuevo.set_prev(as_mult(tail));
        }else{
            head = nuevo;
        }
        tail = nuevo;
    }

    @Override
    public void removeFirst(){
        super.removeFirst();
        as_mult(head).set_prev(null);
    }

    @Override
    public void removeLast(){
        if(tail != null){
            if(head != tail){
                tail = as_mult(tail).get_prev();
                tail.set_next(null);
            }else{
                tail = null;
                head = null;
            }
        }else{
            throw new InputMismatchException("Error: Lista vacia. No se puede eliminar el elemento.");
        }
    }

    @Override
    public Node_mult<T> find(T valor){ //Retorna el Nodo de la primera ocurrencia del elemento, retorna null si el elemento no existe en la lista
        return as_mult(super.find(valor));
    }

    @Override
    public boolean erase(T valor){
        boolean respuesta = false;
        if(head != null){
            if(head.get_valor().equals(valor)){
                removeFirst();
                respuesta = true;
            }else{
                Node_mult<T> current = as_mult(head.get_next());
                while(current != tail){
                    if(current.get_valor().equals(valor)){
                        current.get_next().set_prev(current.get_prev());
                        current.get_prev().set_next(current.get_next());
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
    public void addBefore(Node<T> posicion, T valor){
        Node_mult<T> nuevo = new Node_mult<T>(valor);
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

    @Override
    public void addAfter(Node<T> posicion, T valor){
        Node_mult<T> nuevo = new Node_mult<T>(valor);
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

    private Node_mult<T> as_mult(Node<T> nodo){
        return ((Node_mult<T>)nodo);
    }
}