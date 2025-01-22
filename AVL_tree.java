public class AVL_tree<T extends Comparable<T>> extends Binary_tree<T>{
    AVL_tree(){
        super();
    }
    AVL_tree(T element){
        raiz = new Node_tree_avl<T>(element);
    }

    @Override public int height(){
        return ((Node_tree_avl<T>)raiz).get_height();
    }
}