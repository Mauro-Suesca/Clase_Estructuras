public class Binary_tree<T extends Comparable<T>>{
    protected Node_tree<T> raiz;

    Binary_tree(){
        raiz = null;
    }
    Binary_tree(T element){
        raiz = new Node_tree<T>(element);
    }

    /**
     * Elimina el valor dado del árbol si lo tiene, y mantiene las propiedades del árbol
     * @param valor El valor a eliminar
     * @return Si se encontró el elemento en el árbol para poder eliminarlo
     * @throws Invalid_size_operation Si se intenta eliminar de un árbol vacío
     */
    public boolean delete(T valor) throws Invalid_size_operation{
        if(raiz != null){
            boolean respuesta = false;
            Node_tree<T> current = search(valor, true);
            
            if(current != null && current != raiz){
                delete_recursive(current, current.get_valor().compareTo(valor) > 0);
                respuesta = true;
            }else if(current == raiz){
                if(raiz.get_left() == null){
                    this.raiz = raiz.get_right();
                }else if(raiz.get_right() == null){
                    this.raiz = raiz.get_left();
                }else{
                    Node_tree<T> aux = raiz.get_right();
                    boolean lefta_righta = true;
        
                    if(aux.get_left() != null){
                        while(aux.get_left().get_left() != null){
                            aux = aux.get_left();
                        }
                    }else{
                        aux = raiz;
                        lefta_righta = false;
                    }
        
                    raiz.set_valor((lefta_righta ? aux.get_left() : aux.get_right()).get_valor());
                    delete_recursive(aux, lefta_righta);
                }
                respuesta = true;
            }

            return respuesta;
        }else{
            throw new Invalid_size_operation("Error: Arbol vacio. No se puede eliminar el elemento.");
        }
    }

    /**
     * Elimina un nodo de un árbol, y se encarga de manera recursiva de que la estructura del árbol no se pierda
     * @param padre El padre del nodo a eliminar
     * @param left Si el nodo a eliminar es el hijo de la izquierda de 'padre'
     */
    private void delete_recursive(Node_tree<T> padre, boolean left){
        Node_tree<T> nodo = left ? padre.get_left() : padre.get_right();
        if(nodo.get_left() == null){
            if(left){
                padre.set_left(nodo.get_right());
            }else{
                padre.set_right(nodo.get_right());
            }
        }else if(nodo.get_right() == null){
            if(left){
                padre.set_left(nodo.get_left());
            }else{
                padre.set_right(nodo.get_left());
            }
        }else{
            Node_tree<T> aux = nodo.get_right();
            boolean lefta_righta = true;

            if(aux.get_left() != null){
                while(aux.get_left().get_left() != null){
                    aux = aux.get_left();
                }
            }else{
                aux = nodo;
                lefta_righta = false;
            }

            nodo.set_valor((lefta_righta ? aux.get_left() : aux.get_right()).get_valor());
            delete_recursive(aux, lefta_righta);
        }
    }

    /**
     * Encuentra el nodo del árbol con el valor dado
     * @param valor
     * @return El nodo que contiene el valor indicado, o null si el valor no existe en el árbol
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
        return raiz != null ? height_recursive(raiz) : 0;
    }

    private int height_recursive(Node_tree<T> n){
        return 1 + Math.max((n.get_left() != null ? height_recursive(n.get_left()) : 0), (n.get_right() != null ? height_recursive(n.get_right()) : 0));
    }

    /**
     * Inserta el elemento dado en la posición correcta para que el árbol se mantenga siendo binario de búsqueda
     * @param element El elemento a insertar
     */
    public void insert(T element){
        if(raiz != null){
            insert_recursive(raiz, element);
        }else{
            raiz = new Node_tree<T>(element);
        }
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
    public Node_tree<T> Next(Node_tree<T> nodo){
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
        if(raiz != null){
            Queue_nodo<Node_tree<T>> recorrer = new Queue_nodo<>();
            Node_tree<T> aux = null;
            recorrer.enqueue(raiz);
            while(!recorrer.empty()){
                aux = recorrer.dequeue();
                
                System.out.println(aux.get_valor() + " ");

                if(aux.get_left() != null){
                    recorrer.enqueue(aux.get_left());
                }

                if(aux.get_right() != null){
                    recorrer.enqueue(aux.get_right());
                }
            }
        }else{
            System.out.println();
        }
    }

    /**
     * Imprime los elementos del árbol en el orden dado por un recorrido in order
     */
    public void print_in(){
        if(raiz != null){
            print_in_recursive(raiz);
        }else{
            System.out.println();
        }
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
        if(raiz != null){
            print_post_recursive(raiz);
        }else{
            System.out.println();
        }
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
        if(raiz != null){
            print_pre_recursive(raiz, 0);
        }else{
            System.out.println();
        }
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
     * Encuentra todos los valores dentro del árbol que están dentro del rango dado
     * @param x Límite inferior del rango, inclusivo
     * @param y Límite superior del rango, inclusivo
     * @return Una lista con todos los valores dentro del árbol que se encuentran en el rango
     */
    public Lista_array_dinamico<T> range_search(T x, T y){
        Lista_array_dinamico<T> resultado = new Lista_array_dinamico<>();

        if(raiz != null){
            Node_tree<T> aux = search(x, false);
            if(aux.get_valor().compareTo(x) >= 0){
                resultado.addLast(aux.get_valor());
            }
            aux = Next(aux);
            while(aux != null && aux.get_valor().compareTo(y) <= 0){
                resultado.addLast(aux.get_valor());
                aux = Next(aux);
            }
        }

        return resultado;
    }

    /**
     * Encuentra el nodo del árbol con el valor dado, o la posición en la que debería estar, y retorna su padre
     * @param valor
     * @param find_exact En el caso de no encontrar el valor dentro del árbol, indica si se debería considerar fallida la operación o, por el contrario, se debe retornar la posición en la que se debería insertar el valor dado
     * @return El padre del nodo que contiene el valor indicado (o this.raiz si el nodo con el valor es la raíz); o null si dicho nodo no existe (find_exact == true), o el que debería ser el nodo padre del valor dado (find_exact == false)
     */
    public Node_tree<T> search(T valor, boolean find_exact){
        Node_tree<T> current = raiz;
        while(current != null){
            if(current.get_valor().compareTo(valor) > 0){
                if(current.get_left() != null){
                    if(current.get_left().get_valor().compareTo(valor) != 0){
                        current = current.get_left();
                    }else{
                        break;
                    }
                }else{
                    if(find_exact){
                        current = null;
                    }
                    break;
                }
            }else if(current.get_valor().compareTo(valor) < 0){
                if(current.get_right() != null){
                    if(current.get_right().get_valor().compareTo(valor) != 0){
                        current = current.get_right();
                    }else{
                        break;
                    }
                }else{
                    if(find_exact){
                        current = null;
                    }
                    break;
                }
            }else{
                break;
            }
        }

        return current;
    }

    /**
     * @return El número de nodos del árbol
     */
    public int size(){
        return raiz != null ? size_recursive(raiz) : 0;
    }

    public int size_no_check(){
        return raiz != null ? size_recursive_no_check(raiz) : 0;
    }

    private int size_recursive(Node_tree<T> n){
        return 1 + (n.get_left() != null ? size_recursive(n.get_left()) : 0) + (n.get_right() != null ? size_recursive(n.get_right()) : 0);
    }

    private int size_recursive_no_check(Node_tree<T> n){
        if(n == null){
            return 0;
        }else{
            return 1 + size_recursive(n.get_left()) + size_recursive(n.get_right());
        }
    }
}