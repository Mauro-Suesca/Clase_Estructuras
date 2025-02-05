public class AVL_tree<T extends Comparable<T>> extends Binary_tree<T>{
    AVL_tree(){
        super();
    }
    AVL_tree(T element){
        raiz = new Node_tree_avl<T>(element);
    }

    @Override public boolean delete(T valor) throws Invalid_size_operation{
        if(raiz != null){
            Node_tree_avl<T> raiz_avl = (Node_tree_avl<T>)raiz;
            boolean respuesta = false;

            if(raiz.get_valor().compareTo(valor) == 0){
                if(raiz_avl.get_left() == null){
                    this.raiz = raiz_avl.get_right();
                }else if(raiz_avl.get_right() == null){
                    this.raiz = raiz_avl.get_left();
                }else{
                    Node_tree_avl<T> aux = raiz_avl.get_right();
                    Stack_nodo<Node_tree_avl<T>> actualizar = new Stack_nodo<>();
                    boolean lefta_righta = true;

                    if(aux.get_left() != null){
                        actualizar.push(aux);
                        while(aux.get_left().get_left() != null){
                            actualizar.push(aux.get_left());
                            aux = aux.get_left();
                        }
                    }else{
                        aux = raiz_avl;
                        lefta_righta = false;
                    }

                    raiz_avl.set_valor((lefta_righta ? aux.get_left() : aux.get_right()).get_valor());
                    delete_truly(aux, lefta_righta);
                    
                    while(!actualizar.empty()){
                        update_node_height(actualizar.pop());
                    }
                }
            }else{
                respuesta = delete_recursive(raiz_avl, valor);
            }

            if(!is_balanced(raiz_avl)){
                rebalance_root();
            }else{
                update_node_height(raiz_avl);
            }

            return respuesta;
        }else{
            throw new Invalid_size_operation("Error: Arbol vacio. No se puede eliminar el elemento.");
        }
    }

    /**
     * Busca y elimina un nodo de un árbol, y se encarga de que la estructura del árbol no se pierda
     * @param n El nodo actual en la recursión, no puede ser el nodo con el valor dado
     * @param valor El valor a buscar para eliminar
     * @return Si se eliminó un nodo en la recursión
     */
    private boolean delete_recursive(Node_tree_avl<T> n, T valor){
        boolean respuesta;
        if(n.get_valor().compareTo(valor) > 0){
            if(n.get_left() != null){
                if(n.get_left().get_valor().compareTo(valor) != 0){
                    respuesta = delete_recursive(n.get_left(), valor);
                }else{
                    delete_truly(n, true);
                    respuesta = true;
                }
                if(respuesta){
                    if(!is_balanced(n.get_left())){
                        rebalance(n, true);
                    }else{
                        update_node_height(n.get_left());
                    }
                }
            }else{
                respuesta = false;
            }
        }else{
            if(n.get_right() != null){
                if(n.get_right().get_valor().compareTo(valor) != 0){
                    respuesta = delete_recursive(n.get_right(), valor);
                }else{
                    delete_truly(n, false);
                    respuesta = true;
                }
                if(respuesta){
                    if(!is_balanced(n.get_right())){
                        rebalance(n, false);
                    }else{
                        update_node_height(n.get_right());
                    }
                }
            }else{
                respuesta = false;
            }
        }

        if(respuesta){
            update_node_height(n);
        }

        return respuesta;
    }

    /**
     * Método auxiliar de delete_recursive, se encarga de eliminar el nodo en sí y de que los hijos de ese nodo se mantengan balanceados y con alturas coherentes
     * @param padre El padre del nodo a eliminar
     * @param left Si el nodo a eliminar es el hijo de la izquierda de 'padre'
     */
    private void delete_truly(Node_tree_avl<T> padre, boolean left){
        Node_tree_avl<T> nodo = left ? padre.get_left() : padre.get_right();
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
            Node_tree_avl<T> aux = nodo.get_right();
            Stack_nodo<Node_tree_avl<T>> actualizar = new Stack_nodo<>();
            boolean lefta_righta = true;

            if(aux.get_left() != null){
                actualizar.push(aux);
                while(aux.get_left().get_left() != null){
                    actualizar.push(aux.get_left());
                    aux = aux.get_left();
                }
            }else{
                aux = nodo;
                lefta_righta = false;
            }

            nodo.set_valor((lefta_righta ? aux.get_left() : aux.get_right()).get_valor());
            delete_truly(aux, lefta_righta);
            
            while(!actualizar.empty()){
                update_node_height(actualizar.pop());
            }
        }
    }

    @Override public int height(){
        return raiz != null ? ((Node_tree_avl<T>)raiz).get_height() : 0;
    }

    @Override public void insert(T element){
        if(raiz != null){
            insert_recursive((Node_tree_avl<T>)raiz, element);
        }else{
            raiz = new Node_tree_avl<T>(element);
        }
    }

    /**
     * Busca recursivamente la posición indicada para insertar un nodo al árbol y lo mantiene balanceado
     * @param n El nodo actual dentro de la recursión
     * @param element El valor del elemento a insertar
     * @return true si el nodo actual está balanceado, false si no
     */
    private boolean insert_recursive(Node_tree_avl<T> n, T element){
        if(n.get_valor().compareTo(element) >= 0){
            if(n.get_left() != null){
                if(!insert_recursive(n.get_left(), element)){
                    rebalance(n, true);
                }
                if(!is_balanced(n)){
                    return false;
                }

                update_node_height(n);
            }else{
                n.set_left(new Node_tree_avl<T>(element));
            }
        }else{
            if(n.get_right() != null){
                if(!insert_recursive(n.get_right(), element)){
                    rebalance(n, false);
                }
                if(!is_balanced(n)){
                    return false;
                }

                update_node_height(n);
            }else{
                n.set_right(new Node_tree_avl<T>(element));
            }
        }
        return true;
    }

    /**
     * Indica si un nodo está balanceado según las alturas de sus hijos
     * @param n El nodo a revisar
     * @return Si el nodo está balanceado, retorna true si el nodo es null
     */
    public boolean is_balanced(Node_tree_avl<T> n){
        if(n != null){
            return Math.abs((n.get_left() != null ? n.get_left().get_height() : 0) - (n.get_right() != null ? n.get_right().get_height() : 0)) <= 1;
        }else{
            return true;
        }
    }

    /**
     * Encuentra el Node_tree_avl dentro del árbol con el mínimo valor mayor al valor del nodo dado
     * @param nodo El Node_tree_avl cuyo vecino siguiente se quiere encontrar
     * @return El Node_tree_avl dentro del árbol cuyo valor es el mínimo valor mayor al valor del nodo dado, o null si no existe tal valor
     */
    public Node_tree_avl<T> Next(Node_tree_avl<T> nodo){
        Node_tree_avl<T> current = null;
        if(nodo.get_right() != null){
            current = nodo.get_right();
            while(current.get_left() != null){
                current = current.get_left();
            }
        }else{
            if(raiz.get_valor().compareTo(nodo.get_valor()) > 0){
                Node_tree_avl<T> min = (Node_tree_avl<T>)raiz.get_left();
                current = (Node_tree_avl<T>)raiz.get_left();
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
     * Rebalancea un sub-árbol y actualiza la altura de tanto el nodo que era la raíz del sub-árbol como del que pasó a serla
     * @param padre El padre del subárbol a rebalancear
     * @param lefta_righta Si el hijo a rebalancear es el de la izquierda
     */
    private void rebalance(Node_tree_avl<T> padre, boolean lefta_righta){
        Node_tree_avl<T> hijo = lefta_righta ? padre.get_left() : padre.get_right();
        Node_tree_avl<T> aux;

        if((hijo.get_left() != null ? hijo.get_left().get_height() : 0) > (hijo.get_right() != null ? hijo.get_right().get_height() : 0)){
            aux = hijo.get_left();
            if((aux.get_right() != null ? aux.get_right().get_height() : 0) > (aux.get_left() != null ? aux.get_left().get_height() : 0)){
                rotate_left(hijo, true);
            }
            rotate_right(padre, lefta_righta);
        }else{
            aux = hijo.get_right();
            if((aux.get_left() != null ? aux.get_left().get_height() : 0) > (aux.get_right() != null ? aux.get_right().get_height() : 0)){
                rotate_right(hijo, false);
            }
            rotate_left(padre, lefta_righta);
        }
    }

    /**
     * Rebalancea la raiz del árbol y actualiza su altura
     */
    private void rebalance_root(){
        Node_tree_avl<T> raiz_avl = (Node_tree_avl<T>)raiz, aux;

        if((raiz_avl.get_left() != null ? raiz_avl.get_left().get_height() : 0) > (raiz_avl.get_right() != null ? raiz_avl.get_right().get_height() : 0)){
            aux = raiz_avl.get_left();
            if((aux.get_right() != null ? aux.get_right().get_height() : 0) > (aux.get_left() != null ? aux.get_left().get_height() : 0)){
                rotate_left(raiz_avl, true);
            }
            rotate_root(false);
        }else{
            aux = raiz_avl.get_right();
            if((aux.get_left() != null ? aux.get_left().get_height() : 0) > (aux.get_right() != null ? aux.get_right().get_height() : 0)){
                rotate_right(raiz_avl, false);
            }
            rotate_root(true);
        }
    }

    /**
     * Rota un sub-árbol hacia la derecha y actualiza la altura de tanto el nodo que era la raíz del sub-árbol como del que pasó a serla
     * @param padre El padre del subárbol a rotar
     * @param lefta_righta Si el hijo a rotar es el de la izquierda
     */
    private void rotate_right(Node_tree_avl<T> padre, boolean lefta_righta){
        //Rotar
        Node_tree_avl<T> hijo = lefta_righta ? padre.get_left() : padre.get_right();
        Node_tree_avl<T> aux = hijo.get_left().get_right();
        if(lefta_righta){
            padre.set_left(hijo.get_left());
            padre.get_left().set_right(hijo);
        }else{
            padre.set_right(hijo.get_left());
            padre.get_right().set_right(hijo);
        }
        hijo.set_left(aux);

        //Actualizar alturas
        update_node_height(hijo);
        hijo = lefta_righta ? padre.get_left() : padre.get_right(); //Los hijos de padre cambiaron por la rotación, ya no es el mismo "hijo" de antes
        update_node_height(hijo);
    }

    /**
     * Rota la raiz del árbol en la dirección indicada y actualiza su altura
     * @param left Si la dirección a rotar es la izquierda
     */
    private void rotate_root(boolean left){
        //Rotar
        Node_tree_avl<T> raiz_avl = (Node_tree_avl<T>)raiz, aux;

        if(left){
            aux = raiz_avl.get_right().get_left();
        }else{
            aux = raiz_avl.get_left().get_right();
        }
        
        if(left){
            this.raiz = raiz_avl.get_right();
            this.raiz.set_left(raiz_avl);
            raiz_avl.set_right(aux);
        }else{
            this.raiz = raiz_avl.get_left();
            this.raiz.set_right(raiz_avl);
            raiz_avl.set_left(aux);
        }

        //Actualizar alturas
        update_node_height(raiz_avl);
        raiz_avl = (Node_tree_avl<T>)raiz; //La raíz cambió por la rotación, ya no es el mismo "raiz_avl" de antes
        update_node_height(raiz_avl);
    }

    /**
     * Rota un sub-árbol hacia la izquierda y actualiza la altura de tanto el nodo que era la raíz del sub-árbol como del que pasó a serla
     * @param padre El padre del subárbol a rotar
     * @param lefta_righta Si el hijo a rotar es el de la izquierda
     */
    private void rotate_left(Node_tree_avl<T> padre, boolean lefta_righta){
        //Rotar
        Node_tree_avl<T> hijo = lefta_righta ? padre.get_left() : padre.get_right();
        Node_tree_avl<T> aux = hijo.get_right().get_left();
        if(lefta_righta){
            padre.set_left(hijo.get_right());
            padre.get_left().set_left(hijo);
        }else{
            padre.set_right(hijo.get_right());
            padre.get_right().set_left(hijo);
        }
        hijo.set_right(aux);

        //Actualizar alturas
        update_node_height(hijo);
        hijo = lefta_righta ? padre.get_left() : padre.get_right(); //Los hijos de padre cambiaron por la rotación, ya no es el mismo "hijo" de antes
        update_node_height(hijo);
    }

    /**
     * Actualiza la altura del nodo dado según las alturas de sus dos hijos
     * @param n El nodo a actualizar
     */
    private void update_node_height(Node_tree_avl<T> n){
        if(n != null){
            n.set_height(1 + Math.max((n.get_left() != null ? n.get_left().get_height() : 0), (n.get_right() != null ? n.get_right().get_height() : 0)));
        }
    }
}