public class Stack_nodo<T> extends Lista_enlazada<T> implements Stack<T>{
    Stack_nodo(){
        super();
    }

    Stack_nodo(Stack_nodo<T> otro){
        Node<T> current = otro.head;
        while(current != null){
            this.addLast(current.valor);
            current = current.next;
        }
    }
    
    @Override public T pop() throws Invalid_size_operation{
        T respuesta = topBack();
        removeFirst();
        return respuesta;
    }

    @Override public void push(T elemento){
        addFirst(elemento);
    }
}