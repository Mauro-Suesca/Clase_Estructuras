public class Node_mult<T> extends Node<T>{
    private Node_mult<T> prev;

    Node_mult(T valor){
        super(valor);
        prev = null;
    }

    public Node_mult<T> get_prev(){
        return prev;
    }
}