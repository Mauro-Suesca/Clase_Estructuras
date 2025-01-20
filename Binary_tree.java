public class Binary_tree<T extends Comparable<T>>{
    private Node_tree<T> raiz;

    Binary_tree(T element){
        raiz = new Node_tree<T>(element);
    }

    /**
     * Encuentra el nodo del árbol con el valor dado
     * @param valor
     * @return El nodo que contiene el valor indicado
     */
    public Node_tree<T> find(T valor){
        Node_tree<T> current = raiz;
        while(current != null){
            if(current.get_valor().compareTo(valor) > 0){
                current = current.get_left();
            }else if(current.get_valor().compareTo(valor) < 0){
                current = current.get_right();
            }else{
                break;
            }
        }

        return current;
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
     * @param element El elemento a insertar
     */
    public void insert(T element){
        insert_recursive(raiz, element);
    }

    private void insert_recursive(Node_tree<T> n, T element){
        if(n.get_valor().compareTo(element) >= 0){
            if(n.get_left() != null){
                insert_recursive(n.get_left(), element);
            }else{
                n.set_left(new Node_tree<T>(element));
            }
        }else{
            if(n.get_right() != null){
                insert_recursive(n.get_right(), element);
            }else{
                n.set_right(new Node_tree<T>(element));
            }
        }
    }

    /**
     * Encuentra el Node_tree dentro del árbol con el mínimo valor mayor al valor del nodo dado
     * @param nodo El Node_tree cuyo vecino siguiente se quiere encontrar
     * @return El Node_tree dentro del árbol cuyo valor es el mínimo valor mayor al valor del nodo dado, o null si no existe tal valor
     */
    public Node_tree<T> Next_Neighbor(Node_tree<T> nodo){
        Node_tree<T> current = null;
        if(nodo.get_right() != null){
            current = nodo.get_right();
            while(current.get_left() != null){
                current = current.get_left();
            }
        }else{
            if(raiz.get_valor().compareTo(nodo.get_valor()) > 0){
                Node_tree<T> min = raiz.get_left();
                current = raiz.get_left();
                while(current != null && current != nodo){
                    if(current.get_valor().compareTo(nodo.get_valor()) < 0){
                        current = current.get_right();
                    }else{
                        if(current.get_valor().compareTo(min.get_valor()) < 0){
                            min = current;
                        }
                        current = current.get_left();
                    }
                }
                current = min;
            }else{
                return null;
            }
        }
        return current;
    }

    /**
     * Imprime los elementos del árbol en el orden dado por un recorrido Breadth-First
     */
    public void print_breadth(){
        Queue_nodo<Node_tree<T>> recorrer = new Queue_nodo<>();
        Node_tree<T> aux = null;
        recorrer.enqueue(raiz);
        while(!recorrer.empty()){
            try{
                aux = recorrer.dequeue();
            }catch(Invalid_size_operation e){}
            
            System.out.println(aux.get_valor() + " ");

            if(aux.get_left() != null){
                recorrer.enqueue(aux.get_left());
            }

            if(aux.get_right() != null){
                recorrer.enqueue(aux.get_right());
            }
        }
    }

    /**
     * Imprime los elementos del árbol en el orden dado por un recorrido in order
     */
    public void print_in(){
        print_in_recursive(raiz);
    }

    private void print_in_recursive(Node_tree<T> n){
        if(n.get_left() != null){
            print_in_recursive(n.get_left());
        }

        System.out.println(n.get_valor() + " ");

        if(n.get_right() != null){
            print_in_recursive(n.get_right());
        }
    }

    /**
     * Imprime los elementos del árbol en el orden dado por un recorrido post order
     */
    public void print_post(){
        print_post_recursive(raiz);
    }

    private void print_post_recursive(Node_tree<T> n){
        if(n.get_left() != null){
            print_post_recursive(n.get_left());
        }

        if(n.get_right() != null){
            print_post_recursive(n.get_right());
        }

        System.out.println(n.get_valor() + " ");
    }

    /**
     * Imprime los elementos del árbol en el orden dado por un recorrido pre order, en un formato similar al de las carpetas de windows
     */
    public void print_pre(){
        print_pre_recursive(raiz, 0);
    }

    private void print_pre_recursive(Node_tree<T> n, int depth){
        if(depth != 0){
            System.out.println(String.format("%1$"+depth+"s", "") + n.get_valor());
        }else{
            System.out.println(n.get_valor());
        }

        if(n.get_left() != null){
            print_pre_recursive(n.get_left(), depth+1);
        }

        if(n.get_right() != null){
            if(n.get_left() == null) System.out.println();
            print_pre_recursive(n.get_right(), depth+1);
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