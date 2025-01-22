public class Node_tree<T>{
    protected T valor;
    protected Node_tree<T> left;
    protected Node_tree<T> right;

    Node_tree(T valor){
        this.valor = valor;
        set_left(null);
        set_right(null);
    }

    public Node_tree<T> get_left(){
        return left;
    }

    public Node_tree<T> get_right(){
        return right;
    }

    public T get_valor(){
        return valor;
    }

    public boolean is_leaf(){
        return this.left == null && this.right == null;
    }

    public void set_left(Node_tree<T> left){
        this.left = left;
    }

    public void set_right(Node_tree<T> right){
        this.right = right;
    }

    public void set_valor(T valor){
        this.valor = valor;
    }
}