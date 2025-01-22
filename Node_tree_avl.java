public class Node_tree_avl<T extends Comparable<T>> extends Node_tree<T>{
    private int height;

    Node_tree_avl(T valor){
        super(valor);
        set_height(0);
    }

    public int get_height(){
        return height;
    }

    @Override public Node_tree_avl<T> get_left(){
        return (Node_tree_avl<T>)left;
    }

    @Override public Node_tree_avl<T> get_right(){
        return (Node_tree_avl<T>)right;
    }

    public void set_height(int height){
        this.height = height;
    }

    public void set_left(Node_tree_avl<T> left){
        this.left = left;
    }

    public void set_right(Node_tree_avl<T> right){
        this.right = right;
    }
}