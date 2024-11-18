public class Node<T>{
    protected T valor;
    protected Node<T> next;

    Node(T valor){
        this.valor = valor;
        next = null;
    }

    public Node<T> get_next(){
        return next;
    }

    public T get_valor(){
        return valor;
    }

    public void set_next(Node<T> next){
        this.next = next;
    }

    public void set_valor(T valor){
        this.valor = valor;
    }
}