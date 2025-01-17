public class Binary_tree<T extends Comparable<T>>{
    private Node_tree<T> raiz;

    Binary_tree(T valor){
        raiz = new Node_tree<T>(valor);
    }

    /**
     * @return La altura del árbol
     */
    public int height(){
        return height_recursive(raiz);
    }

    private int height_recursive(Node_tree<T> n){
        if(n.is_leaf()){
            return 1;
        }else{
            return 1 + Math.max((n.get_left() != null ? height_recursive(n.get_left()) : 0), (n.get_right() != null ? height_recursive(n.get_right()) : 0));
        }
    }

    /**
     * Inserta el elemento dado en la posición correcta para que el árbol se mantenga siendo binario de búsqueda
     * @param valor El elemento a insertar
     */
    public void insert(T valor){
        insert_recursive(raiz, valor);
    }

    private void insert_recursive(Node_tree<T> n, T valor){
        if(n.get_valor().compareTo(valor) >= 0){
            if(n.get_left() != null){
                insert_recursive(n.get_left(), valor);
            }else{
                n.set_left(new Node_tree<T>(valor));
            }
        }else{
            if(n.get_right() != null){
                insert_recursive(n.get_right(), valor);
            }else{
                n.set_right(new Node_tree<T>(valor));
            }
        }
    }

    public void print(){
        print_recursive(raiz, 0);
    }

    private void print_recursive(Node_tree<T> n, int depth){
        if(depth != 0){
            System.out.println(String.format("%1$"+depth+"s", "") + n.get_valor());
        }else{
            System.out.println(n.get_valor());
        }

        if(n.get_left() != null){
            print_recursive(n.get_left(), depth+1);
        }

        if(n.get_right() != null){
            if(n.get_left() == null) System.out.println();
            print_recursive(n.get_right(), depth+1);
        }
    }

    /**
     * @return El número de nodos del árbol
     */
    public int size(){
        return size_recursive(raiz);
    }

    private int size_recursive(Node_tree<T> n){
        return 1 + (n.get_left() != null ? size_recursive(n.get_left()) : 0) + (n.get_right() != null ? size_recursive(n.get_right()) : 0);
    }
}