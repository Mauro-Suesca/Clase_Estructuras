public class Node_mult<T> extends Node<T>{
    private Node_mult<T> prev;

    Node_mult(T valor){
        super(valor);
        set_prev(null);
        set_next(null);
    }

    public Node_mult<T> get_prev(){
        return prev;
    }

    public void set_prev(Node_mult<T> prev){
        this.prev = prev;
    }

    public Node_mult<T> get_next(){
        return ((Node_mult<T>)next);
    }

    public void set_next(Node_mult<T> next){
        this.next = next;
    }
}